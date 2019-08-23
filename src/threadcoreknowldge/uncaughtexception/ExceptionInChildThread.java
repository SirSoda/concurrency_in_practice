package threadcoreknowldge.uncaughtexception;

/**
 * 子线程中的异常 并不会阻止主线程的运行
 * mxf
 * 2019年08月23日22:41:14
 */
public class ExceptionInChildThread implements Runnable{
    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        new Thread(new ExceptionInChildThread()).start();
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
    }
}
