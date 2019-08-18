package threadcoreknowldge.createThreads;

/**
 * 同时使用runnable 和 thread
 * mxf
 * 2019年08月17日15:18:36
 */
public class BothRunnableThread {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我来自runnble");
            }
        }){
            @Override
            public void run() {
                System.out.println("我来自thread");
            }
        }.start();
    }
}
