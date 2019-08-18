package threadcoreknowldge.createThreads;

/**
 * 创建多线程--继承thread
 * mxf
 * 2019年08月17日08:12:04
 */
public class ThreadStyle extends Thread{

    public static void main(String[] args) {
        new ThreadStyle().start();
    }

    @Override
    public void run() {
        System.out.println("继承thread实现多线程");
    }
}
