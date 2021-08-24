package com.jett.java8.lambda;

public class LambdaTester04 {
    public static void main(String[] args) {
        // java8 之前
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8");
            }
        }).start();
        
        // java8 之后
        // 拆开写
        Runnable r = () -> System.out.println("拆开写!");
        Thread th = new Thread(r);
        th.start();
        // 更加精简
        new Thread(() -> System.out.println("In Java8, Lambda expression")).start();
    }
}
