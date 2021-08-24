package com.jett.java.lang.string;

/**
 * StringBuffer 类是可变字符串类，创建 StringBuffer 类的对象后可以随意修改字符串的内容。
 * 每个 StringBuffer 类的对象都能够存储指定容量的字符串，如果字符串的长度超过了 StringBuffer 类对象的容量，则该对象的容量会自动扩大。
 *
 * @author jett
 */
public class StringBufferTest {
    public static void main(String[] args) {
        // 定义一个含有10个字符容量的字符串缓冲区
        StringBuffer stringBuffer = new StringBuffer(10);
        
        // 追加字符串
        stringBuffer.append("123");
    
    }
}
