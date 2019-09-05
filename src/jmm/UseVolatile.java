package jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: mxf
 * @Description: volatile适用的场景 直接赋值
 * @Date: Created at 21:57 2019-09-05
 **/
public class UseVolatile implements Runnable{
    volatile boolean flag;
    AtomicInteger atomicInteger = new AtomicInteger();
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            setFlag();
            atomicInteger.incrementAndGet();
        }
    }

    private void setFlag() {
        flag = true;
    }

    public static void main(String[] args) throws InterruptedException {
        UseVolatile useVolatile = new UseVolatile();
        Thread thread1 = new Thread(useVolatile);
        Thread thread2 = new Thread(useVolatile);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(useVolatile.flag);
        System.out.println(useVolatile.atomicInteger.get());

    }
}
