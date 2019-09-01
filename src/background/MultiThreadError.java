package background;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程常见错误 a++操作凭空消失操作
 * mxf
 * 2019年09月01日07:29:46
 */
public class MultiThreadError implements Runnable{
    static MultiThreadError multiThreadError = new MultiThreadError();
    int index = 0;
    final boolean[] marked = new boolean[100000];
    static AtomicInteger realCount = new AtomicInteger(); // 统计真正运行的次数
    static AtomicInteger errorCount = new AtomicInteger(); // 统计错误的次数


    static CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2); // 控制线程的运行 此处有两个线程
    static CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(multiThreadError);
        Thread thread2 = new Thread(multiThreadError);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("index = " + multiThreadError.index);
        System.out.println("realCount = " + realCount.get());
        System.out.println("errorCount = " + errorCount.get());
    }
    @Override
    public void run() {
        marked[0] = true;
        for (int i = 0; i < 10000; i++) {
            try {
                // 等待多个线程同时到达该位置后 再继续执行后续操作
                cyclicBarrier2.reset();
                cyclicBarrier1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            index ++;
            try {
                // 等待两个线程同时完成赋值操作后
                cyclicBarrier1.reset();
                cyclicBarrier2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            realCount.incrementAndGet();
            synchronized (multiThreadError) { // synchronized修饰不光具有原子性还能保证可见性
                if(marked[index] && marked[index - 1]) {
                    System.out.println(index + "冲突了");
                    errorCount.incrementAndGet();
                }
                marked[index] = true;
            }

        }

    }
}
