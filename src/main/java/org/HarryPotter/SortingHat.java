package org.HarryPotter;

import org.HarryPotter.Characters.wizards.House;
import org.HarryPotter.Characters.wizards.Pet;
import org.HarryPotter.Characters.wizards.Wizard;

public class SortingHat {
    public static void chooseHouse(Wizard player, SafeScanner sc){
        System.out.println("You are now at the opening banquet of the year at Hogwarts, and the time has come for you to be assigned to one of the four houses.");
        System.out.println("The sorting hat is placed upon your head, and after a brief moment of consideration, it asks you : which house would you prefer ?");
        int choix = sc.getInt2("Type 1 for Gryffindor, 2 for Slytherin, 3 for Hufflepuff or 4 for Ravenclaw");
        if(choix<5){
            player.setHouse(House.values()[choix-1]);
        }
        System.out.println("You were assigned to "+ player.getHouse().name());


    }

}
