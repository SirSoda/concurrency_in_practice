package threadcoreknowldge.createThreads.wrongways;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器创建多线程
 * mxf
 * 2019年08月17日15:49:35
 */
public class TimerTaskDemo {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, 1000, 1000);
    }
}
