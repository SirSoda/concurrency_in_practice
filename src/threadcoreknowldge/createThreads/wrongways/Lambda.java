package threadcoreknowldge.createThreads.wrongways;

/**
 * 使用lambda表达式创建多线程
 * mxf
 * 2019年08月17日15:34:49
 */
public class Lambda {

    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}
