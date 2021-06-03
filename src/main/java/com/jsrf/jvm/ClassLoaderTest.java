package com.jsrf.jvm;

/**
 * Desc:请输入类描述信息
 * ----------------------------------
 *
 * @author:jishirifang@meituan.com
 * @Time:2020/5/20 10:31 上午
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader = DemoTest.class.getClassLoader();

        Class<?> test2 = classLoader.loadClass("com.jsrf.jvm.Test2");
//        test2.newInstance();
//
//        Class.forName("com.jsrf.jvm.Test2", false, classLoader);
//        Test2.test();
//        new Test2();
    }
}
