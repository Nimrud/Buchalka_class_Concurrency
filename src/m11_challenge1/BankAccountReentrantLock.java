package m11_challenge1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Challenge 3:
// Make BankAccount class threadsafe using ReentrantLock

// Challenge 4:
// Instead of using lock(), use tryLock() with a timeout value of 1 second.
// If the waiting period times out, print "Could not get the lock" to the console

public class BankAccountReentrantLock {
    private double balance;
    private String accountNumber;
    private Lock lock;

    public BankAccountReentrantLock(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.lock = new ReentrantLock();
        // tworzymy tylko 1 locka, aby upewnić się, że tylko 1 operacja będzie wyk. w danej chwili
    }

    // Challenge 4:
    public void deposit(double amount) {
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)){
                try {
                    balance += amount;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Could not get the lock");
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    // Challenge 3:
    public void withdraw(double amount){
        lock.lock();
        try {
            balance -= amount;
        } finally {
            lock.unlock();
        }
    }

    public double getBalance() {
        return balance;
    }

}
