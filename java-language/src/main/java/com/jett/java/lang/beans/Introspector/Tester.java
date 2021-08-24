package com.jett.java.lang.beans.Introspector;

import java.beans.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author jett
 * @Date 2021-3-8
 */
public class Tester {
    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        // 获取 beanClass 及其所有父类的 BeanInfo
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
        // bean 信息
        BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();
        // 属性信息
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        // 方法信息
        MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
        System.out.println("bean信息：\n" + beanInfo);
        System.out.println("bean描述：\n" + beanDescriptor);
        System.out.println("属性描述：");
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            System.out.println(propertyDescriptor);
        }
        System.out.println("方法描述：");
        for (MethodDescriptor methodDescriptor : methodDescriptors) {
            System.out.println(methodDescriptor);
        }
        
        Tester test = new Tester();
        test.propertyEditor();
        test.readWriteMethod();
        
    }
    
    
    public void propertyEditor() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        // 修改属性，未完成
        User user = new User();
        System.out.println(user);
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("id", User.class);
        propertyDescriptor.setPropertyEditorClass(TitlePositionEditor.class);
        PropertyEditor editor = propertyDescriptor.createPropertyEditor(user);
        editor.setValue("新ID编号111111");
        System.out.println(user);
    }
    
    public void readWriteMethod() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        User user = new User();
        System.out.println(user);
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("id", User.class);
        Method readMethod = propertyDescriptor.getReadMethod();
        Method writeMethod = propertyDescriptor.getWriteMethod();
        System.out.println(readMethod);
        System.out.println(writeMethod);
        //将值赋进这个类中
        writeMethod.invoke(user, "新ID编号222222");
        Object readResult = readMethod.invoke(user);
        System.out.println(readResult);
        System.out.println(user);
    }
}
