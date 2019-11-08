package com.jsrf.proxy.cglib;

public class Student extends Father implements Person {
    @Override
    public void run() {
        System.out.println("running");

    }

    @Override
    public void speak() {
        System.out.println("speaking");
    }
}
