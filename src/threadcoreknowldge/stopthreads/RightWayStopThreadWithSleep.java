package threadcoreknowldge.stopthreads;

/**
 * 带有sleep的线程进行中断
 * mxf
 * 2019年08月18日08:00:12
 */
public class RightWayStopThreadWithSleep {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int num = 0;
                while(!Thread.currentThread().isInterrupted() && num <= 300) {
                    if(num % 100 == 0) {
                        System.out.println(num + "是100的整数倍");
                    }
                    num ++;
                }
                System.out.println("执行结束");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();

    }
}
