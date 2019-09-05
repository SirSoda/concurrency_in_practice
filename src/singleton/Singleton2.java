package singleton;

/**
 * @Author: mxf
 * @Description: 恶汉模式 静态代码块 可用 虚拟机保证多线程安全
 * @Date: Created at 22:41 2019-09-05
 **/
public class Singleton2 {
    private Singleton2(){}

    private static final Singleton2 INSTANCE;

    static {
        INSTANCE = new Singleton2();
    }

    public Singleton2 getInstance() {
        return INSTANCE;
    }
}
