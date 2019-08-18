package threadcoreknowldge.stopthreads;

/**
 * 错误中断线程的方法 ：使用stop 会造成程序运行一半突然停止，没办法完成一个基本单位的操作，造成脏数据
 * 另外suspend和resume中断的方式会带着锁，有可能会造成死锁
 * 所以这三种中断方式都已经被弃用了
 *
 * 这个demo模拟新起一个线程模拟军队发放武器，当使用stop后一部分军人拿到了武器，另一部分没有武器
 * mxf
 * 2019年08月18日17:16:22
 */
public class StopThread implements Runnable{

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println("连队" + i + "开始领取武器");
            for(int j = 0; j < 10; j++) {
                System.out.println(j);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("连队" + i + "领取结束");
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new StopThread());
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.stop();
    }
}
