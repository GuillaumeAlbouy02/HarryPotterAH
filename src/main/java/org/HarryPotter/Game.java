package org.HarryPotter;

import org.HarryPotter.Characters.wizards.Wizard;

public class Game {
private Wizard player;

public void play(){
    createPlayer();

}

private void createPlayer(){
    System.out.printf("please enter your name:");
    System.out.println("Congratulation ! You've been accepted to Hogwarts School of Witchcraft and Wizardry !");
    System.out.println("You must be exited to start the first year, but first, you have to buy a pet and a wand in Diagon Alley");

}
}
