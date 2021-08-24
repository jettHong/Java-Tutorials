package com.jett.java5.annotation;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 注解测试类
 */
@MyAnnType()
public class MyAnnotationTest {
    
    @MyAnnField(name="名字", order = 2)
    private String name;
    
    @MyAnnField(name="年龄", order = 1)
    private Integer age;
    
    @MyAnnField(name="属性01", order = 3)
    private final Integer attr01 = 123;
    
    @MyAnnMethod(value = "注解1的值")
    public String myfun(String str) {
        return ">" + str;
    }
    
    public static void main(String[] args) {
        MyAnnotationTest test = new MyAnnotationTest();
        test.myfun("方法入参");
        Field[] declaredFields = test.getClass().getDeclaredFields();
        Arrays.stream(declaredFields).forEach(f->{
            f.setAccessible(true);
            MyAnnField myAnnField = f.getAnnotation(MyAnnField.class);
            Object obj = null;
            try {
                obj = f.get(test);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println(myAnnField.name());
            System.out.println(myAnnField.order());
        });
    }
    
    
    
    
}
