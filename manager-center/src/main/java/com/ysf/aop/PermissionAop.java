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
		MethodSignature msig = (MethodSignature) point.getSignature();
		Method method = msig.getMethod();
		Permission annotation = method.getAnnotation(Permission.class);
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
