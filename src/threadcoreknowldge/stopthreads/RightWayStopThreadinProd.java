package threadcoreknowldge.stopthreads;

/**
 * 生产环境最佳实践1:传递中断
 * catch了InterruptedException优先选择在方法签名中抛出异常，以便在run方法中强制进行try catch
 * mxf
 * 2019年08月18日16:35:28
 */
public class RightWayStopThreadinProd implements Runnable{

    @Override
    public void run() {
        while(true) {
            System.out.println("process");
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                System.out.println("处理异常");
                e.printStackTrace();
            }
        }
    }

    private void throwInMethod() throws InterruptedException {
        Thread.sleep(2000);
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RightWayStopThreadinProd());
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
