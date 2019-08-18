package threadcoreknowldge.startthreads;

/**
 * 不能调用两次start，否则会报错
 * start方法执行过程：1检查线程状态 2加入线程组 3调用start0方法
 * Exception in thread "main" java.lang.IllegalThreadStateException
 * mxf
 * 2019年08月18日06:07:03
 */
public class CantStartTwice {

    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}
