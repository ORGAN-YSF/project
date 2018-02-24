package com.ysf.aop;

import java.lang.reflect.Method;
import javax.naming.NoPermissionException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import com.ysf.annotation.Permission;
import com.ysf.manager.PermissionCheckManager;

@Aspect
@Component
public class PermissionAop {
	@Pointcut(value="@annotation(com.ysf.annotation.Permission)")
	private void cutPermission() {
	}

	@Around("cutPermission()")
	public Object doPermission(ProceedingJoinPoint point) throws Throwable {
		//方法标志
		MethodSignature msig = (MethodSignature) point.getSignature();
		//获取切入的方法
		Method method = msig.getMethod();
		//获取方法上的注解
		Permission annotation = method.getAnnotation(Permission.class);
		//获取注解上的value值
		Object[] permissions = annotation.value();
		
		if(permissions == null || permissions.length == 0) {
			//检查全体角色
			boolean result = PermissionCheckManager.checkAll();
			if (result) {
				return point.proceed();
			} else {
				throw new NoPermissionException();
			}
		} else {
			//检查指定角色
			boolean result = PermissionCheckManager.check(permissions);
			if (result) {
				return point.proceed();
			} else {
				throw new NoPermissionException();
			}
		}
	} 
}
