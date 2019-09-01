package background;

/**
 * 多线程线程安全 演示死锁
 * mxf
 * 2019年09月01日09:57:14
 */
public class MultiThreadError2 implements Runnable{

    int flag = 1;
    static Object object1 = new Object();
    static Object object2 = new Object();

    public static void main(String[] args) {
        MultiThreadError2 runnable1 = new MultiThreadError2();
        MultiThreadError2 runnable2 = new MultiThreadError2();

        runnable1.flag = 1;
        runnable2.flag = 2;

        new Thread(runnable1).start();
        new Thread(runnable2).start();
    }

    @Override
    public void run() {
        System.out.println("currentThread = " + Thread.currentThread().getName() + " flag = " + flag);
        if(flag == 1) {
            synchronized (object1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2) {
                    System.out.println("currentThread = " + Thread.currentThread().getName() + " flag = 1");
                }
            }
        }else {
            synchronized (object2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object1) {
                    System.out.println("currentThread = " + Thread.currentThread().getName() + " flag = 2");
                }
            }
        }

    }
}
