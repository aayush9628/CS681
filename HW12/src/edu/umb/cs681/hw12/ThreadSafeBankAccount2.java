package edu.umb.cs681.hw12;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount2 implements BankAccount{
    private double balance = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition sufficientFundsCondition = lock.newCondition();
    private Condition belowUpperLimitFundsCondition = lock.newCondition();

    public void deposit(double amount){
        lock.lock();
        try{
            System.out.println("Lock obtained");
            System.out.println(Thread.currentThread().getId() +
                    " (d): current balance: " + balance);
            while(balance >= 300){
                System.out.println(Thread.currentThread().getId() +
                        " (d): await(): Balance exceeds the upper limit.");
                belowUpperLimitFundsCondition.await();
            }
            balance += amount;
            System.out.println(Thread.currentThread().getId() +
                    " (d): new balance: " + balance);
            sufficientFundsCondition.signalAll();
        }
        catch (InterruptedException exception){
            exception.printStackTrace();
        }
        finally{
            lock.unlock();
            System.out.println("Lock released");
        }
    }

    public void withdraw(double amount){
        lock.lock();
        try{
            System.out.println("Lock obtained");
            System.out.println(Thread.currentThread().getId() +
                    " (w): current balance: " + balance);
            while(balance <= 0){
                System.out.println(Thread.currentThread().getId() +
                        " (w): await(): Insufficient funds");
                sufficientFundsCondition.await();
            }
            balance -= amount;
            System.out.println(Thread.currentThread().getId() +
                    " (w): new balance: " + balance);
            belowUpperLimitFundsCondition.signalAll();
        }
        catch (InterruptedException exception){
            exception.printStackTrace();
        }
        finally{
            lock.unlock();
            System.out.println("Lock released");
        }
    }

    public double getBalance() { return this.balance; }

    public static void main(String[] args){
        int instances = 5;
        ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
        Thread threadsForDeposit[] = new Thread[instances];
        DepositRunnable depositRunnable[] = new DepositRunnable[instances];
        Thread threadsForWithdrawal[] = new Thread[instances];
        WithdrawRunnable withdrawRunnable[] = new WithdrawRunnable[instances];
        for(int i = 0; i < instances; i++){
            depositRunnable[i] = new DepositRunnable(bankAccount);
            threadsForDeposit[i] = new Thread(depositRunnable[i]);
            withdrawRunnable[i] = new WithdrawRunnable(bankAccount);
            threadsForWithdrawal[i] = new Thread(withdrawRunnable[i]);
        }
        for(int i = 0; i < instances; i++){
            threadsForDeposit[i].start();
            threadsForWithdrawal[i].start();
        }

        try {
            Thread.sleep(3000); // main thread sleeps for 3 secs
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < instances; i++){
            depositRunnable[i].setDone();
            withdrawRunnable[i].setDone();
        }
        for(int i = 0; i < instances; i++){
            threadsForDeposit[i].interrupt();
            threadsForWithdrawal[i].interrupt();
        }

        try {
            for(int i = 0; i < instances; i++){
                threadsForDeposit[i].join();
                threadsForWithdrawal[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

