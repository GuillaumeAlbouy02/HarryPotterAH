package org.HarryPotter;

import org.HarryPotter.Characters.ennemies.AbstractEnemy;
import org.HarryPotter.Characters.ennemies.Boss;
import org.HarryPotter.Characters.ennemies.Enemy;
import org.HarryPotter.Characters.spells.Spell;
import org.HarryPotter.Characters.wizards.Core;
import org.HarryPotter.Characters.wizards.Pet;
import org.HarryPotter.Characters.wizards.Wand;
import org.HarryPotter.Characters.wizards.Wizard;
import org.HarryPotter.levels.Level;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
private Wizard player;

private AbstractEnemy currentEnemy;

private int level = 1;

private Level currentLevel;
private SafeScanner sc = new SafeScanner(System.in);

private boolean gameOver = false;

public void play(){
    createPlayer();

        while(level<8 && !gameOver){
        levelSelect();                                              //Level creation
        currentLevel.display(level,"Intro");
        while(currentLevel.getCurrentEnemy()!=null && !gameOver) {
            currentEnemy = currentLevel.getCurrentEnemy();
            System.out.println("You're facing a " + currentEnemy.getName() + " at " + currentEnemy.getHp() + " hp");
            System.out.println("You're at " + player.getHp() + " hp");
            playerMove();
            if (currentEnemy.getHp() <= 0) {
                currentLevel.killCurrentEnemy();
                currentEnemy = null;
            }
            if (currentEnemy != null) {
                currentEnemy.attack(player, currentEnemy.getDamage());
            }
            if (player.getHp() <= 0) {
                gameOver = true;
                System.out.println("Game Over");
            }
        }
        if(currentLevel.getCurrentEnemy()==null){
            currentLevel.display(level,"Outro");
            level++;
        }



        }
        if (level==8){
            System.out.println("you saved the world!");
        }
        else{
            System.out.println("You were tragically killed trying to protect your fiends :(");
        }




    //}
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

public void levelSelect(){
    switch (level) {
        case 1:
        lev1();
        break;
        case 2:
        lev2();
        break;
        case 3:
        lev3();
        break;
        case 4:
        lev4();
        break;
        case 5:
        lev5();
        break;
        case 6:
        lev6();
        break;
        case 7:
        lev7();
        break;
    }

}

public void playerMove(){
    if(sc.getInt2("You can : 1 - Cast spell or 2 - Use object")==1){
        System.out.println("Here are the spells you know:");
        for(int i=0; i<player.getKnownSpells().length;i++){
            System.out.println(i+" - "+player.getKnownSpells()[i].toString());
        }
        int g=-1;
        while(g>=player.getKnownSpells().length || g<0) {
            g = sc.getInt2("Which spell do you choose ?");
        }
        player.getKnownSpells()[g].use(currentEnemy);


    }
    else{

    }

}


    public void lev1(){
    Boss[] bosses = new Boss[1];
    bosses[0] = new Boss("Troll", 150,75,100);
    player.setKnownSpells(new Spell[]{new Spell("Alohomora", 1,0), new Spell("Reparo",1,0), new Spell("Wingardium Leviosa",50,1)});

    currentLevel = new Level(null,bosses);

    }
    public void lev2(){
        Boss[] bosses = new Boss[1];
        bosses[0] = new Boss("Basilisk", 1000,15,10);
        player.setKnownSpells(new Spell[]{new Spell("Alohomora", 1,0), new Spell("Reparo",1,0), new Spell("Wingardium Leviosa",50,0)});

        currentLevel = new Level(null,bosses);

    }
    public void lev3(){

    }
    public void lev4(){

    }
    public void lev5(){

    }
    public void lev6(){

    }

    public void lev7(){

    }
}
