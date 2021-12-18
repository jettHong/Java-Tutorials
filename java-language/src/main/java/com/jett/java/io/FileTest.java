package com.jett.java.io;

import org.apache.commons.codec.binary.Hex;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class FileTest {
    public static void main(String[] args) throws IOException {
        File file = new File("D:/workSpace/kkFileView/server/src/main/file/demo/测试64位测试qw测试`-=[];',..-+!@#$%^&()+{}.docx");
//        file.mkdir();
        
        System.out.println(file.exists());
        System.out.println(file.delete());
    
        removeBomWithDir(new File("D:\\workSpace\\game\\Lin\\l1j-zhw-202111\\src"));
    }
    
    public static void removeBomWithDir(File file) throws IOException {
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                removeBomWithDir(f);
            }
        } else {
            removeBom(file.toPath());
        }
    }
    
    
    /**
     * 判断是否为 BOM 开头
     * @param path
     * @return
     * @throws IOException
     */
    public static boolean isContainBOM(Path path) throws IOException {
        
        if (Files.notExists(path)) {
            throw new IllegalArgumentException("Path: " + path + " does not exists!");
        }
        
        boolean result = false;
        
        byte[] bom = new byte[3];
        try (InputStream is = new FileInputStream(path.toFile())) {
            
            // read first 3 bytes of a file.
            is.read(bom);
            
            // BOM encoded as ef bb bf
            String content = new String(Hex.encodeHex(bom));
            if ("efbbbf".equalsIgnoreCase(content)) {
                result = true;
            }
            
        }
        
        return result;
    }
    
    
    /**
     * 移除 BOM
     * @param path
     * @throws IOException
     */
    public static void removeBom(Path path) throws IOException {
        
        if (isContainBOM(path)) {
            
            byte[] bytes = Files.readAllBytes(path);
            ByteBuffer bb = ByteBuffer.wrap(bytes);
            
            byte[] bom = new byte[3];
            // get the first 3 bytes
            bb.get(bom, 0, bom.length);
            
            // remaining
            byte[] contentAfterFirst3Bytes = new byte[bytes.length - 3];
            bb.get(contentAfterFirst3Bytes, 0, contentAfterFirst3Bytes.length);
            
            System.out.println("Remove the first 3 bytes, and overwrite the file!");
            
            // override the same path
            Files.write(path, contentAfterFirst3Bytes);
            
        } else {
            System.out.println("This file doesn't contains UTF-8 BOM!");
        }
        
    }

}
