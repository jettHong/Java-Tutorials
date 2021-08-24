package com.jett.java.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Java开发手册测试用例
 *
 * @author jett
 */
public class MapTest {
    

    @Test
    public void base() {
        Map<String, String> map = new HashMap(16) {{
            put("1", "a");
            put("2", "bb");
            put("3", "cccc");
            put("4", null);
        }};
        
        // PDF 推荐使用 forEach
        map.forEach((k, v) -> System.out.println(k + "=" + v));
    }
    
    /**
     * 冲突
     */
    @Test
    public void collision() {
        // 观察多个hash值碰撞时，如何扩容与取回。
        Map<String, String> temp = new HashMap(2);
        temp.put("aaa", "key-aaa");
        temp.put("bbb", "key-bbb");
        temp.put("ccc", "key-ccc");
        temp.put("ddd", "key-ddd");
        temp.put("eee", "key-eee");
        
        String strG = temp.get("ggg");
        String strE = temp.get("eee");
        
    }
    
    
    /**
     * 确定边界
     */
    @Test
    public void border() {
        
        int hashValue = 18;
        int length = 16;
        
        // 取模
        int mod = hashValue % length;
        // 寻址算法
        int addr = hashValue & (length - 1);
        System.out.println("mod  = " + mod);
        System.out.println("addr = " + addr);
        
    }
    
    
    /**
     * 寻找冲突的hashCode，以验证HashMap原理。
     * https://blog.csdn.net/haerxiong/article/details/109330836
     */
    @Test
    public void findCollision() {
        HashMap<String, String> map = new HashMap<>();
        
        // 根据原理，举例构造两个hashCode相等的字符串
        String a = new String(new char[]{2, 0});
        String b = new String(new char[]{0, 62});
        System.out.println(a);
        System.out.println(b);
        
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        
        // 以此两个字符串为基础构造多个hashCode相等的字符串
        List<String> list = new ArrayList<String>();
        list.add(a);
        list.add(b);
        for (int i = 0; i < 3; i++) {
            List<String> tmp = new ArrayList<String>();
            list.forEach(s -> {
                tmp.add(s + a);
                tmp.add(s + b);
            });
            list = tmp;
        }
        
        // 字符串依次放入map中，可断点跟踪代码验证了。
        for (int i = 0; i < 8; i++) {
            String s = list.get(i);
            System.out.println(s + "|" + s.hashCode());
            map.put(s, "index" + i);
        }
        System.out.println(map);
        map.put(list.get(8), "index8888");
        System.out.println(map);
    }
}
