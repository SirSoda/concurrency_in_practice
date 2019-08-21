package threadcoreknowldge.threadobjectclasscommonmethods;

/**
 * 证明 wait只释放当前的那把锁
 * mxf
 * 2019年08月21日21:24:09
 */
public class WaitNotifyReleaseOwnMonitor {

    private static Object resourceA = new Object();
    private static Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println("threadA 获取到了 resourceA 的锁");
                    synchronized (resourceB) {
                        System.out.println("threadA 获取到了 resourceB 的锁");
                        try {
                            System.out.println("threadA 释放了 resourceA 的锁");
                            resourceA.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        Thread threadB = new Thread() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println("threadB 获取到了 resourceA 的锁");
                    synchronized (resourceB) {
                        System.out.println("threadB 获取到了 resourceB 的锁");
                    }
                }
            }
        };

        threadA.start();
        Thread.sleep(200);
        threadB.start();
    }
}
