package org.HarryPotter.display;

import java.util.Scanner;

public class Display {
    public Display(){

    }
    public void printText(String text) {
        System.out.println(text);
    }

    public void displayLevel(int level, String state, boolean isEvil) {
        String fileName = "/" + String.valueOf(level) + state + "Text.txt";
        if(isEvil) {
            fileName = "/" + String.valueOf(level) + state + "TextEvil.txt";
        }

        Scanner fileSc = new Scanner(getClass().getResourceAsStream(fileName));
        while (fileSc.hasNextLine()) {
            System.out.println(fileSc.nextLine());
        }


    }
}
