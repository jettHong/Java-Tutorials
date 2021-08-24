package com.jett.java.modifiers;


/**
 * 基类
 * @author jett
 */
public class BaseClass {
    private final String strPrivate = "在同一类内可见。使用对象：变量、方法。 注意：不能修饰类（外部类）";
    protected String strProtected = "对同一包内的类和所有子类可见。使用对象：变量、方法。 注意：不能修饰类（外部类）。";
    protected String strPublic = "对所有类可见。使用对象：类、接口、变量、方法。";
}
