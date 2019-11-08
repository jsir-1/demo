package com.jsrf.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;

public class Test {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Student.class);
        enhancer.setCallback(new MyMethodInterceptor());
        Student student = (Student) enhancer.create();
        student.run();
        student.speak();
        student.sayHello();
    }
}
