package deadlock;

import java.nio.channels.AcceptPendingException;
import java.util.Random;

/**
 * 多账户转账
 * mxf
 * 2019年10月06日08:30:15
 */
public class MultiTransferMoney {

    private static final int NUM_ACCOUNTS = 500;
    private static final int NUM_AMOUNT = 1000;
    private static final int NUM_THREADS = 20;
    private static final int NUM_ITERATIONS = 1000000;


    public static void main(String[] args) {
        TransferMoney.Account accounts[] = new TransferMoney.Account[NUM_ACCOUNTS];
        Random random = new Random();
        for (int i = 0; i < NUM_ACCOUNTS; i++) {
            accounts[i] = new TransferMoney.Account(NUM_AMOUNT);
        }
        class TransferThread extends Thread {

            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS ; i++) {
                    TransferMoney.Account from = accounts[random.nextInt(NUM_ACCOUNTS)];
                    TransferMoney.Account to = accounts[random.nextInt(NUM_ACCOUNTS)];
                    int amount = random.nextInt(NUM_AMOUNT);
                    TransferMoney.transferMoney(from, to, amount);
                }

            }
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            new TransferThread().start();
        }
    }
}
