package com.jett.java.lang.bean;

/**
 * ClassName: 对照实体
 */
public class Comparison {
    
    //字段
    private String field;
    //字段旧值
    private Object before;
    //字段新值
    private Object after;
    
    /**
     * @return the field
     */
    public String getField() {
        return field;
    }
    
    /**
     * @param field the field to set
     */
    public void setField(String field) {
        this.field = field;
    }
    
    /**
     * @return the before
     */
    public Object getBefore() {
        return before;
    }
    
    /**
     * @param before the before to set
     */
    public void setBefore(Object before) {
        this.before = before;
    }
    
    /**
     * @return the after
     */
    public Object getAfter() {
        return after;
    }
    
    /**
     * @param after the after to set
     */
    public void setAfter(Object after) {
        this.after = after;
    }
    
}
