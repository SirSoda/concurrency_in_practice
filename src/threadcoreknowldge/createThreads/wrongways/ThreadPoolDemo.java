package threadcoreknowldge.createThreads.wrongways;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池创建多线程
 * mxf
 * 2019年08月17日15:40:17
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i < 1000; i++) {
            executorService.submit(new Task(){});
        }
    }

    static class Task implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }
}
