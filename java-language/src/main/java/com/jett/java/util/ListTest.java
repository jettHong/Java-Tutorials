package com.jett.java.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListTest {
    
    
    /**
     * 创建一个 List 数据
     *
     * @return
     */
    public List<String> getList() {
        return new ArrayList<String>() {{
            add("a");
            add("b");
            add("c");
            add("d");
            add("e");
            add("f");
            add("g");
        }};
    }
    
    /**
     * 初始化
     */
    @Test
    public void init() {
        
        List<String> list = getList();
        System.out.println(list);
        
        // JDK8 - Stream.of
        List<String> numList = Stream.of("one", "two", "three").collect(Collectors.toList());
        
        
//        // JDK9
//        List<String> immutableList = List.of("one", "two", "three");
        
        // Arrays.asList 请慎用，这个List不能再次操作 add、remove
        List<String> stringList = Arrays.asList("a", "b", "c");
        try {
            stringList.add("d");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            stringList.remove(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * 清除记录
     */
    @Test
    public void clean() {
        
        List<String> list = getList();
        
        // 保存前 N 位
        int n = 2;
        list.subList(list.size() - (list.size() - n), list.size()).clear();
        System.out.println(list);
        
        
        // 删除后 N 位
        list = getList();
        list.subList(list.size() - n, list.size()).clear();
        System.out.println(list);
        
        // 删除指定位
        list = getList();
        list.remove(3); // 下标为 0，等于第 4 位的 d
        System.out.println(list);
    }
    
    /**
     * 复制
     */
    @Test
    public void listClone() {
        
        List<String> list = getList();
        
        // subList 截取出来的对象是浅拷贝，变更新的List，影响原List
        List<String> subList = list.subList(0, 2);
        subList.add(1, "替换");
        System.out.println(subList);
        System.out.println(list);
    }
    
    
}
