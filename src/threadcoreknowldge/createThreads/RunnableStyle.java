package threadcoreknowldge.createThreads;

/**
 * 实现runable实现多线程
 * mxf
 * 2019年08月17日08:11:50
 */
public class RunnableStyle implements Runnable {

    public static void main(String[] args) {
        new RunnableStyle().run();
    }
    @Override
    public void run() {
        System.out.println("runable实现多线程");
    }
}
