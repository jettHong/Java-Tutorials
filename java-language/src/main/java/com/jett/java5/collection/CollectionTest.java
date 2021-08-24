package com.jett.java5.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @see java.util.Collection
 * 1、重点方法：add、addAll、
 * @author jett
 */
public class CollectionTest {
    
    static Collection collection = new ArrayList<String>();
    
    /**
     * 一、ArrayList
     * 1、底层是数组，添加元素时，需要复制数据，造成性能下降，所以一般地需要进行业务考虑数组长度，使用有数构造函数。
     * 2、特点：
     *     可重复数据；
     *     一般地按add方法添加的元素，存储数据顺序也一样。（区别于map）；
     *     可直接根据索引下标查找元素；
     */
    
    @Test
    public void arrayListRemove() {
        collection.add("1");
        collection.add("2");
        collection.add(null);
        collection.add("3");
        collection.add(null);
        collection.add("4");
        System.out.println(collection);
        // jdk8 之后引入的 forEach 方法
//        collection.forEach((obj) -> {
//            System.out.println(obj+",");
//        });
        // 移除的是第一个
        collection.remove(null);
        System.out.println(collection);
    }
    
}
