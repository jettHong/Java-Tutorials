package com.jett.design.patterns.builder.开闭原则;

/**
 * JAVA 优惠逻辑类，继承于java课程，
 * 用于处理优惠逻辑，不影响到原来 JavaCourse
 *
 * @author jett
 */
public class JavaDiscountCourse extends JavaCourse {
    
    
    /**
     * 取得原始的价格
     * @return
     */
    public Double getOriginPrice() {
        return super.getPrice();
    }
    
    /**
     * 取得价格(优惠后)
     * @return
     */
    @Override
    public Double getPrice() {
        return super.getPrice() * 0.8;
    }
}
