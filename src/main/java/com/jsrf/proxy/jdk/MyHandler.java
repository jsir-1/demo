package com.jsrf.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 保存下来生成的代理类的 Class 文件
 * -Dsun.misc.ProxyGenerator.saveGeneratedFiles=true
 */
public class MyHandler implements InvocationHandler {
    private Object realObj;

    public MyHandler(Object realObj) {
        this.realObj = realObj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy begin......");
        Object result = method.invoke(realObj, args);
        System.out.println("proxy end......");
        return result;
    }

    public static void main(String[] args) {
        RealClass realClass = new RealClass();
        MyHandler handler = new MyHandler(realClass);
        IService obj = (IService) Proxy.newProxyInstance(RealClass.class.getClassLoader(), new Class[]{IService.class}, handler);
        obj.sayHello();

    }
}
