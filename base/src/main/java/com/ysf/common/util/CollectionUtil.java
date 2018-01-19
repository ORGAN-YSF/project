package com.ysf.common.util;

public class CollectionUtil {
	public static <T> String join(T[] array, String conjunction) {
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (T item : array) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(conjunction);
			}
			sb.append(item);
		}
		return sb.toString();
	}
}
