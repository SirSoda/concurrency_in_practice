package threadcoreknowldge.startthreads;

/**
 * 比较start和run方法
 * start方法通知jvm空闲的时间执行这个线程，并不一定马上执行，要等调度空闲的时间才会被调用
 * run只是调用了run方法，而start开启了线程整个生命周期
 * mxf
 * 2019年08月18日05:54:17
 */
public class StartAndRunMethod {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        runnable.run();

        new Thread(runnable).start();

    }

}
