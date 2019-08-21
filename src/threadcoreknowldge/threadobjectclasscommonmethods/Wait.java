package threadcoreknowldge.threadobjectclasscommonmethods;

/**
 * 研究object中的方法wait
 * 1.研究代码执行顺序   2.wait方法会释放锁
 * mxf
 * 2019年08月21日20:42:52
 */
public class Wait {

    public static Object object = new Object();

    static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "获取到了锁");
                // 调用wait此时线程释放锁进入waiting堵塞状态 等待被唤醒 thread2获取锁
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "被唤醒了");
            }
        }
    }

    static class Thread2 extends Thread{
        @Override
        public void run() {
            synchronized (object) {
                // 调用notify 唤醒占有object对象的锁
                object.notify();
                System.out.println(Thread.currentThread().getName() + "执行了notify()");
            }
        }
    }

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();

        thread1.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}
