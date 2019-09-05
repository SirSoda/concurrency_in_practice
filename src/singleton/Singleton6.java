package singleton;

/**
 * @Author: mxf
 * @Description: 双重检查 线程安全 推荐 并且属性要用volatile防止创建对象重排序
 * 创建对象 ：
 * 1、创建空对象
 * 2、初始化对象
 * 3、引用指向对象
 * @Date: Created at 22:41 2019-09-05
 **/
public class Singleton6 {
    private Singleton6(){}

    private static volatile Singleton6 INSTANCE;

    public Singleton6 getInstance() {
        if(INSTANCE == null) {
            synchronized (Singleton6.class) {
                if(INSTANCE == null) {
                    INSTANCE = new Singleton6();
                }
            }
        }
        return INSTANCE;
    }
}
