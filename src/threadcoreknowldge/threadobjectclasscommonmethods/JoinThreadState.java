package threadcoreknowldge.threadobjectclasscommonmethods;

/**
 * join 使得主线程进入waiting状态
 * mxf
 * 2019年08月22日21:52:18
 */
public class JoinThreadState {

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println("主线程的状态：" + mainThread.getState());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程执行结束");
    }
}
