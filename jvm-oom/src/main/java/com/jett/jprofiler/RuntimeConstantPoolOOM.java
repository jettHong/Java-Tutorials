package com.jett.jprofiler;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * VM Args：-XX:PermSize=4m -XX:MaxPermSize=4m
 * JDK8后，请使用 -XX:MetaspaceSize=9m -XX:MaxMetaspaceSize=9m
 * @author zzm
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) throws InterruptedException {
        // 使用Set保持着常量池引用， 避免Full GC回收常量池行为
        Set<String> set = new HashSet<>();

        long i = 0;
        while (i < Long.MAX_VALUE) {
            // String::intern()是一个本地方法，它的作用是如果字符串常量池中已经包含一个等于此String对象的字符串，
            // 则返回代表池中这个字符串的String对象的引用；
            // 否则会将此String对象包含的字符串添加到常量池中，并且返回此String对象的引用。
            set.add(String.valueOf(i++).intern());
            TimeUnit.MILLISECONDS.sleep(10);
        }
    }
}
