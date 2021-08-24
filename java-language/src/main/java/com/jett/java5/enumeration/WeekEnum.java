package com.jett.java5.enumeration;

import org.omg.CORBA.UNKNOWN;

import java.util.Arrays;

/**
 * 一星期，定义枚举类型
 */
public enum WeekEnum {
    /**
     * 周一
     */
    MONDAY("周一", 1),
    
    /**
     * 周二
     */
    TUESDAY("周二", 2),
    
    /**
     * 周三
     */
    WEDNESDAY("周三", 3),
    
    /**
     * 周四
     */
    THURSDAY("周四", 4),
    
    /**
     * 周五
     */
    FRIDAY("周五", 5),
    
    /**
     * 周六
     */
    SATURDAY("周六", 6),
    
    /**
     * 周日
     */
    SUNDAY("周日", 7),
    
    /**
     * 异常
     */
    UNKNOWN("错误", -1);
    
    WeekEnum(String name, Integer index) {
        this.name = name;
        this.index = index;
    }
    
    private final String name;
    private final Integer index;
    
    public String getName() {
        return name;
    }
    
    public Integer getIndex() {
        return index;
    }
    
    /**
     * 根据 index 取得对应枚举
     *
     * @param index
     * @return
     */
    public static WeekEnum ofIndex(Integer index) {
        WeekEnum result = Arrays.asList(values()).stream()
                .filter(item -> item.getIndex().equals(index))
                .findFirst().orElse(WeekEnum.UNKNOWN);
        return result;
    }
}
