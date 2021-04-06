package m11_challenge1;

public class BankAccount {
    private double balance;
    private String accountNumber;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public void deposit(double amount){
        balance += amount;
    }

    public void withdraw(double amount){
        balance -= amount;
    }

// synchronizacja metody za pomocą 'this':
//    public void withdraw(double amount){
//        synchronized (this){
//            balance -= amount;
//        }
//    }

    public double getBalance() {
        return balance;
    }

    // poniższe metody nie potrzebują synchronizacji ani locka, bo tylko odczytują dane
    // używamy locka lub synchronizacji tylko w krytycznych częściach kodu!
    public String getAccountNumber() {
        return accountNumber;
    }

    public void printAccountNumber(){
        System.out.println("Account number = " + accountNumber);
    }
}
