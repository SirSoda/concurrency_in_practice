package singleton;

/**
 * @Author: mxf
 * @Description: 懒汉模式 不可用 线程不安全
 * @Date: Created at 22:41 2019-09-05
 **/
public class Singleton3 {
    private Singleton3(){}

    private static Singleton3 INSTANCE;

    public Singleton3 getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Singleton3();
        }
        return INSTANCE;
    }
}
