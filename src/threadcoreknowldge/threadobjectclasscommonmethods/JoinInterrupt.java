package threadcoreknowldge.threadobjectclasscommonmethods;

import java.util.concurrent.TimeUnit;

/**
 * 子线程join期间 等待的是主线程 所以中断抛出异常的是主线程
 * mxf
 * 2019年08月22日21:36:52
 */
public class JoinInterrupt {

    public static void main(String[] args) {
        // 获取当前主线程
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    mainThread.interrupt();
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程运行完毕");
            }
        };

        thread.start();
        try {
            // 子线程加入 主线程进入阻塞状态 此时对主线程进行中断 抛出异常
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(Thread.currentThread().getName() + "线程被中断");
            // 传递中断
            thread.interrupt();
        }
        System.out.println("主线程运行结束了");

    }
}
