package singleton;

/**
 * @Author: mxf
 * @Description: 懒汉模式 线程不安全
 * @Date: Created at 22:41 2019-09-05
 **/
public class Singleton5 {
    private Singleton5(){}

    private static Singleton5 INSTANCE;

    public Singleton5 getInstance() {
        if(INSTANCE == null) {
            synchronized (Singleton5.class) {
                INSTANCE = new Singleton5();
            }
        }
        return INSTANCE;
    }
}
