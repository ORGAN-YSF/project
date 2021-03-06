package com.ysf.util;

import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * 控制层工具类
 */
public class ControllerUtil {
	public static ValueFilter valueFilter = new ValueFilter() {
		@Override
		public Object process(Object o,String key,Object value) {
			if(value instanceof Long){
				return String.valueOf(value);
			}
			return value;
		}
	};
}
