package util;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHelper {
    public static int readInt(Scanner scanner){

        while(true){
            try{
                return scanner.nextInt();
            } catch(InputMismatchException e){
                System.out.println("Please enter a valid value");
                scanner.nextLine();
            }
        }
    }

    public static long readLong(Scanner scanner){

        while(true){
            try{
                return scanner.nextLong();
            } catch(InputMismatchException e){
                System.out.println("Please enter a valid account number");
                scanner.nextLine();
            }
        }
    }

    public static double readDouble(Scanner scanner){

        while(true){
            try{
                return scanner.nextDouble();
            }
            catch(InputMismatchException e){
                System.out.println("Please enter a valid amount");
            }
        }
    }
}
