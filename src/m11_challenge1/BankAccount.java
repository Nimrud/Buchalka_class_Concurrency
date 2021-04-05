package m11_challenge1;

import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private double balance;
    private String accountNumber;
    private ReentrantLock lock;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public void deposit(double amount, ReentrantLock lock){
        lock.lock();
        try{
            balance += amount;
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(double amount, ReentrantLock lock){
        lock.lock();
        try{
            balance -= amount;
        } finally {
            lock.unlock();
        }
    }

    public double getBalance() {
        return balance;
    }
}
