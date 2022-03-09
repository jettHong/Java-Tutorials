package com.jett.jprofiler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * OutOfMemoryError: Java heap space，本例中由 Bean.list 静态变量执有，无法回收。
 * VM Args：-Xmx128m -Xms128m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapMain {
    
    public static void main(String[] args) throws InterruptedException {
        boolean makeOOM = true;
        while (true) {
            int iTime = 1024;
            List list = new ArrayList<Bean>();
            while (iTime-- > 0) {
                list.add(new Bean());
                if (makeOOM) {
                    Bean.list.addAll(list); // 由静态变量执有，无法回收
                }
                TimeUnit.MILLISECONDS.sleep(10);
            }
        }
    }
    
}
