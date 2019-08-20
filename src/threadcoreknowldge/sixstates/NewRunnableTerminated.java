package threadcoreknowldge.sixstates;

/**
 * 展示线程的new runnable terminated 三种状态
 * mxf
 * 2019年08月20日22:45:34
 */
public class NewRunnableTerminated implements Runnable {

    public static void main(String[] args) {
        Thread thread = new Thread(new NewRunnableTerminated());
        // NEW
        System.out.println(thread.getState());
        thread.start();
        // RUNUABLE
        System.out.println(thread.getState());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // RUNNABLE
        System.out.println(thread.getState());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // TERMINATED
        System.out.println(thread.getState());

    }
    @Override
    public void run() {
        for(int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}
