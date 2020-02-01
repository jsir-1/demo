package com.jsrf.jvm;

import org.openjdk.jol.info.ClassLayout;

public class JVMLearning {
    static DemoTest demoTest;

    public static void main(String[] args) throws InterruptedException {
//        DemoTest jvmObject = new DemoTest();
//        System.out.println(VM.current().details());
//        System.out.println("befor hash");
//        System.out.println(ClassLayout.parseInstance(jvmObject).toPrintable());
//        System.out.println("//计算完hashcode 转为16进制：");
//        System.out.println("jvm hashcode------------0x"+Integer.toHexString(jvmObject.hashCode()));
//        System.out.println("after hash");
//        System.out.println(ClassLayout.parseInstance(jvmObject).toPrintable());

//        Thread.sleep(5000);
        demoTest = new DemoTest();
        System.out.println("befor lock");
        System.out.println(ClassLayout.parseInstance(demoTest).toPrintable());

        //加锁
        sysn();

        System.out.println("after lock");
        System.out.println(ClassLayout.parseInstance(demoTest).toPrintable());
    }

    private static void sysn() {
        synchronized (demoTest) {
            System.out.println("lock ing");
            System.out.println(ClassLayout.parseInstance(demoTest).toPrintable());
        }
    }
}
