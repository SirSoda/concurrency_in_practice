package threadcoreknowldge.uncaughtexception;

/**
 * 自定义全局线程异常处理器
 * mxf
 * 2019年08月23日23:01:43
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private String name;

    public MyUncaughtExceptionHandler(String name) {
        this.name = name;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(name + "捕获到了" + t.getName() + "抛出的异常" + e);
    }
}
