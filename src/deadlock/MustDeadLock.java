package deadlock;

/**
 * 必然发生死锁
 * mxf
 * 2019年09月24日23:25:02
 */
public class MustDeadLock implements Runnable{

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
        MustDeadLock lock1 = new MustDeadLock();
        lock1.flag = 1;
        MustDeadLock lock2 = new MustDeadLock();
        lock2.flag = 2;

        Thread thread1 = new Thread(lock1);
        Thread thread2 = new Thread(lock2);

        thread1.start();
        thread2.start();
    }
}
