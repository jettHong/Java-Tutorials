package com.jett.algorithm.list2tree;

import com.jett.SampleBean;

import java.beans.BeanInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * https://juejin.cn/post/6983904373508145189
 * 扁平数据结构转Tree，级联数据
 */
public class List2TreeMain {
    public static void main(String[] args) {
        List<SampleBean> list = new ArrayList<SampleBean>() {{
            add(SampleBean.Builder.create().name("强爸").age(1).baseInt(0).build());
            add(SampleBean.Builder.create().name("小强").age(10).baseInt(1).build());
            add(SampleBean.Builder.create().name("小强子").age(100).baseInt(10).build());
            add(SampleBean.Builder.create().name("强妹").age(2).baseInt(1).build());
        }};
        
        Map<Integer, SampleBean> collect = list.stream().collect(Collectors.toMap(SampleBean::getAge, i -> i));
        collect.keySet().stream().forEach(i -> {
            SampleBean sb = collect.get(i);
            SampleBean parent = collect.get(sb.getBaseInt());
            if (parent != null) {
                parent.addChildren(sb);
            }
        });
        
        collect.values().stream().forEach(System.out::println);
    }
    
}
