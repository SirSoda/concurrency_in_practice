package singleton;

/**
 * @Author: mxf
 * @Description: 懒汉模式 线程安全 不推荐 多个线程无法并行 效率低下
 * @Date: Created at 22:41 2019-09-05
 **/
public class Singleton4 {
    private Singleton4(){}

    private static Singleton4 INSTANCE;

    public synchronized Singleton4 getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Singleton4();
        }
        return INSTANCE;
    }
}
