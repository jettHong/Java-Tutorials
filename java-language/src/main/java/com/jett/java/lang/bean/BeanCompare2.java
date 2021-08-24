package com.jett.java.lang.bean;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 类属性值对比工具2
 */
public class BeanCompare2 {
    public static void main(String[] args) throws Exception {
        A a = new A("a", 1.2, 2, true, 'e');
        B b = new B("a", 1.3, 2, false, 'f');
        compare(a, b);
    }
    
    private static void compare(Object a, Object b) throws Exception {
        Map<String, Struct> fa = getFieldInfo(a);
        Map<String, Struct> fb = getFieldInfo(b);
        findDiff(fa, fb, a.getClass().toString(), b.getClass().toString());
    }
    
    private static void findDiff(Map<String, Struct> fa, Map<String, Struct> fb, String ta, String tb) {
        Iterator<String> iter = fa.keySet().iterator();
        while (iter.hasNext()) {
            String name = iter.next();
            Struct sa = fa.get(name);
            Struct sb = fb.get(name);
            if (sb == null) {
                System.out.println(tb + "缺少属性" + sa.getString(name));
                continue;
            }
            switch (sa.compare(sb)) {
                case 0:
                    System.out.println(ta + "与" + tb + "有共同的属性" + sa.getString(name));
                    break;
                case 1:
                    System.out.println(ta + "与" + tb + "属性" + name + "的类型不同[" + sa.getType() + ", " + sb.getType() + "]");
                    break;
                case 2:
                    System.out.println(ta + "与" + tb + "属性" + name + "的值不同[" + sa.getValue() + ", " + sb.getValue() + "]");
                    break;
                default:
                    System.out.println("无法比较属性" + name);
            }
        }
        iter = fb.keySet().iterator();
        while (iter.hasNext()) {
            String name = iter.next();
            if (!fa.containsKey(name)) {
                Struct sb = fb.get(name);
                System.out.println(ta + "缺少属性" + sb.getString(name));
            }
        }
    }
    
    private static Map<String, Struct> getFieldInfo(Object obj) throws Exception {
        Field[] fields = obj.getClass().getDeclaredFields();
        Map<String, Struct> info = new HashMap<String, Struct>();
        for (Field f : fields) {
            f.setAccessible(true);
            info.put(f.getName(), new Struct(f.getType(), f.get(obj)));
        }
        return info;
    }
    
    private static class Struct {
        private final Class cls;
        private final Object value;
        
        public Struct(Class cls, Object value) {
            this.cls = cls;
            this.value = value;
        }
        
        public Class getType() {
            return cls;
        }
        
        public Object getValue() {
            return value;
        }
        
        public int compare(Struct s) {
            if (cls != s.cls) {
                return 1;
            }
            if (value == s.value) {
                return 0;
            }
            if (value != null && value.equals(s.value)) {
                return 0;
            }
            return s.value.equals(value) ? 0 : 2;
        }
        
        public String getString(String name) {
            return cls + " " + name + " : " + value;
        }
    }
    
    private static final class A {
        private final String a;
        private final double b;
        private final int c;
        private final boolean d;
        private final char e;
        
        public A(String a, double b, int c, boolean d, char e) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = e;
        }
    }
    
    private static final class B {
        private final String a;
        private final double b;
        private final int c;
        private final boolean d;
        private final char f;
        
        public B(String a, double b, int c, boolean d, char f) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.f = f;
        }
    }
}
