package com.ysf.factory;

public interface ICheckFactory {
	/**
	 * 检查指定角色
     */
    boolean check(Object[] permissions);

    /**
     * 检查全体角色
     */
    boolean checkAll();
}
