import model.Account;
import model.Transaction;
import service.BankService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        BankService bank = new BankService();

        Scanner scanner = new Scanner(System.in);

        System.out.println("=========================");
        System.out.println("*************************");

        System.out.println("Welcome to Dholakpur Bank");

        System.out.println("*************************");
        System.out.println("=========================");

        System.out.println("Choose an option");
        System.out.println("1.Register");
        System.out.println("2.Login to an existing account");
        System.out.println("3.Exit");

        int input = scanner.nextInt();

        if(input==1) {
            System.out.println("Enter your name");
            String name = scanner.next();
            System.out.println("\nSet your security PIN.");
            int PIN = scanner.nextInt();
            System.out.println("Choose an initial amount to be deposited");
            double initialBalance = scanner.nextDouble();

            Account acc = bank.createAccount(name,PIN,initialBalance);

            System.out.println("Account created successfully. Your account number is "+acc.getAccountNumber()+". You can login now");
            main(args);
        }
        else if(input==2) {
            System.out.println("Enter your account number");
            long accNumber = scanner.nextInt();

            System.out.println("Enter your security PIN");
            int PIN = scanner.nextInt();

            bank.login(accNumber,PIN);

            System.out.println("Choose an option");
            System.out.println("1.Deposit");
            System.out.println("2.Withdraw");
            System.out.println("3.Bank transfer");
            System.out.println("4.View transaction History");
            System.out.println("5.Logout");

            int option = scanner.nextInt();

            if(option==1){
                System.out.println("Enter the amount to be deposited");

                double amount = scanner.nextDouble();

                double newBalance = bank.deposit(accNumber,amount);

                System.out.println("Your account XXX"+accNumber%100+" is credited by Rs"+amount+"on "+ LocalDateTime.now()+" Available Balance Rs "+newBalance);

                System.out.println("Thank you for choosing us. Have a nice day\n\n\n");
                main(args);
            }

            else if(option==2){
                System.out.println("Enter the amount to be withdrawn");

                double amount = scanner.nextDouble();

                double newBalance = bank.withdraw(accNumber,amount);

                System.out.println("Your account XXX"+accNumber%100+" is debited by Rs"+amount+"on "+ LocalDateTime.now()+" Available Balance Rs "+newBalance);

                System.out.println("Thank you for choosing us. Have a nice day\n\n\n");
                main(args);
            }

            else if(option==3){
                System.out.println("Enter receiver's account number");

                long accNumber2 = scanner.nextInt();

                System.out.println("Enter the amount to be transferred");

                double amount = scanner.nextDouble();

                double newBalance = bank.transfer(accNumber,accNumber2,amount);

                System.out.println("Your account XXX"+accNumber%100+" is debited by Rs"+amount+"on "+ LocalDateTime.now()+" Available Balance Rs "+newBalance);

                System.out.println("Thank you for choosing us. Have a nice day\n\n\n");
                main(args);
            }

            else if(option==4){
                List<Transaction> history = bank.getTransactionHistory(accNumber);
                for(Transaction t:history) {
                    System.out.println(t);
                }
            }

            else if(option==5)
            {
                System.out.println("Logged out successfully. Have a nice day\n\n\n");
                main(args);
            }

            else{
                System.out.println("Please choose a valid option");
            }
        }
        else if(input==3) {
            System.out.println("Thank you for choosing us. Have a nice day\n\n\n");
            main(args);
        }
        else{
            System.out.println("Please choose a valid option");
        }
    }
}