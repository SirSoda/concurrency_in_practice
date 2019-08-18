package threadcoreknowldge.createThreads.wrongways;

/**
 * 匿名内部类实现多线程
 * mxf
 * 2019年08月17日15:53:08
 */
public class AnnoymousInnerClassDemo {

    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
    }
}
