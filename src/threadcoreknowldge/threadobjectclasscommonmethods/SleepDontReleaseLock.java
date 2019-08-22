package threadcoreknowldge.threadobjectclasscommonmethods;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * sleep 不会释放Lock
 * mxf
 * 2019年08月22日20:57:26
 */
public class SleepDontReleaseLock implements Runnable{

    private final static Lock lock = new ReentrantLock();
    @Override
    public void run() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "线程获取到了锁");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + "线程释放了锁");
        }

    }

    public static void main(String[] args) {
        SleepDontReleaseLock sleepDontReleaseLock = new SleepDontReleaseLock();
        new Thread(sleepDontReleaseLock).start();
        new Thread(sleepDontReleaseLock).start();
    }
}
