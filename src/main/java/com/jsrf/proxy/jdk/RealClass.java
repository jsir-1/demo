package com.jsrf.proxy.jdk;

public class RealClass extends ProxyTest implements IService {
    @Override
    public void sayHello() {
        System.out.println("Hello world");
    }

    /**
     * 无法被代理
     */
    public void sayHi(){
        System.out.println("hi");
    }
}
