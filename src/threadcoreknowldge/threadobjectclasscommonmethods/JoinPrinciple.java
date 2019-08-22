package threadcoreknowldge.threadobjectclasscommonmethods;

import java.util.concurrent.TimeUnit;

/**
 * join 的底层原理 实际调用了wait ，而每个线程执行结束之后都会调用notify方法唤醒wait
 * mxf
 * 2019年08月22日21:25:51
 */
public class JoinPrinciple {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程开始执行");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "子线程执行完毕");
            }
        });

        thread1.start();
        System.out.println("开始等待子线程执行");
//        thread1.join();
        synchronized (thread1) {
            // wait方法当前线程处于阻塞状态 即主线程进入waiting状态 当子线程执行结束后 调用notify方法 唤醒占用thread资源的线程 主线程才被唤醒
            thread1.wait();
        }
        System.out.println("所有子线程执行完毕");

    }
}
