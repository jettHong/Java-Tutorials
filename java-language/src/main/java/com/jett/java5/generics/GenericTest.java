package com.jett.java5.generics;

import com.jett.SampleBean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class GenericTest {
    public static void main(String[] args) {
        SampleBean bean = SampleBean.Builder.create()
                .age(1)
                .name("123")
                .friends(new ArrayList<>())
                .build();
        try {
            // 这里直接把变量类型强制于方法内（拗口）
            // 取出来的属性类型由变量类型决定。
            String size = GenericTest.getByPropertyName(bean, "name");
            System.out.println(size);
            List<String> friends = GenericTest.getByPropertyName(bean, "friends");
            System.out.println(friends);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 通过对象中指定属性名的属性对象
     *
     * @param <C>
     * @param obj
     * @param name
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <C> C getByPropertyName(Object obj, String name) throws Exception {
        Class<?> c = obj.getClass();
        Field f = c.getDeclaredField(name);
        f.setAccessible(true);
        return (C) f.get(obj);
    }
}
