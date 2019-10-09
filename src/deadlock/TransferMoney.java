package deadlock;

import java.util.concurrent.TimeUnit;

/**
 * 模拟银行转账 注释打开会出现死锁
 * mxf
 * 2019年10月06日07:57:13
 */
public class TransferMoney implements Runnable{

    int flag;

    private static Account a = new Account(500);
    private static Account b = new Account(500);
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        TransferMoney t1 = new TransferMoney();
        TransferMoney t2 = new TransferMoney();

        t1.flag = 0;
        t2.flag = 1;
        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("a账户余额=" + a.balance);
        System.out.println("b账户余额=" + b.balance);
    }
    @Override
    public void run() {
        if(flag == 0) {
            transferMoney(a, b, 200);
        }else {
            transferMoney(b, a, 200);
        }
    }

    public static void transferMoney(Account from, Account to, int amount) {

        class Helper {
            public void transfer () {
                if(from.balance - amount >= 0) {
                    from.balance -= amount;
                    to.balance += amount;
                    System.out.println("转账成功");
                }else {
                    System.out.println("账户余额不足，转账失败");
                }
            }
        }

        // 会发生死锁
//        synchronized (from) {
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            };
//            synchronized (to) {
//
//            }
//        }

        // 避免死锁 通过hash值保证获取锁的顺序一致
        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);

        if(fromHash > toHash) {
            synchronized (from) {
                synchronized (to) {
                    new Helper().transfer();
                }
            }
        }else if(fromHash < toHash) {
            synchronized (to) {
                synchronized (from) {
                    new Helper().transfer();
                }
            }
        }else {
            synchronized (lock) {
                synchronized (from) {
                    synchronized (to) {
                        new Helper().transfer();
                    }
                }
            }
        }

    }

    public static class Account{

        public Account(int balance) {
            this.balance = balance;
        }
        int balance;

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }
    }
}
