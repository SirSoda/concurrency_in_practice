package threadcoreknowldge.stopthreads;

/**
 * 每个循环中都有sleep或者wait，则不需要再循环中加入判断线程是否被中断的条件
 * mxf
 * 2019年08月18日09:21:47
 */
public class RightWayStopThreadWithSleepEveryLoop {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int num = 0;
                // 注意try catch 再while循环的外边，当抛出异常的时间直接catch住并跳出循环
                try{
                    while(num <10000) {
                        if(num % 100 == 0) {
                            System.out.println(num + "是100的整数倍");
                        }
                        num ++;
                        Thread.sleep(10);
                    }
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();

    }
}
