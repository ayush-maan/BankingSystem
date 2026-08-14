package model;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable{

    private long transactionId;
    private long accountNumber;
    private String type;
    private double amount;
    private LocalDateTime dateTime;

    public Transaction(long transactionId,long accountNumber,String type,double amount,LocalDateTime dateTime){
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", accountNumber=" + accountNumber +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", dateTime=" + dateTime +
                '}';
    }

}
