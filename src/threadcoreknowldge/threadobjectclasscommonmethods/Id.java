package threadcoreknowldge.threadobjectclasscommonmethods;

import sun.awt.windows.ThemeReader;

/**
 * 线程id id从1开始的，并且逐渐++ 启动jvm之后 我们自己创建的线程id早已不是2
 */
public class Id {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getId());
        System.out.println(new Thread().getId());
    }
}
