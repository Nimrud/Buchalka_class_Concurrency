package m11_challenge1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

    public void deposit(double amount){
        lock.lock();
        try{
            balance += amount;
        } finally {
            lock.unlock();
        }
    }

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
