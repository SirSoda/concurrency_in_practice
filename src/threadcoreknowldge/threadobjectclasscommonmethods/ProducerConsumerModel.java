package threadcoreknowldge.threadobjectclasscommonmethods;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 使用wait 和 notify 写一个生产者消费者
 * mxf
 * 2019年08月21日21:50:38
 */
public class ProducerConsumerModel {

    static class EvnetStorage{
        private int maxsize;
        private LinkedList<Date> storage;

        EvnetStorage() {
            maxsize = 10;
            storage = new LinkedList<Date>();
        }

        public synchronized void put() {
            while (storage.size() == maxsize) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Date date = new Date();
            storage.add(date);
            System.out.println(date + "加入了仓库,此时仓库有" + storage.size() + "个商品");
            notify();
        }

        public synchronized void get() {
            while (storage.size() == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(storage.poll() + "从仓库取出来了, 此时仓库有" + storage.size() + "个商品");
            notify();
        }
    }

    static class Producer implements Runnable{

        private EvnetStorage evnetStorage;
        Producer(EvnetStorage evnetStorage) {
            this.evnetStorage = evnetStorage;
        }

        @Override
        public void run() {
            for(int i = 0; i < 100; i++) {
                evnetStorage.put();
            }
        }
    }

    static class Consumer implements Runnable{
        private EvnetStorage evnetStorage;
        Consumer(EvnetStorage evnetStorage) {
            this.evnetStorage = evnetStorage;
        }
        @Override
        public void run() {
            for(int i = 0; i < 100; i++) {
                evnetStorage.get();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        EvnetStorage evnetStorage = new EvnetStorage();
        Producer producer = new Producer(evnetStorage);
        Consumer consumer = new Consumer(evnetStorage);

        Thread threadProducer = new Thread(producer);
        Thread threadConsumer = new Thread(consumer);

        threadConsumer.start();
        threadProducer.start();

    }
}
