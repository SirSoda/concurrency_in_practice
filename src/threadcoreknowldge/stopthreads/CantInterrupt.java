package threadcoreknowldge.stopthreads;

/**
 * 当在循环里面进行try catch住异常的时间，无论是循环前判断线程状态与否，都无法中断线程
 * mxf
 * 2019年08月18日09:39:08
 */
public class CantInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            while(num <= 10000 && !Thread.currentThread().isInterrupted()) {
                if(num % 100 == 0) {
                    System.out.println(num + "是100的整数倍");
                }
                num ++;
                // 注意 catch放在了循环内 并不能终止循环 即使循环前做了线程的状态判断也无法终止
                // 这是由于sleep设计理念 当sleep被中断便会把线程的中断标志位给清除 所以线程并没有被中断
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
