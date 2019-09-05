package jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: mxf
 * @Description: volatile不适用的场景2
 * @Date: Created at 21:57 2019-09-05
 **/
public class UseVolatile2 implements Runnable{
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
        flag = !flag;
    }

    public static void main(String[] args) throws InterruptedException {
        UseVolatile2 useVolatile = new UseVolatile2();
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
