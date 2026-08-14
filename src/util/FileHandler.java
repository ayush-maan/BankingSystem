package util;

import model.Account;
import model.Transaction;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class FileHandler {

    public static void saveAccounts(List<Account> accounts){
        try(ObjectOutputStream out =
                new ObjectOutputStream(
                        new FileOutputStream("data/accounts.dat"))){

            out.writeObject(accounts);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static List<Account> loadAccounts(){
        try(ObjectInputStream in =
                new ObjectInputStream(
                        new FileInputStream("data/accounts.dat"))){

            return (List<Account>) in.readObject();

        }catch(FileNotFoundException e){
            return new ArrayList<>();

        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    public static void saveTransactions(List<Transaction> transactions) {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(
                             new FileOutputStream("data/transactions.dat"))) {

            out.writeObject(transactions);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Transaction> loadTransactions() {
        try (ObjectInputStream in =
                     new ObjectInputStream(
                             new FileInputStream("data/transactions.dat"))) {

            return (List<Transaction>) in.readObject();

        } catch (FileNotFoundException e) {
            return new ArrayList<>();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
