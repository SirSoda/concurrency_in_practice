package jmm;

import java.util.concurrent.CountDownLatch;

/**
 * 演示指令重排序
 * 重排序的3种情况：
 * 1、jvm优化
 * 2、指令重排序，cpu重排序
 * 3、"内存重排"
 * mxf
 * 2019年09月05日19:42:15
 */
public class OutOfOrderExecution {

    private static int x, y, a, b;

    public static void main(String[] args) throws InterruptedException {
        int index = 0;
        for (; ;) {
            x = y = a = b = 0;
            index ++;
            CountDownLatch latch = new CountDownLatch(1);
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    a = 1;
                    x = b;
                }
            });

            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    b = 1;
                    y = a;
                }
            });

            thread1.start();
            thread2.start();
            latch.countDown();
            thread1.join();
            thread2.join();
            if(x == 0 && y == 0) {
                System.out.println("第" + index + "次 ：x = " + x + ", y = " + y);
                break;
            }else {
                System.out.println("第" + index + "次 ：x = " + x + ", y = " + y);
            }

        }
    }
}
