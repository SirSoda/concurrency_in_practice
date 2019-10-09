package deadlock;

import java.util.Random;

/**
 * 牛郎织女演示活锁
 * 加入随机因子解决活锁
 * mxf
 * 2019年10月09日19:37:12
 */
public class LiveLock {

    static class Spoon{
        private Diner diner;

        public Spoon(Diner diner) {
            this.diner = diner;
        }

        public synchronized void use() {
            System.out.printf("%s has eaten!", diner.getName());
        }
    }

    static class Diner{
        private String name;
        private boolean isHungry;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isHungry() {
            return isHungry;
        }

        public void setHungry(boolean hungry) {
            isHungry = hungry;
        }

        public Diner(String name, boolean isHungry) {
            this.name = name;
            this.isHungry = isHungry;
        }

        public void eatWith(Spoon spoon, Diner diner) {
            Random random = new Random();
            while(isHungry) {
                if(spoon.diner != this) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                Integer factor = random.nextInt(10);
                if(diner.isHungry && factor < 9) {
                    System.out.println(this.name + " : 你先吃吧" + diner.getName());
                    spoon.diner = diner;
                    continue;
                }
                spoon.use();
                isHungry = false;
                System.out.println(name + " : 我吃饱了");
                spoon.diner = diner;
            }
        }


    }
    public static void main(String[] args) {
        Diner husband = new Diner("牛郎", true);
        Diner wife = new Diner("织女", true);

        Spoon spoon = new Spoon(husband);
        new Thread(new Runnable() {
            @Override
            public void run() {
                husband.eatWith(spoon, wife);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                wife.eatWith(spoon, husband);
            }
        }).start();
    }
}
