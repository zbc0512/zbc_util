package io.zbc.util.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试环境最多能创建多少个线程
 * 
 * @author Z
 *
 */
public class TestThread extends Thread {

    private static final AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) {
        while (true) {
            (new TestThread()).start();
        }
    }

    @Override
    public void run() {
        System.out.println(count.incrementAndGet());
        while (true) {
            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

}