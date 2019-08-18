package threadcoreknowldge.stopthreads;

/**
 * 生产环境最佳实践2:恢复中断
 * 在调用的方法中catch住异常之后，调用Thread.currentThread.interrupt进行恢复中断信息
 * mxf
 * 2019年08月18日16:35:28
 */
public class RightWayStopThreadinProd2 implements Runnable{

    @Override
    public void run() {
        while(true) {
            System.out.println("process");
            if(Thread.currentThread().isInterrupted()) {
                System.out.println("处理异常，中断");
                break;
            }
            reInterrupt();

        }
    }

    private void reInterrupt() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RightWayStopThreadinProd2());
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
