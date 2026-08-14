package model;
import exception.InsufficientBalanceException;

import java.io.Serializable;

public class Account implements Serializable {

    private long accountNumber;
    private String holderName;
    private int pin;
    private double balance;

    public double getBalance()
    {
        return balance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public int getPin() {
        return pin;
    }

    public Account(long accountNumber, String holderName, int pin, double balance)
    {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.pin = pin;
        this.balance = balance;
    }

    public double deposit(double money)
    {
        if(money<=0){
            throw new IllegalArgumentException("Please enter a valid amount");
        }
        balance+=money;
        return balance;
    }

    public double withdraw(double money)
    {
        if(money<=0){
            throw new IllegalArgumentException("Please enter a valid amount");
        }

        if(money>balance) {
            throw new InsufficientBalanceException("Insufficient Funds");
        }
        balance-=money;
        return balance;
    }

    public double transfer(Account receiver,double money)
    {
        if(receiver==null)
        {
            throw new IllegalArgumentException("Invalid receiver account");
        }

        if(money<=0){
            throw new IllegalArgumentException("Please enter a valid amount");
        }

        withdraw(money);
        receiver.deposit(money);

        return balance;
    }

}