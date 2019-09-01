package background;

import java.util.concurrent.TimeUnit;

/**
 * 多线程常见错误 发布对象溢出 还未初始化完成就将对象发布出去
 * mxf
 * 2019年09月01日11:04:44
 */
public class MultiThreadError4 {

    static Point point;

    public static void main(String[] args) throws InterruptedException {
        new Thread(new PointMaker()).start();
//        Thread.sleep(10);
        Thread.sleep(200);
        System.out.println(point);
    }

    static class Point{
        private final int x, y;

        public Point(int x, int y) throws InterruptedException {
            this.x = x;
            MultiThreadError4.point = this;
            Thread.sleep(100);
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static class PointMaker implements Runnable{

        @Override
        public void run() {
            try {
                new Point(1, 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
