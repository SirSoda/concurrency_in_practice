package threadcoreknowldge.threadobjectclasscommonmethods;

/**
 * 使用wait 和 notify 创建两个线程交替打印奇偶数
 * mxf
 * 2019年08月21日22:55:35
 */
public class WaitNotifyPrintOddEven {

    private final static Object lock = new Object();
    private static int count = 0;

    static class TurningRunner implements Runnable{

        @Override
        public void run() {
            while (count <= 100) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + ":" + count);
                    count ++;
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread threadEven = new Thread(new TurningRunner(), "我是偶数线程");
        Thread threadOdd = new Thread(new TurningRunner(), "我是奇数线程");

        threadEven.start();
        threadOdd.start();
    }


}
