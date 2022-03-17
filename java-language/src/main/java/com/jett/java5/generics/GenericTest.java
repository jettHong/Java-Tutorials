package com.jett.java5.generics;

import com.jett.SampleBean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * https://www.cnblogs.com/minikobe/p/11547220.html
 * https://developer.aliyun.com/article/640124
 */
public class GenericTest {
    public static void main(String[] args) {
        SampleBean bean = SampleBean.Builder.create()
                .age(1)
                .name("123")
                .friends(new ArrayList<>())
                .build();
        try {
            // 这里直接把变量类型强制于方法内（拗口）
            // 取出来的属性类型由变量类型决定。
            String size = GenericTest.getByPropertyName(bean, "name");
            System.out.println(size);
            List<String> friends = GenericTest.getByPropertyName(bean, "friends");
            System.out.println(friends);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 通配符，上界
        Vector<? extends Number> x = new Vector<Integer>(); // 这是正确的
        // Vector<? extends Number> y = new Vector<String>(); // 本行会报错
        Vector<? extends A> z = new Vector<C>(); // 这是正确的，C继承于B继承于A
        // z.add(new C()); // 报错，因为无法确认具体哪个子类，无法进行写操作。
        for (A a : z) {
            System.out.println(a); // 可以进行读操作。
        }
        
        // 通配符，下界
        Vector<? super Integer> m = new Vector<Integer>(); // 这是正确的
        Vector<? super Integer> n = new Vector<Number>(); // 这是正确的
        Vector<? super C> p = new Vector<B>(); // 这是正确的
        Vector<? super C> q = new Vector<A>(); // 这是正确的
        m.add(2); // 可以进行写操作，因为会自动向上转型。
        for (Object o : m) { // 因为无法确认知道当前是本身或者哪个父类，所以只能向上得到基类
            System.out.println(o);
        }
        
        List<A> aList = new ArrayList<A>();
        List<B> bList = new ArrayList<B>();
//        aList = bList; // 报错
//        bList = aList; // 报错
        C c = new C();
        aList.add(c);
        List<?> unKnowList = new ArrayList<>();
//        unKnowList.add(""); // 报错，因为你不知道集合是哪种类型，所以你只能够对集合进行读操作。
        unKnowList.forEach(System.out::println);
        
    }
    
    
    /**
     * 通过对象中指定属性名的属性对象
     *
     * @param <C>
     * @param obj
     * @param name
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <C> C getByPropertyName(Object obj, String name) throws Exception {
        Class<?> c = obj.getClass();
        Field f = c.getDeclaredField(name);
        f.setAccessible(true);
        return (C) f.get(obj);
    }
}

class A {
}

class B extends A {
}

class C extends B {
}
