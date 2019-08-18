package threadcoreknowldge.stopthreads.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 使用interrupt唤醒线程并终止
 * mxf
 * 2019年08月18日18:02:39
 */
public class WrongWayVolatileCantStopFixed {

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

        producerThread.interrupt(); // 使用interrupt终止线程
        System.out.println("消费者不需要更多了");

    }

    static class Producer implements Runnable{
        private BlockingQueue blockingQueue;

        public Producer(BlockingQueue blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            int num = 0;
            try {
                while(num <= 10000 && !Thread.currentThread().isInterrupted()) {
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
