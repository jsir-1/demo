package com.jsrf.thread;

/**
 * Desc:请输入类描述信息
 * ----------------------------------
 *
 * @author:jishirifang@meituan.com
 * @Time:2020/7/10 2:56 下午
 */
public class ThreadTest {
    public static void main(String[] args) {
        Thread th = Thread.currentThread();
        Thread thread = new Thread(new Runner(th));
        thread.start();

        while (true) {
            if (th.isInterrupted()) {
                System.out.println("线程打断");
                break;
            }
            // 省略业务代码无数
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("============");
                th.interrupt();
                e.printStackTrace();
            }
        }
    }

    static class Runner implements Runnable {
        Thread th;

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            th.interrupt();
        }

        public Runner(Thread th) {
            this.th = th;
        }
    }
}
