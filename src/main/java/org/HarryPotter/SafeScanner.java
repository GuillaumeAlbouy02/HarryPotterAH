package org.HarryPotter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SafeScanner {
    Scanner sc;
    public SafeScanner(){
        this.sc = new Scanner(System.in);

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

    public int getInt2(){
        System.out.println("Veuillez entrer un entier");
        while(!sc.hasNextInt()){
            System.out.println("Veuillez entrer un entier");
            sc.next();
        }
        return sc.nextInt();
    }

public void closeScanner(){
        sc.close();
}

    public static void cat(SafeScanner sc){
        int height = 0;
        int width = 0;
        while (height<3) {
            System.out.println("Choisir une hauteur supérieure à 3");
            height = sc.getInt2();
        }
        while(width<7) {
            System.out.println("Choisir la lageur supérieure à 5");
            width = sc.getInt2();
        }
        System.out.print("|\\");
        for (int i =0;i<width-4;i++){
            System.out.print("-");

        }
        System.out.println("/|");
        for (int h=0;h<(height-2)/2;h++){
            System.out.print("|");
            for (int i =0;i<width-2;i++){
                System.out.print(" ");
            }
            System.out.print("|");
        }

        System.out.print("| o");
        for (int c=0;c<width-6;c++){
            System.out.print("_");
        }
        System.out.println("o |");

        for (int h=0;h<(height-2)/2;h++){
            System.out.print("|");
            for (int i =0;i<width-2;i++){
                System.out.print(" ");
            }
            System.out.print("|");
        }

        System.out.print(" \\_");
        for (int i =0;i<width-6;i++){
            System.out.print("^");

        }
        System.out.print("_/ ");


    }
}


