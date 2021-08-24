package com.jett.design.patterns.builder;

/**
 * 累加器
 *
 * @author jett
 */
public class Sum {
    Integer result = 0;
    
    /**
     * （链式）添加
     * @param i
     * @return
     */
    public Sum add(Integer i) {
        this.result += i;
        return this;
    }
    
    /**
     * 返回最终结果
     * @return
     */
    public Integer sum() {
        return result;
    }
    
}
