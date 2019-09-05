package jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: mxf
 * @Description: volatile不适用于a++的场合 保证不了原子性
 * @Date: Created at 2019年09月05日21:46:56
 **/
public class NoVolatile implements Runnable{

    volatile int a;
    AtomicInteger atomicInteger = new AtomicInteger();
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            a++;
            atomicInteger.incrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NoVolatile noVolatile = new NoVolatile();
        Thread thread1 = new Thread(noVolatile);
        Thread thread2 = new Thread(noVolatile);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(noVolatile.a);
        System.out.println(noVolatile.atomicInteger.get());

    }
}
