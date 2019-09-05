package singleton;

/**
 * @Author: mxf
 * @Description: 枚举 推荐 线程安全 最好的方式
 * 懒汉模式
 * 还可以防止反序列化重新创建对象
 * @Date: Created at 23:10 2019-09-05
 **/
public enum Singleton8 {
    INSTANCE;
}
