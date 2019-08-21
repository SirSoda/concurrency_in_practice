package threadcoreknowldge.threadobjectclasscommonmethods;

/**
 * 使用syn 两个线程交替打印0~100的奇偶数
 * mxf
 * 2019年08月21日22:45:49
 */
public class WaitNotifyPrintOddEvenSyn {

    private static int count = 0;
    private static final Object syn = new Object();

    public static void main(String[] args) {
        new Thread("我是偶数线程") {
            @Override
            public void run() {
                while (count < 100) {
                    synchronized (syn) {
                        if(count % 2 == 0) {
                            System.out.println(Thread.currentThread().getName() + ": " + count);
                            count++;
                        }
                    }
                }
            }
        }.start();

        new Thread("我是奇数线程") {
            @Override
            public void run() {
                while (count < 100) {
                    synchronized (syn) {
                        if(count % 2 == 1) {
                            System.out.println(Thread.currentThread().getName() + ": " + count);
                            count++;
                        }
                    }
                }
            }
        }.start();

    }
}
