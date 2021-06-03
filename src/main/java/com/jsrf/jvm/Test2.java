package com.jsrf.jvm;

public class Test2 {
    private static int a = 3;

    static {
        System.out.println("静态初始化块执行了！");
        System.out.println(a);
    }

    public Test2() {
        System.out.println("构造器执行");
    }

    public static void test() {
        System.out.println("静态方法执行");
    }
}