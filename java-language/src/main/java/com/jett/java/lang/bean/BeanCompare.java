package com.jett.java.lang.bean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 类值比对工具
 */
public class BeanCompare {
    public static List<Comparison> compare(Object beforeObj, Object afterObj) throws Exception {
        List<Comparison> differents = new ArrayList<Comparison>();
        
        if (beforeObj == null) {
            throw new Exception("原对象不能为空");
        }
        if (afterObj == null) {
            throw new Exception("新对象不能为空");
        }
        if (!beforeObj.getClass().isAssignableFrom(afterObj.getClass())) {
            throw new Exception("两个对象不相同，无法比较");
        }
        
        //取出属性
        Field[] beforeFields = beforeObj.getClass().getDeclaredFields();
        Field[] afterFields = afterObj.getClass().getDeclaredFields();
        Field.setAccessible(beforeFields, true);
        Field.setAccessible(afterFields, true);
        
        //遍历取出差异值
        if (beforeFields != null && beforeFields.length > 0) {
            for (int i = 0; i < beforeFields.length; i++) {
                Object beforeValue = beforeFields[i].get(beforeObj);
                Object afterValue = afterFields[i].get(afterObj);
                // 差异：
                // 1：beforeValue不是null不是空串，AB值不相同
                // 2：A值为null、空串，B值不为null
                if ((beforeValue != null && !"".equals(beforeValue) && !beforeValue.equals(afterValue))
                        || ((beforeValue == null || "".equals(beforeValue)) && afterValue != null)) {
                    Comparison comparison = new Comparison();
                    comparison.setField(beforeFields[i].getName());
                    comparison.setBefore(beforeValue);
                    comparison.setAfter(afterValue);
                    differents.add(comparison);
                }
            }
        }
        
        return differents;
    }
}
