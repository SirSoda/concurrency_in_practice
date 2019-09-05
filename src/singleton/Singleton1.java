package singleton;

/**
 * @Author: mxf
 * @Description: 恶汉模式 静态变量 可用 多线程保证线程安全
 * @Date: Created at 22:41 2019-09-05
 **/
public class Singleton1 {
    private Singleton1(){}

    private static final Singleton1 INSTANCE = new Singleton1();

    public Singleton1 getInstance() {
        return INSTANCE;
    }
}
