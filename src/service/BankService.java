package service;

import model.Account;
import model.Transaction;
import util.FileHandler;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

public class BankService {

    private List<Account> accounts;
    private List<Transaction> transactions;

    public BankService(){
        accounts = FileHandler.loadAccounts();
        transactions = FileHandler.loadTransactions();
    }

    public Account createAccount(String holderName, int pin, double initialBalance) {
        long accountNumber = 10000+accounts.size()+1;

        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }

        if (pin < 1000 || pin > 9999) {
            throw new IllegalArgumentException("PIN must be exactly 4 digits");
        }

        Account account = new Account(
                accountNumber,
                holderName,
                pin,
                initialBalance
        );

        accounts.add(account);
        FileHandler.saveAccounts(accounts);

        return account;
    }

    public Account findAccount(long accountNumber)
    {
        for(Account acc:accounts)
        {
            if(acc.getAccountNumber()==accountNumber)
                return acc;
        }
        return null;
    }

    public Account login(long accountNumber, int pin)
    {
        Account account = findAccount(accountNumber);

        if(account==null)
            throw new IllegalArgumentException("Account not found");

        if(account.getPin()!=pin)
        {
            throw new IllegalArgumentException("Incorrect PIN");
        }

        return account;
    }

    private void recordTransaction(long accountNumber,
                                   String type,
                                   double amount) {

        long transactionId = transactions.size() + 1;

        Transaction transaction = new Transaction(
                transactionId,
                accountNumber,
                type,
                amount,
                LocalDateTime.now()
        );

        transactions.add(transaction);

        FileHandler.saveTransactions(transactions);
    }

    public double deposit(long accountNumber, double amount)
    {
        Account account = findAccount(accountNumber);

        if(account==null)
        {
            throw new IllegalArgumentException("Account not found");
        }

        double newBalance = account.deposit(amount);

        FileHandler.saveAccounts(accounts);

        recordTransaction(accountNumber,"DEPOSIT",amount);

        return newBalance;
    }

    public double withdraw(long accountNumber, double amount)
    {
        Account account = findAccount(accountNumber);

        if(account==null)
        {
            throw new IllegalArgumentException("Account not found");
        }

        double newBalance = account.withdraw(amount);

        FileHandler.saveAccounts(accounts);

        recordTransaction(accountNumber,"WITHDRAW",amount);

        return newBalance;
    }

    public double transfer(long accountNumber1, long accountNumber2, double amount)
    {
        Account account1 = findAccount(accountNumber1);
        Account account2 = findAccount(accountNumber2);

        if(account1==null || account2==null) {
            throw new IllegalArgumentException("Invalid sender or receiver account number");
        }

        if(account1==account2)
            throw new IllegalArgumentException("Sender and receiver accounts must be different");

        double newBalance = account1.withdraw(amount);

        account2.deposit(amount);

        FileHandler.saveAccounts(accounts);

        recordTransaction(accountNumber1,"TRANSFER_SENT",amount);
        recordTransaction(accountNumber2,"TRANSFER_RECEIVED",amount);

        return newBalance;
    }

    public List<Transaction> getTransactionHistory(long accountNumber) {
        List<Transaction> history = new ArrayList<>();

        for(Transaction transaction : transactions)
        {
            if(transaction.getAccountNumber()==accountNumber){
                history.add(transaction);
            }
        }

        return history;
    }
}
