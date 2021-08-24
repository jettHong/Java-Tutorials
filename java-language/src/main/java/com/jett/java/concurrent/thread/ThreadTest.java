package com.jett.java.concurrent.thread;

import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 一、Thread 类
 * 自定义线程类，承继于 Thread 类，实现 run()，调用 start() 启动。
 * Q：为什么要使用 start() 启动，而不是 run()。
 * A：JVM 下不同操作系统的实现不同。
 * Q：start() 内部是如何实现的。 Thread.start0() // private native void start0();
 * <p>
 * 二、Runnable 接口
 * Thread 实际是实现的 Runnable 接口
 * Q：Runnable 接口与 Thread 类区别
 * <p>
 * 三、Callable 接口
 * Q：Callable 与 Runnable 的区别。
 * A：Callable 是可取得运行结果的 Runnable
 *
 * @author:jett
 * @date:2021-08-20
 */
public class ThreadTest {
    
    public static int iTime = 10;
    java.util.Random random = new Random(System.currentTimeMillis());
    
    /**
     * 简单的线程类
     */
    class MyThread extends Thread {
        private String title = "";
        
        public MyThread(String title) {
            this.title = title;
        }
        
        @Override
        public void run() {
            for (int i = 0; i < iTime; i++) {
                try {
                    Thread.sleep(random.nextInt(50));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.title + "-->" + i);
            }
        }
    }
    
    /**
     * 如果是简单调用，就只有顺序执行，没有用到多线程
     */
    @Test
    public void justRun() {
        System.out.println("如果是简单调用，就只有顺序执行，没有用到多线程");
        for (int i = 0; i < iTime; i++) {
            MyThread test = new MyThread("新线程" + i);
            test.run();
        }
    }
    
    /**
     * 使用 start() 调用多线程
     */
    @Test
    public void justStart() {
        System.out.println("使用 start() 调用多线程");
        for (int i = 0; i < iTime; i++) {
            MyThread test = new MyThread("新线程" + i);
            test.start();
        }
    }
    
    /**
     * 多次调用 start()，引起错误 IllegalThreadStateException
     */
    @Test(expected = IllegalThreadStateException.class)
    public void repeat() {
        System.out.println("多次调用 start() ");
        MyThread test = new MyThread("线程");
        test.start();
        test.start();
    }
    
    /**
     * 简单的线程类
     */
    class MyRunnable implements Runnable {
        private String title = "";
        
        public MyRunnable(String title) {
            this.title = title;
        }
        
        @Override
        public void run() {
            for (int i = 0; i < iTime; i++) {
                try {
                    Thread.sleep(random.nextInt(50));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.title + "-->" + i);
            }
        }
    }
    
    /**
     * 简单运行实现了 runnable 接口的线程类
     */
    @Test
    public void runnableTest() throws InterruptedException {
        System.out.println("简单运行实现了 runnable 接口的线程类");
        for (int i = 0; i < iTime; i++) {
            MyRunnable run = new MyRunnable("接口实现" + i);
            // 需要放到 Thread 去调用。
            new Thread(run).start();
        }
        // 粗暴地使用休眠，让上面的线程跑完。
        // TODO 优雅的退出
        Thread.sleep(10 * 1000);
    }
    
    /**
     * Callable 是可取得运行结果的 Runnable
     */
    class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            // to do something
            // 做一些资源抢占的事，比如CPU、读取网络数据等
            for (int i = 0; i < iTime; i++) {
                System.out.println("正在做事 " + i);
            }
            return "做完了~";
        }
    }
    
    /**
     * callable 实现
     * callable 实现类，通过 FutureTask 的构造方法输入。
     * futureTask 变量传入 Thread 类，再通过 Thread().start() 启动。
     * 最终通过 task.get() 取得线程处理结果。
     */
    @Test
    public void callable() throws Exception {
        MyCallable call = new MyCallable();
        FutureTask<String> task = new FutureTask<>(call);
        new Thread(task).start();
        // 取得处理结果
        String result = task.get();
        System.out.println(result);
    }
    
}



















