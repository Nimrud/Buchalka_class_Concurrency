package m11_challenge1;

/*
Two people are using a joint bank account at the same time.
Create and start threads that use the same BankAccount instance and an initial balance of $1000.00.
One will deposit $300.00 into the bank account and then withdraw $50.00.
The other will deposit $203.75 and then withdraw $100.00.
 */

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("12345-678", 1000.00);
        ReentrantLock lock = new ReentrantLock();

        Thread person1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try{
                    account.deposit(300);
                    System.out.println("Balance (after op1): " + account.getBalance());
                    account.withdraw(50);
                    System.out.println("Balance (after op2): " + account.getBalance());
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread person2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try{
                    account.deposit(203.75);
                    System.out.println("Balance (after op3): " + account.getBalance());
                    account.withdraw(100);
                    System.out.println("Balance (after op4): " + account.getBalance());
                } finally {
                  lock.unlock();
                }
            }
        });

        person1.start();
        person2.start();

        //System.out.println("Total balance: " + account.getBalance());
    }
}
