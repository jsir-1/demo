package com.jsrf.test;


import org.springframework.util.StopWatch;

/**
 * Desc:请输入类描述信息
 * ----------------------------------
 *
 * @author:jishirifang@meituan.com
 * @Time:2020/7/6 5:07 下午
 */
public class StopWatchTest {
    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("normaluse");
        Thread.sleep(1000);
        stopWatch.stop();


        stopWatch.start("gooduse");
        Thread.sleep(2000);
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }
}
