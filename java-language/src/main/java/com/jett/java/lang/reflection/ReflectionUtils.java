package com.jett.java.lang.reflection;


import java.lang.reflect.Field;

/**
 * 反射工具
 *
 * @author jett
 */
public class ReflectionUtils {
    
    /**
     * 获取私有成员变量的值
     *
     * @param instance
     * @param filedName
     * @return
     */
    public static Object getPrivateField(Object instance, String filedName) throws NoSuchFieldException, IllegalAccessException {
        Field field = instance.getClass().getDeclaredField(filedName);
        field.setAccessible(true);
        return field.get(instance);
    }
    
    /**
     * @see ReflectionUtils#getPrivateFieldIncludeSuper(Object, String, Class)
     */
    public static Object getPrivateFieldIncludeSuper(Object instance, String filedName) throws NoSuchFieldException, IllegalAccessException {
        return getPrivateFieldIncludeSuper(instance, filedName, instance.getClass());
    }
    
    /**
     * 获取私有成员变量的值
     *
     * @param instance
     * @param filedName
     * @return
     */
    public static Object getPrivateFieldIncludeSuper(Object instance, String filedName, Class<?> cls) throws NoSuchFieldException, IllegalAccessException {
        // 先在本类查找，查无则查询父类
        if (cls == null) {
            throw new NoSuchFieldException("到达 Object 都查无此field字段：" + filedName);
        }
        Field[] declaredFields = cls.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.getName().equals(filedName)) {
                Field field = cls.getDeclaredField(filedName);
                field.setAccessible(true);
                return field.get(instance);
            }
        }
        // 查询父类
        return getPrivateFieldIncludeSuper(instance, filedName, cls.getSuperclass());
    }
    
    
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
    
        UserInfo author = new UserInfo();
        author.setAge(120);
        author.setName("名称");
        author.setSex("男");
        
        System.out.println(ReflectionUtils.getPrivateField(author, "age"));
        System.out.println(ReflectionUtils.getPrivateFieldIncludeSuper(author, "userInfoField_B"));
        System.out.println(ReflectionUtils.getPrivateFieldIncludeSuper(author, "userInfoField_A"));
        System.out.println(ReflectionUtils.getPrivateFieldIncludeSuper(author, "不存在的字段", author.getClass()));
    }
}
