package com.jett.java7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 资源自动关闭
 * 被自动关闭的资源需要实现Closeable或者AutoCloseable接口
 * @author jett
 */
public class TryWithResource {
    public static void main(String[] args) {
        String path = TryWithResource.class.getResource("/gbk.txt").getPath();
        
        // 资源自动关闭
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String str = br.readLine();
            while (null != str) {
                System.out.println(str);
                str = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
