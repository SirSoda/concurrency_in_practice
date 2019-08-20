package threadcoreknowldge.sixstates;

/**
 * 演示 blocked 线程进入syn修饰的方法或者代码块 而又获取不到锁便会blocked
 * waiting 线程调用 wait() join() 方法
 * timed_waiting 线程调用 wait(t) join(t) sleep(t)
 * mxf
 * 2019年08月20日23:01:18
 */
public class BlockedWaitingTimedWaiting implements Runnable{

    public static void main(String[] args) {
        BlockedWaitingTimedWaiting blockedWaitingTimedWaiting = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(blockedWaitingTimedWaiting);
        Thread thread2 = new Thread(blockedWaitingTimedWaiting);

        thread1.start();
        thread2.start();
        // timed_waiting 因为调用了sleep(t)
        System.out.println(thread1.getState());
        // blocked 因为想进入syn修饰的方法，却又拿不到锁
        System.out.println(thread2.getState());
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // waiting 因为调用了wait
        System.out.println(thread1.getState());

    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
