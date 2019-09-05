package jmm;

/**
 * @Author: mxf
 * @Description: 演示可见性
 * 使用volatile关键字强制刷新变量到主存
 * @Date: Created at 2019年09月05日20:29:41
 **/
public class FieldVisibility {

    volatile int a = 1;
    volatile int b = 1;

    public static void main(String[] args) {
        while (true) {

            FieldVisibility fieldVisibility = new FieldVisibility();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    fieldVisibility.change();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    fieldVisibility.print();
                }
            }).start();
        }
    }

    private void print() {
        System.out.println("a = " + a + " b = " + b + " a = " + a);
    }

    private void change() {
        a = 3;
        b = a;
    }

}
