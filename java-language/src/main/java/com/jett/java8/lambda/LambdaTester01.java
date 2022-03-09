package com.jett.java8.lambda;

/**
 * Lambda 比对旧接口
 * 1、使用接口实现类
 * 2、使用匿名类
 * 3、使用Lambda
 */
public class LambdaTester01 {
    
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(10 * 1000);
        // 1、使用接口实现类
        GreetingService interfaceImpl = new MyGreetingServiceImpl();
        interfaceImpl.sayMessage("使用接口实现类");
        
        // 2、使用匿名类，如果使用 IDEA 工具甚至会提醒你简化成 Lambda 表达式。
        GreetingService anonymousImpl = new GreetingService() {
            @Override
            public void sayMessage(String message) {
                System.out.println("Anonymous:" + message);
            }
        };
        anonymousImpl.sayMessage("使用匿名类");
        
        
        // 3、使用 Lambda
        GreetingService greetService1 = message -> System.out.println("Lambda:" + message);
        greetService1.sayMessage("使用Lambda");
    }
    
    static class MyGreetingServiceImpl implements GreetingService {
        @Override
        public void sayMessage(String message) {
            System.out.println("implements:" + message);
        }
    }
    
    /**
     * 问候接口
     */
    interface GreetingService {
        void sayMessage(String message);
    }
}
