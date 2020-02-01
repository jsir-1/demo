package com.jsrf.test;

/**
 * 最佳实践是不再使用的时候手动调用remove方法进行移除，防止内存泄漏
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    threadLocal.set(i + 1);
                    System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
                }
            } finally {
                threadLocal.remove();
            }
        }, "thread1").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    //                threadLocal.set(i + 1);
                    System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
                }
            } finally {
                threadLocal.remove();
            }
        }, "thread2").start();
        Thread thread = new Thread("test");
    }
}
