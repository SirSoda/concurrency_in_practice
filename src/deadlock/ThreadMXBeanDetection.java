package deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;

/**
 * ThreadMXBean检测死锁
 * mxf
 * 2019年09月24日23:25:02
 */
public class ThreadMXBeanDetection implements Runnable{

    private int flag;
    private static Object o1 = new Object();
    private static Object o2 = new Object();


    @Override
    public void run() {
        if(flag == 1) {
            synchronized (o1) {
                System.out.println("线程1拿到了锁o1");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.printf("线程1拿到了两把锁");
                }
            }
        }else {
            synchronized (o2) {
                System.out.println("线程1拿到了锁o2");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.printf("线程2拿到了两把锁");
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadMXBeanDetection lock1 = new ThreadMXBeanDetection();
        lock1.flag = 1;
        ThreadMXBeanDetection lock2 = new ThreadMXBeanDetection();
        lock2.flag = 2;

        Thread thread1 = new Thread(lock1);
        Thread thread2 = new Thread(lock2);

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        thread1.start();
        thread2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long[] deadLockedThreads = threadMXBean.findDeadlockedThreads();
        if(deadLockedThreads != null && deadLockedThreads.length > 0) {
            for (int i = 0; i < deadLockedThreads.length; i++) {
                ThreadInfo threadInfo = threadMXBean.getThreadInfo(deadLockedThreads[i]);
                System.out.println("发现死锁 " + threadInfo.getThreadName());
            }
        }

    }
}
