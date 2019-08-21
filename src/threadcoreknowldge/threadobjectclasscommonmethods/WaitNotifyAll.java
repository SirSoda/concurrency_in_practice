package threadcoreknowldge.threadobjectclasscommonmethods;

/**
 * 演示notify 和 notifyall
 * notify会随机唤醒一个占用资源锁的线程 而notifyall会唤醒所有阻塞的等待资源锁的线程
 * start先启动并不一定线程先启动
 * mxf
 * 2019年08月21日21:02:33
 */
public class WaitNotifyAll implements Runnable{

    private static Object object = new Object();

    @Override
    public void run() {
        synchronized (object) {
            System.out.println(Thread.currentThread().getName() + "获取到了锁");
            try {
                System.out.println(Thread.currentThread().getName() + "即将调用wait 进入堵塞状态");
                object.wait();
                System.out.println(Thread.currentThread().getName() + "被唤醒了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    object.notifyAll(); // 唤醒所有资源对象所堵塞的线程
//                    object.notify(); // 唤醒某一个资源堵塞的线程
                    System.out.println(Thread.currentThread().getName() + "唤醒了线程");
                }

            }
        });

        WaitNotifyAll waitNotifyAll = new WaitNotifyAll();
        Thread thread1 = new Thread(waitNotifyAll);
        Thread thread2 = new Thread(waitNotifyAll);

        thread1.start();
        thread2.start();
        Thread.sleep(200);
        thread3.start();
    }
}
