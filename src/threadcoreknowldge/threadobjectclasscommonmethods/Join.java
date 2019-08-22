package threadcoreknowldge.threadobjectclasscommonmethods;

import java.util.concurrent.TimeUnit;

/**
 * 演示join()方法 Thread类的方法 加入线程到主线程 等待子线程执行完毕后执行主线程
 * mxf
 * 2019年08月22日21:25:51
 */
public class Join {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "子线程执行完毕");
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "子线程执行完毕");
            }
        });

        thread1.start();
        thread2.start();
        System.out.println("开始等待子线程执行");
        thread1.join();
        thread2.join();
        System.out.println("所有子线程执行完毕");

    }
}
