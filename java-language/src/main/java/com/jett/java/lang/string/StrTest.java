package com.jett.java.lang.string;

/**
 * 字符串是 Java 中特殊的类，使用方法像一般的基本数据类型，被广泛应用在 Java 编程中。
 * Java 没有内置的字符串类型，而是在标准 Java 类库中提供了一个 String 类来创建和操作字符串。
 *
 * @author jett
 */
public class StrTest {
    public static void main(String[] args) {
        String dateStr = "12345678";
        String result = dateStr.substring(0,4) + "-" + dateStr.substring(4,6) + "-" + dateStr.substring(6,8);
        System.out.println(result);
        // 与其它类型的转换 toString
        // 拼接 加号、concat()
        // 长度 length()
        // 大小写转换 toLowerCase()、toUpperCase()
        // 去空格（前后，中间？） trim
        // 抽取子字串 substring
        // 分割 split
        // 替换 replace
        // 比较 equals()
        // null与空串
        // 查找 indexOf contains
        
        
    }
}
