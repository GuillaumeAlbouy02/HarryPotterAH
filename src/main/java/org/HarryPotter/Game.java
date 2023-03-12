package org.HarryPotter;

import org.HarryPotter.Characters.wizards.Core;
import org.HarryPotter.Characters.wizards.Pet;
import org.HarryPotter.Characters.wizards.Wand;
import org.HarryPotter.Characters.wizards.Wizard;
import org.HarryPotter.levels.Level;

import java.sql.SQLOutput;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
private Wizard player;

private int level = 1;

private Level currentLevel;
private SafeScanner sc = new SafeScanner();

public void play(){
    createPlayer();
    while (!gameOver())
        currentLevel = new Level();
    }


}






private void createPlayer(){
    player = new Wizard();
    player.setName(sc.getString("Please enter your name : "));
    player.setPet(null);
    System.out.println("Congratulation "+player.getName()+ " ! You've been accepted to Hogwarts School of Witchcraft and Wizardry !");
    System.out.println("You must be exited to start the first year, but first you have to buy a pet and a wand in Diagon Alley");
    System.out.println("Let's start with the pet, you can choose between an owl, a rat, a cat or a toad");
    while (player.getPet()==null){
        int choix = sc.getInt2("Enter 1 for owl, 2 for rat, 3 for cat or 4 for a toad");
        if(choix<5){
            player.setPet(Pet.values()[choix-1]);
        }

    }
    System.out.println("So you chose an adorable "+ player.getPet().name());
    System.out.println("You must now go to Ollivander's to choose a wand, or, to be exact, to be chosen by a wand");
    player.setWand(new Wand(Core.values()[ThreadLocalRandom.current().nextInt(0,3)], ThreadLocalRandom.current().nextInt(9,15)));
    System.out.println("You were chosen by a "+player.getWand().getSize() + " inches wand, with a " + player.getWand().getCore().name() + " core");
    SortingHat.chooseHouse(player,sc);



}

private boolean gameOver(){
    return false;
}
}
