package com.jett.design.patterns.builder;

public class BuilderTest {
    
    public static void main(String[] args) {
        Sum sum = new Sum();
        Integer result = sum.add(1).add(2).add(9).sum();
        try {
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    
    }
}
