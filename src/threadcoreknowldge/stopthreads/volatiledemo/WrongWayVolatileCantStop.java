package threadcoreknowldge.stopthreads.volatiledemo;

import sun.jvm.hotspot.debugger.ThreadAccess;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 演示在生产者消费者模式下使用volatile无法终止线程 这是由于当消费者线程终止时 生产者处于阻塞状态 而volatile无法唤醒线程
 * mxf
 * 2019年08月18日18:02:39
 */
public class WrongWayVolatileCantStop {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(10);
        Producer producer = new Producer(blockingQueue);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        Consumer consumer = new Consumer(blockingQueue);
        while(consumer.needMore()) {
            System.out.println(consumer.blockingQueue.take() + "被消费了");
            Thread.sleep(100);
        }

        producer.canceld = true;
        System.out.println("消费者不需要更多了");

    }

    static class Producer implements Runnable{
        private volatile boolean canceld = false;
        private BlockingQueue blockingQueue;

        public Producer(BlockingQueue blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            int num = 0;
            try {
                while(num <= 10000 && !canceld) {
                    if(num % 100 == 0) {
                        // 当队列满了之后会阻塞队列 造成后续volatile关键字无法唤醒队列
                        blockingQueue.put(num);
                        System.out.println(num + "被放入生产者了");
                    }
                    num++;
                }
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                System.out.println("生产结束");
            }

        }
    }

    static class Consumer{

        BlockingQueue blockingQueue;

        public Consumer(BlockingQueue blockingQueue) {
            this.blockingQueue = blockingQueue;
        }
        public boolean needMore() {
            if(Math.random() > 0.95) {
                return false;
            }
            return true;
        }
    }
}
