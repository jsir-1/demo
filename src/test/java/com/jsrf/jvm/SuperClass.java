package com.jsrf.jvm;

public class SuperClass {
    static {
        value = 9;
        System.out.println("SuperClass init");
    }

    public static int value = 10;
    public static final String HELLOWORD = "Hello world";
}


class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init");
    }
}

