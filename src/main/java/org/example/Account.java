package org.example;

public class Account {

    private int balance;
    private int accountId;

    public Account(int balance, int accountId) {
        this.balance = balance;
        this.accountId = accountId;
    }

    public synchronized void depositMoney(int amount) {
        balance += amount;
        System.out.println("Deposited " + amount + " worth of money. Total balance is " + balance + " in account ID " + accountId);
    }

    public synchronized void withdrawMoney(int amount) {
       if(amount > balance) {
           System.out.println("You do not have enough balance in account " + accountId);
           return;
       }

       balance -=amount;
        System.out.println("Withdrew " + amount + " worth of money. Total balance is " + balance + " in account " + accountId);
    }

    public synchronized int getBalance() {
        return balance;
    }

    public synchronized int getAccountId() {
        return accountId;
    }

    public static void transfer(Account from, Account to, int amount) {
        synchronized (from) {
            synchronized (to) {
                if(from.getBalance() >= amount) {
                    from.withdrawMoney(amount);
                    to.depositMoney(amount);
                    System.out.println("Deposited amount " + amount + " from " + from.accountId + " to " + to.getAccountId());
                } else {
                    System.out.println("Transfer failed. Insufficient funds in account " + from.getAccountId());
                }
            }
        }
    }
}
