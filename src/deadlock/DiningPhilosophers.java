package deadlock;

/**
 * 演示科学家吃饭死锁
 * mxf
 * 2019年10月08日20:33:12
 */
public class DiningPhilosophers {

    public static class Philosopher implements Runnable {

        private Object leftChopstick;
        private Object rightChopstick;

        public Object getLeftChopstick() {
            return leftChopstick;
        }

        public void setLeftChopstick(Object leftChopstick) {
            this.leftChopstick = leftChopstick;
        }

        public Object getRightChopstick() {
            return rightChopstick;
        }

        public void setRightChopstick(Object rightChopstick) {
            this.rightChopstick = rightChopstick;
        }

        public Philosopher(Object leftChopstick, Object rightChopstick) {
            this.leftChopstick = leftChopstick;
            this.rightChopstick = rightChopstick;
        }

        @Override
        public void run() {
            while (true) {
                doAction("thinking...");
                synchronized (leftChopstick) {
                    doAction("pick up left chopStick");
                    synchronized (rightChopstick) {
                        doAction("pick up right chopStick");
                        doAction("eating...");
                        doAction("put down right chopStick");
                    }
                    doAction("put down left chopStick");
                }
            }
        }

        private void doAction(String action) {
            System.out.println(Thread.currentThread().getName() +" " + action);
            try {
                Thread.sleep((long) (Math.random() * 10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Philosopher[] philosophers = new Philosopher[5];
        Object[] chopSticks = new Object[5];
        for (int i = 0; i < chopSticks.length; i++) {
            chopSticks[i] = new Object();
        }
        for (int i = 0; i < philosophers.length; i++) {
            Object leftChopstick = chopSticks[i];
            Object rightChopstick = chopSticks[(i + 1) % chopSticks.length];
            if(i == philosophers.length - 1) {
                philosophers[i] = new Philosopher(rightChopstick, leftChopstick);
            } else {
                philosophers[i] = new Philosopher(leftChopstick, rightChopstick);
            }


            new Thread(philosophers[i], "哲学家" + (i + 1)).start();
        }
    }
}
