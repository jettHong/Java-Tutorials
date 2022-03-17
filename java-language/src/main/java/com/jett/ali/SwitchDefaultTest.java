package com.jett.ali;

/**
 * 猜一猜，打印出来是什么？
 */
public class SwitchDefaultTest {
    static int i;
    
    public static void main(String args[]) {
        int i = 9;
        switch (i) {
            default:
                System.out.println("default");
            case 0:
                System.out.println("zero");
                break;
            case 1:
                System.out.println("one");
            case 2:
                System.out.println("two");
        }
        
        System.out.println("----------------");
        
        switch (i) {
            case 0:
                System.out.println("zero");
                break;
            case 1:
                System.out.println("one");
            case 2:
                System.out.println("two");
            default:
                System.out.println("default");
                break;
        }
    }
    
}


