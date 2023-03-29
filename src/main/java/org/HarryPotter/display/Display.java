package org.HarryPotter.display;

import java.util.Scanner;

public class Display {
    public Display(){

    }
    public void printText(String text) {
        System.out.println(text);
    }

    public void displayLevel(int level, String state, boolean isEvil) {
        Scanner fileSc = null;
        if(!isEvil) {
            fileSc = new Scanner(getClass().getResourceAsStream("/" + String.valueOf(level) + state + "Text.txt"));
        }
        else{
            fileSc = new Scanner(getClass().getResourceAsStream("/" + String.valueOf(level) + state + "TextEvil.txt"));

        }
        while (fileSc.hasNextLine()) {
            System.out.println(fileSc.nextLine());
        }


    }
}
