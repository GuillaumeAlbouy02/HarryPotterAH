package org.HarryPotter;

import org.HarryPotter.Characters.ennemies.AbstractEnemy;
import org.HarryPotter.Characters.ennemies.Boss;
import org.HarryPotter.Characters.ennemies.Enemy;
import org.HarryPotter.Characters.spells.Spell;
import org.HarryPotter.Characters.wizards.*;
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
    level = sc.getInt2("level : ");

        while(level<8 && !gameOver){
        levelSelect();                                              //Level creation
        currentLevel.display(level,"Intro");
        int turn = 1;
        while(currentLevel.getCurrentEnemy()!=null && !gameOver) {
            specialRule(turn);
            currentEnemy = currentLevel.getCurrentEnemy();
            System.out.println("You're facing a " + currentEnemy.getName() + " at " + currentEnemy.getHp() + " hp");
            System.out.println("You're at " + player.getHp() + " hp");
            playerMove();


            //Mettre cette partie dans une fonction
            if (currentEnemy.getHp() <= 0) {
                currentLevel.killCurrentEnemy();
                currentEnemy = null;
            }
            if (currentEnemy != null) {
                currentEnemy.attack(player, currentEnemy.getDamage(), currentEnemy.getPrecision());
            }
            if (player.getHp() <= 0) {
                gameOver = true;
                System.out.println("Game Over");
            }
            turn++;
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
public void specialRule(int turn){
    switch (level) {
        case 2:
            if(turn ==3 && player.getHouse().equals(House.GRYFFINDOR)){
                System.out.println("You are slowly losing hope of ever defeating this beast, when you suddenly hear a bird's cry beahind you. You turn around, only to discover that :");
                System.out.println("Dumbledore's phoenix, Fawkes, has brought you... GRYFFINDOR's SWORD ?!!?");
                player.setPotions(new Potion[]{new Potion("health potion",0), new Potion("Godric Gryffindor's sword",1)});
            } else if (turn == 5 && !player.getHouse().equals(House.GRYFFINDOR)){
                System.out.println("You grow tired and you are losing hope, there's simply nothing you can do !");
                System.out.println("You throw a rock at the basilisk out of hopelessness, and somehow manage to break one of its fang, which you quickly pick up as it would make a nice souvenir if you ever got out of here alive.");
                player.setPotions(new Potion[]{new Potion("health potion",0), new Potion("A basilisk fang",1)});


            }
            break;
        case 3:
            if (turn == 6){
                player.setKnownSpells(new Spell[]{new Spell("Alohomora", 1,0), new Spell("Reparo",1,0), new Spell("Wingardium Leviosa",50,0), new Spell("Expecto Patronum", 45,3)});

            }

            break;
        case 4:
            if(turn>4 && currentEnemy.getName()=="Voldemort"){
                player.setKnownSpells(new Spell[]{ new Spell("Wingardium Leviosa",50,0), new Spell("Expecto Patronum", 15,0), new Spell("Expelliarmus", 75,2), new Spell("Accio", 90, 3)});

            }

            break;
        case 5:
            if(turn>ThreadLocalRandom.current().nextInt(3,7)){
                System.out.println("you got fireworks");
                player.setPotions(new Potion[]{new Potion("Health potion", 0), new Potion("Fireworks", 1)});
            }

            break;
        case 6:

            break;
        case 7:

            break;
        default:
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
        player.getKnownSpells()[g].use(currentEnemy, currentLevel);


    }
    else if(player.getPotions()!=null){
        System.out.println("Here are the objects you have:");
        for(int i=0; i<player.getPotions().length;i++){
            System.out.println(i+" - "+player.getPotions()[i].toString());
        }
        int g=-1;
        while(g>=player.getKnownSpells().length || g<0) {
            g = sc.getInt2("Which object do you choose to use ?");
        }
        player.getPotions()[g].use(currentEnemy, player);

    }
    else{
        System.out.println("You don't have any objects");
    }

}


    public void lev1(){
    Boss[] bosses = new Boss[1];
    bosses[0] = new Boss("Troll", 150,75,100);
    player.setKnownSpells(new Spell[]{new Spell("Alohomora", 100,0), new Spell("Reparo",100,0), new Spell("Wingardium Leviosa",75,1)});


    currentLevel = new Level(null,bosses);

    }
    public void lev2(){
        Boss[] bosses = new Boss[1];
        bosses[0] = new Boss("Basilisk", 1000,15,10);
        player.setKnownSpells(new Spell[]{new Spell("Alohomora", 1,0), new Spell("Reparo",1,0), new Spell("Wingardium Leviosa",50,0)});
        player.setPotions(new Potion[]{new Potion("Health potion", 0)});

        currentLevel = new Level(null,bosses);

    }
    public void lev3(){
        Enemy[] enemies = new Enemy[15];
        for(int i =0; i<15;i++){
            enemies[i] = new Enemy("dementor", 100,10,15);

        }
        Boss[] bosses = null;
        player.setKnownSpells(new Spell[]{new Spell("Alohomora", 1,0), new Spell("Reparo",1,0), new Spell("Wingardium Leviosa",50,0), new Spell("Expecto Patronum", 15,2)});
        player.setPotions(new Potion[]{new Potion("Health potion", 0)});

        currentLevel = new Level(enemies,bosses);

    }
    public void lev4(){
        Enemy[] enemies = new Enemy[]{new Enemy("Peter Pettigrew",100,25,25)};


        Boss[] bosses = new Boss[]{new Boss("Voldemort", 1500, 100,10)};
        player.setKnownSpells(new Spell[]{ new Spell("Wingardium Leviosa",50,0), new Spell("Expecto Patronum", 15,0), new Spell("Expelliarmus", 75,2), new Spell("Accio", 10, 3)});
        player.setPotions(new Potion[]{new Potion("Health potion", 0)});

        currentLevel = new Level(enemies,bosses);

    }


    public void lev5(){
        Enemy[] enemies = null;

        Boss[] bosses = new Boss[]{new Boss("Dolores Umbridge", 1000, 13,20)};
        player.setKnownSpells(new Spell[]{ new Spell("Wingardium Leviosa",50,0), new Spell("Expecto Patronum", 15,0), new Spell("Expelliarmus", 75,2), new Spell("Accio", 10, 0)});
        player.setPotions(new Potion[]{new Potion("Health potion", 0)});

        currentLevel = new Level(enemies,bosses);

    }
    public void lev6(){

    }

    public void lev7(){

    }
}
