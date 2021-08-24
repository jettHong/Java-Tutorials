package com.jett.java8.method_references;

@FunctionalInterface
public interface Supplier<T> {
    T get();
}

class Car {
    // Supplier 是 jdk1.8 的接口，这里和 Lambda 一起使用了
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }
 
    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }
 
    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }
 
    public void repair() {
        System.out.println("Repaired " + this);
    }
}
