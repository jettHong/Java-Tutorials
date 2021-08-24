package com.jett.java.construct;

/**
 * @author jett
 */
public class SubClass extends BaseClass {
    
    /**
     * 可以定义一个与父类入参不同的构建函数。
     * 但必需显式的调用父类的 super,
     * 本例子中，父类有两个构造函数，super(), super(string)，子类都可以在构造函数中调用相应方法。
     * 如果父类没有定义无参数的构造，子类又没有显示调用时，编译不通过。
     */
    public SubClass(Integer age) {
        super();
//        super("转为父类的类型" + age);
    }
    
}
