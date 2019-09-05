package singleton;

/**
 * @Author: mxf
 * @Description: 静态内部类 线程安全 推荐 懒汉模式 静态内部类在类加载时不会进行初始化
 * 创建对象 ：
 * 1、创建空对象
 * 2、初始化对象
 * 3、引用指向对象
 * @Date: Created at 22:41 2019-09-05
 **/
public class Singleton7 {
    private Singleton7(){}

    private static class SingletonInstance {
        private static final Singleton7 INSTANCE = new Singleton7();
    }

    public Singleton7 getInstance() {
        return SingletonInstance.INSTANCE;
    }

}
