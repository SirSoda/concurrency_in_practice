package threadcoreknowldge.stopthreads.volatiledemo;

/**
 * volatile关键字作用：多个线程能看到属性状态
 * 使用volatile中断线程看似可行
 * mxf
 * 2019年08月18日17:34:45
 */
public class WrongWayVolatile implements Runnable{

    private volatile boolean canceld = false;

    @Override
    public void run() {
        int num = 0;
        try {
            while(num <= 100000 && !canceld) {
                if(num % 100 == 0) {
                    System.out.println(num + "是100的整数倍");
                }
                num++;
                Thread.sleep(10);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        WrongWayVolatile wrongWayVolatile = new WrongWayVolatile();
        Thread thread = new Thread(wrongWayVolatile);
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wrongWayVolatile.canceld = true;
    }
}
