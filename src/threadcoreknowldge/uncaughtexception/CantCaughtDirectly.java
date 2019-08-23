package threadcoreknowldge.uncaughtexception;

/**
 * 不能在主线程中直接捕获子线程的异常
 * mxf
 * 2019年08月23日22:44:13
 */
public class CantCaughtDirectly implements Runnable{
    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) throws InterruptedException {
        try {
            new Thread(new CantCaughtDirectly(), "Thread-1").start();
            Thread.sleep(200);

            new Thread(new CantCaughtDirectly(), "Thread-2").start();
            Thread.sleep(200);

            new Thread(new CantCaughtDirectly(), "Thread-3").start();
            Thread.sleep(200);

            new Thread(new CantCaughtDirectly(), "Thread-4").start();
            Thread.sleep(200);

        } catch (Exception e) {
            System.out.println("caught exception");
        }
    }
}
