package threadcoreknowldge.uncaughtexception;

/**
 * 使用自定义线程处理器处理未捕获异常
 * mxf
 * 2019年08月23日23:05:03
 */
public class UseMyOwnUncaughtExceptionHandler implements Runnable{
    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) throws InterruptedException {
        MyUncaughtExceptionHandler myUncaughtExceptionHandler = new MyUncaughtExceptionHandler("私有异常处理器");
        Thread.setDefaultUncaughtExceptionHandler(myUncaughtExceptionHandler);

        new Thread(new UseMyOwnUncaughtExceptionHandler()).start();
        Thread.sleep(200);

        new Thread(new UseMyOwnUncaughtExceptionHandler()).start();
        Thread.sleep(200);

        new Thread(new UseMyOwnUncaughtExceptionHandler()).start();
        Thread.sleep(200);

        new Thread(new UseMyOwnUncaughtExceptionHandler()).start();
        Thread.sleep(200);
    }
}
