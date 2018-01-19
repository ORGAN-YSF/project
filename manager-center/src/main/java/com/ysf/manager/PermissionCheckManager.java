package com.ysf.manager;

import com.ysf.common.util.SpringContextHolder;
import com.ysf.factory.ICheckFactory;

public class PermissionCheckManager {
	private static final PermissionCheckManager me = new PermissionCheckManager();
	
	private ICheckFactory defaultCheckFactory = SpringContextHolder.getBean(ICheckFactory.class);

	public static PermissionCheckManager me() {
		return me;
	}

	private PermissionCheckManager() {
	}

	public PermissionCheckManager(ICheckFactory checkFactory) {
		this.defaultCheckFactory = checkFactory;
	}

	public void setDefaultCheckFactory(ICheckFactory defaultCheckFactory) {
		this.defaultCheckFactory = defaultCheckFactory;
	}

	public static boolean check(Object[] permissions) {
		return me.defaultCheckFactory.check(permissions);
	}

	public static boolean checkAll() {
		return me.defaultCheckFactory.checkAll();
	}
}
