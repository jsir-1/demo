package com.jsrf.jvm;

public class DeadLoopClass {
    static {
        if (true) {
            System.out.println(Thread.currentThread() + "init class");
//            while (true) {
//
//            }
        }
    }
}
