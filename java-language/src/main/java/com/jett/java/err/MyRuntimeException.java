package com.jett.java.err;

public class MyRuntimeException extends RuntimeException {
    
    public static void main(String[] args) {
        MyRuntimeException my = new MyRuntimeException();
        try {
            System.out.println("");
        } catch (Exception e) {
            throw my;
        }
        System.out.println("111");
    
        try {
            MyError myError = new MyError();
        } catch (Exception e) {
            throw e;
        }
        System.out.println("2222");
    
    }


}
