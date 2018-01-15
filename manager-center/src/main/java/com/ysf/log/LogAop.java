package com.ysf.log;

import java.lang.reflect.Method;
import java.util.Map;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.ysf.annotation.BusinessLog;
import com.ysf.common.bo.UserInfo;
import com.ysf.common.constant.dictmap.AbstractDictMap;
import com.ysf.factory.LogTaskFactory;
import com.ysf.util.Contrast;
import com.ysf.util.HttpUtil;
import com.ysf.util.ShiroUtil;

@Aspect
@Component
public class LogAop {
	private static final Logger log = LoggerFactory.getLogger(LogAop.class);
	
	@Pointcut(value = "@annotation(com.ysf.annotation.BusinessLog)")
	public void cutService(){
	}
	
	@Around("cutService()")
	public Object recordSysLog(ProceedingJoinPoint point) throws Throwable {
		//先执行业务
		Object result = point.proceed();
		
		try {
            handle(point);
        } catch (Exception e) {
            log.error("日志记录出错!", e);
        }
		
		return result;
	}

	private void handle(ProceedingJoinPoint point) throws Exception {
		//后期补充使用shiro获取当前用户信息
		UserInfo userInfo = ShiroUtil.getUserInfo();
		if(userInfo == null) {
			return;
		}
		
		//签名
		Signature sig = point.getSignature();
		MethodSignature msig = null;
		if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
		msig = (MethodSignature) sig;
		
		log.debug("方法名称:" + msig.getName() + "," + "参数类型:" + msig.getParameterTypes());
		//获取执行对象
		Object target = point.getTarget();
		Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
		String methodName = currentMethod.getName();
		
		//获取拦截方法的参数和类名
		String className = target.getClass().getName();
		
		BusinessLog annotation = currentMethod.getAnnotation(BusinessLog.class);
		String businessName = annotation.value();
		String key = annotation.key();
		Class<? extends AbstractDictMap> dictClass = annotation.dict();
		
		Map<String, String> parameters = HttpUtil.getRequestParameters();
        AbstractDictMap dictMap = (AbstractDictMap) dictClass.newInstance();        
        String msg = Contrast.parseMutiKey(dictMap,key,parameters);

        LogManager.me().executeLog(LogTaskFactory.bussinessLog(userInfo.getUserId(),businessName,className,methodName,msg));
	}
}

