package com.ysf.util;

import java.util.Map;
import com.ysf.common.constant.dictmap.AbstractDictMap;
import com.ysf.factory.DictFieldWarpperFactory;

/**
 * 对比对象变化的工具类
 * @author sunwenxing
 */
public class Contrast {
	
	/**
	 * @param dictMap	对应字典
	 * @param key		字典中的key
	 * @param requests	请求数据
	 * @return
	 */
	public static String parseMutiKey(AbstractDictMap dictMap, String key, Map<String, String> requests) {
        StringBuilder sb = new StringBuilder();
        
        //key多个值的情况，以逗号隔开
        if (key.indexOf(",") != -1) {
            String[] keys = key.split(",");
            for (String item : keys) {
                String fieldWarpperMethodName = dictMap.getFieldWarpperMethodName(item);
                String value = requests.get(item);
                if (fieldWarpperMethodName != null) {
                    Object valueWarpper = DictFieldWarpperFactory.createFieldWarpper(value,fieldWarpperMethodName);
                    sb.append(dictMap.get(item) + "=" + valueWarpper + ",");
                } else {
                    sb.append(dictMap.get(item) + "=" + value + ",");
                }
            }
            return StrUtil.removeSuffix(sb.toString(), ",");
        } else {
            String fieldWarpperMethodName = dictMap.getFieldWarpperMethodName(key);
            String value = requests.get(key);
            if (fieldWarpperMethodName != null) {
                Object valueWarpper = DictFieldWarpperFactory.createFieldWarpper(value, fieldWarpperMethodName);
                sb.append(dictMap.get(key) + "=" + valueWarpper);
            } else {
                sb.append(dictMap.get(key) + "=" + value);
            }
            return sb.toString();
        }
    }
}
