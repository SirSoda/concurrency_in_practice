package threadcoreknowldge.threadobjectclasscommonmethods;

import java.util.concurrent.TimeUnit;

/**
 * sleep相应中断
 * sleep()之后线程进入waiting状态，不在占有cpu资源，但是并不会释放锁，直到规定时间后再执行，休眠期间如果被中断，会抛出异常并清除中断信号
 * mxf
 * 2019年08月22日21:11:27
 */
public class SleepInterrupted implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SleepInterrupted sleepInterrupted = new SleepInterrupted();
        Thread thread = new Thread(sleepInterrupted);
        thread.start();
        try {
            Thread.sleep(5500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
