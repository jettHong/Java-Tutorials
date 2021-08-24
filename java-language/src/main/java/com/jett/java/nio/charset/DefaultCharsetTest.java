package com.jett.java.nio.charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 默认编码
 */
public class DefaultCharsetTest {

    public static void main(String[] args) {
        // 取得默认编码
        String defaultCharsetName = Charset.defaultCharset().displayName();
        System.out.println("defaultCharsetName:" + defaultCharsetName);
        // 系统Charset UTF-8
        System.out.println(StandardCharsets.UTF_8);
        System.out.println(StandardCharsets.UTF_8.displayName());
    }
}
