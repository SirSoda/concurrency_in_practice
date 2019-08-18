package threadcoreknowldge.stopthreads;

/**
 * 中断线程没有sleep
 * interrupt只是发送中断信号 中不中断还是线程自己说了算！
 * mxf
 * 2019年08月18日07:36:27
 */
public class RightWayStopThreadWithoutSleep implements Runnable{

    @Override
    public void run() {
        int num = 0;
        while (!Thread.currentThread().isInterrupted() && num < Integer.MAX_VALUE) {
            if(num % 10000 == 0) {
                System.out.println(num + "是10000的整数倍");
            }
            num ++;
        }
        System.out.println("执行结束");
    }

    public static void main(String[] args) throws InterruptedException {
        RightWayStopThreadWithoutSleep rightWayStopThreadWithoutSleep = new RightWayStopThreadWithoutSleep();
        Thread thread = new Thread(rightWayStopThreadWithoutSleep);
        thread.start();
        Thread.sleep(1000);
        thread.interrupt(); // 中断
    }
}
