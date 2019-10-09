package deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用try lock 避免死锁
 * mxf
 * 2019年10月08日21:15:42
 */
public class TryLockDeadLock implements Runnable{

    int flag = 1;
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();
    @Override
    public void run() {
        for (int i = 0; i < 100 ; i++) {
            if(flag == 1) {
                try {
                    if(lock1.tryLock(500, TimeUnit.MILLISECONDS)) {
                        System.out.println("线程1获取锁1成功");
                        Thread.sleep(new Random().nextInt(1000));
                        if(lock2.tryLock(500, TimeUnit.MILLISECONDS)) {
                            System.out.println("线程1成功获取到了两把锁");
                            lock2.unlock();
                            lock1.unlock();
                            break;
                        }else {
                            System.out.println("线程1获取锁2失败，已重试");
                            lock1.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    }else {
                        System.out.println("线程1获取锁1失败，已重试");
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    if(lock2.tryLock(2000, TimeUnit.MILLISECONDS)) {
                        System.out.println("线程2获取锁2成功");
                        Thread.sleep(new Random().nextInt(1000));
                        if(lock1.tryLock(2000, TimeUnit.MILLISECONDS)) {
                            System.out.println("线程2成功获取到了两把锁");
                            lock1.unlock();
                            lock2.unlock();
                            break;
                        }else {
                            System.out.println("线程2获取锁1失败，已重试");
                            lock1.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    }else {
                        System.out.println("线程2获取锁2失败，已重试");
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        TryLockDeadLock tryLockDeadLock1 = new TryLockDeadLock();
        tryLockDeadLock1.flag = 1;

        TryLockDeadLock tryLockDeadLock2 = new TryLockDeadLock();
        tryLockDeadLock2.flag = 2;

        new Thread(tryLockDeadLock1).start();
        new Thread(tryLockDeadLock2).start();
    }
}
