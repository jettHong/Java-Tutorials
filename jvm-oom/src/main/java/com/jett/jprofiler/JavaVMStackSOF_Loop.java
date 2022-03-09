package com.jett.jprofiler;

/**
 * ·使用-Xss参数减少栈内存容量。
 * 结果： 抛出StackOverflowError异常， 异常出现时输出的堆栈深度相应缩小。
 *
 * VM Args：-Xss128k 栈大小
 */
public class JavaVMStackSOF_Loop {
    
    private int stackLength = 1;
    
    public void stackLeak() {
        stackLength++;
        stackLeak();
    }
    
    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF_Loop oom = new JavaVMStackSOF_Loop();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}
