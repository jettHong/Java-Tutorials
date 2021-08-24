package com.jett.java.util;

import org.junit.Test;

import java.util.UUID;

/**
 * 随机字串
 * @author:jett
 * @date:2021-08-24
 */
public class UUIDTest {
    @Test
    public void genUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        System.out.println(str);
    }
}
