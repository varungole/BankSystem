package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        Account a1 = new Account(5000, 1);
        Account a2 = new Account(6000, 2);

        // Create a thread pool to simulate multiple users
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // Simulate 10 users transferring money between accounts
        for (int i = 0; i < 10; i++) {
            final int userId = i;
            executorService.submit(() -> {
                if (userId % 2 == 0) {
                    Account.transfer(a1, a2, 100);
                } else {
                    Account.transfer(a2, a1, 150);
                }
            });
        }

        // Shutdown the executor service
        executorService.shutdown();

        System.out.println("Account 1 balance is " + a1.getBalance());
        System.out.println("Account 2 balance is " + a2.getBalance());
    }
}