package org.HarryPotter;

import java.io.File;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SafeScanner {
    Scanner sc;
    public SafeScanner(InputStream in){
        this.sc = new Scanner(in);

    }


    public int getInt(){
        int result = 0;
        boolean inputValid=false;
        while (!inputValid)
        {
            try{
                System.out.println("Veuillez saisir un entier");
                result=this.sc.nextInt();

                inputValid=true;

            }
            catch (InputMismatchException e){
                this.sc.next();

            }
        }
        return result;

    }

    public int getInt2(String message){
        System.out.println(message);
        while(!sc.hasNextInt()){
            System.out.println(message);
            sc.next();
        }
        return sc.nextInt();
    }
    public String getString(String message){
        System.out.println(message);
        while(!sc.hasNextLine()){
            System.out.println(message);
            sc.next();
        }
        return sc.nextLine();
    }

public void closeScanner(){
        sc.close();
}


}


