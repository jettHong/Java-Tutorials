package com.jett.java5.enumeration;

import java.util.Arrays;

/**
 * 枚举类
 * @author jett
 */
public class EnumTest {
    

    
    public static void main(String[] args) {
        
        // 创建枚举数组
        WeekEnum[] weekEnums = new WeekEnum[]{WeekEnum.MONDAY, WeekEnum.TUESDAY, WeekEnum.WEDNESDAY,
                WeekEnum.THURSDAY, WeekEnum.FRIDAY, WeekEnum.SATURDAY, WeekEnum.SUNDAY};
        
        // 输出序号
        // 如果枚举中没有定义方法，枚举值默认为从 0 开始的有序数值。
        // .ordinal() 从 0 开始
        for (int i = 0; i < weekEnums.length; i++) {
            System.out.println("day[" + i + "].ordinal():" + weekEnums[i].ordinal());
        }
        
        System.out.println("-------------------------------------");
        //通过compareTo方法比较,实际上其内部是通过ordinal()值比较的
        System.out.println("compareTo 1:" + weekEnums[0].compareTo(weekEnums[1]));
        System.out.println("compareTo 2:" + weekEnums[0].compareTo(WeekEnum.MONDAY));
        
        //获取该枚举对象的Class对象引用,当然也可以通过getClass方法
        Class<?> clazz = weekEnums[0].getDeclaringClass();
        System.out.println("clazz:" + clazz);
        System.out.println("-------------------------------------");
        
        // .name() 取得名称常量
        System.out.println("取得名称常量 days[0].name():" + weekEnums[0].name());
        System.out.println("取得名称常量 days[2].name():" + weekEnums[2].name());
        System.out.println("取得名称常量 days[2].toString():" + weekEnums[2]);
        System.out.println("取得名称常量 days[3].toString():" + weekEnums[3]);
        System.out.println("-------------------------------------");
        
        System.out.println("Enum.valueOf:" + Enum.valueOf(WeekEnum.class, weekEnums[0].name()));
        System.out.println("Day.valueOf 1:" + WeekEnum.valueOf(WeekEnum.class, weekEnums[0].name()));
        System.out.println("Day.valueOf 2:" + WeekEnum.valueOf(WeekEnum.class, "MONDAY"));
        
        // 枚举如何判断一个值是否枚举内。
        WeekEnum weekEnum = WeekEnum.ofIndex(2);
        System.out.println(weekEnum);
    
    }
}
