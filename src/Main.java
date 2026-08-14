import model.Account;
import model.Transaction;
import service.BankService;
import exception.InsufficientBalanceException;
import util.InputHelper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        BankService bank = new BankService();

        Scanner scanner = new Scanner(System.in);

        while(true)
        {
            System.out.println("=========================");
            System.out.println("*************************");

            System.out.println("Welcome to Dholakpur Bank");

            System.out.println("*************************");
            System.out.println("=========================");

            System.out.println("Choose an option");
            System.out.println("1.Register");
            System.out.println("2.Login to an existing account");
            System.out.println("3.Exit");

            int input = InputHelper.readInt(scanner);

            if(input==1) {
                System.out.println("Enter your name");
                scanner.nextLine();
                String name = scanner.nextLine();
                System.out.println("\nSet your security PIN.");
                int PIN = InputHelper.readInt(scanner);
                System.out.println("Choose an initial amount to be deposited");
                double initialBalance = InputHelper.readDouble(scanner);

                try{
                    Account acc = bank.createAccount(name,PIN,initialBalance);

                    System.out.println("Account created successfully. Your account number is "+acc.getAccountNumber()+". You can login now");
                } catch(IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
            else if(input==2) {
                System.out.println("Enter your account number");
                long accNumber = InputHelper.readLong(scanner);

                System.out.println("Enter your security PIN");
                int PIN = InputHelper.readInt(scanner);

                try{
                    Account loggedInAccount = bank.login(accNumber,PIN);

                    while(true){
                        System.out.println("Choose an option");
                        System.out.println("1.Deposit");
                        System.out.println("2.Withdraw");
                        System.out.println("3.Bank transfer");
                        System.out.println("4.View current balance");
                        System.out.println("5.View transaction History");
                        System.out.println("6.Logout");

                        int option = InputHelper.readInt(scanner);

                        if(option==1){
                            try{
                                System.out.println("Enter the amount to be deposited");

                                double amount = InputHelper.readDouble(scanner);

                                double newBalance = bank.deposit(accNumber,amount);

                                System.out.println("Your account XXX"+accNumber%100+" is credited by Rs"+amount+"on "+ LocalDateTime.now()+" Available Balance Rs "+newBalance);

                                System.out.println("Thank you for choosing us. Have a nice day\n\n\n");
                            }
                            catch(IllegalArgumentException e){
                                System.out.println(e.getMessage());
                            }
                        }

                        else if(option==2){
                            try{
                                System.out.println("Enter the amount to be withdrawn");

                                double amount = InputHelper.readDouble(scanner);

                                double newBalance = bank.withdraw(accNumber,amount);

                                System.out.println("Your account XXX"+accNumber%100+" is debited by Rs"+amount+"on "+ LocalDateTime.now()+" Available Balance Rs "+newBalance);

                                System.out.println("Thank you for choosing us. Have a nice day\n\n\n");
                            }
                            catch(IllegalArgumentException | InsufficientBalanceException e){
                                System.out.println(e.getMessage());
                            }
                        }

                        else if(option==3){
                            try{
                                System.out.println("Enter receiver's account number");

                                long accNumber2 = InputHelper.readLong(scanner);

                                System.out.println("Enter the amount to be transferred");

                                double amount = InputHelper.readDouble(scanner);

                                double newBalance = bank.transfer(accNumber,accNumber2,amount);

                                System.out.println("Your account XXX"+accNumber%100+" is debited by Rs"+amount+"on "+ LocalDateTime.now()+" Available Balance Rs "+newBalance);

                                System.out.println("Thank you for choosing us. Have a nice day\n\n\n");
                            }
                            catch(IllegalArgumentException | InsufficientBalanceException e) {
                                System.out.println(e.getMessage());
                            }
                        }

                        else if(option==4){
                            System.out.println("Available Balance Rs "+loggedInAccount.getBalance());
                        }

                        else if(option==5){
                            List<Transaction> history = bank.getTransactionHistory(accNumber);
                            for(Transaction t:history) {
                                System.out.println(t);
                            }
                        }

                        else if(option==6)
                        {
                            System.out.println("Logged out successfully. Have a nice day\n\n\n");
                            break;
                        }

                        else{
                            System.out.println("Please choose a valid option");
                        }
                    }
                }
                catch(IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
            else if(input==3) {
                System.out.println("Thank you for choosing us. Have a nice day\n\n\n");
                break;
            }
            else{
                System.out.println("Please choose a valid option");
            }
        }
    }
}