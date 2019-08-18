package threadcoreknowldge.stopthreads;

/**
 * isInterrupted是作用于调用线程的
 * interrupted是静态方法无论是对象调用还是类调用都是作用于主线程的
 * mxf
 * 2019年08月18日20:23:35
 */
public class RightWayInterrupted {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;) {

                }
            }
        });

        // 启动线程
        thread.start();
        // 中断线程
        thread.interrupt();
        // 获取中断标志
        System.out.println("thread 调用 isInterrupted=" + thread.isInterrupted()); // true 获取中断标志 不清除中断标志
        System.out.println("Thread 调用 interrupted=" + thread.interrupted()); // false 作用于主线程
        System.out.println("Thread 调用 interrupted=" + Thread.interrupted()); // false 作用于主线程
        System.out.println("thread 调用 isInterrupted=" + thread.isInterrupted()); // 由于上面没有清除中断标志 因此此处还是中断状态

    }
}
