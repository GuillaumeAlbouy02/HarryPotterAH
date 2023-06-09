package org.HarryPotter;

import lombok.Getter;
import lombok.Setter;
import org.HarryPotter.Characters.ennemies.AbstractEnemy;
import org.HarryPotter.Characters.ennemies.Boss;
import org.HarryPotter.Characters.ennemies.Enemy;
import org.HarryPotter.Characters.spells.Spell;
import org.HarryPotter.Characters.wizards.*;
import org.HarryPotter.display.Display;
import org.HarryPotter.levels.Level;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
private @Getter @Setter Wizard player;

private AbstractEnemy currentEnemy;

private int level = 1;

private @Setter @Getter Level currentLevel;

private Display ds = new Display();
private SafeScanner sc = new SafeScanner(System.in, ds);


private @Getter boolean gameOver = false;

public void play(){
    createPlayer();                                                 //The game begins with the character creation

    level = sc.getInt2("level : ");                                 //You can access any level at the beginning of the game if you don't want to restart from the 1st level

        while(level<8 && !gameOver){
        levelSelect();                                              //Level creation : depending on this.level value, it will initialise currentLevel (create a list of enemies and bosses) and the player's spells and potions
        ds.displayLevel(level,"Intro", player.isEvil());
        int turn = 1;
        while(currentLevel.getCurrentEnemy()!=null && !gameOver) {
            specialRule(turn);
            currentEnemy = currentLevel.getCurrentEnemy();
            playerMove();

            endTurn();
            turn++;
        }


        if(currentLevel.getCurrentEnemy()==null){                   //End of the level
            ds.displayLevel(level,"Outro", player.isEvil());
            awardChoice();
            level++;
        }

        }
        if (level==8){
            ds.printText("Thank you for playing");
        }
        else{
            ds.printText("You were tragically killed trying to protect your friends :(");
        }
    }

    public void awardChoice(){
        int choice = 0;
        while (choice==0 && level!=7) { //The choice interface loops while the player hasn't chosen one of the options.
            ds.printText("\nYou cleared the level !\nYou can choose to: \n 1 - increase your health\n 2 - increase your damages");

            choice = sc.getInt2("Press 1 for heath, or 2 for damage");

            if (choice == 2) {
                player.setDamageMultiplier(player.getDamageMultiplier()+0.1);

            }
            else if (choice == 1) {
                player.setMaxHealth(player.getMaxHealth()+20);

            }
            else {
                choice = 0;
            }
        }
    }

    public void endTurn(){


        currentEnemy= currentLevel.getCurrentEnemy(); //The enemy is refreshed, to avoid getting killed by an enemy you defeated with the previous move.

        if (currentEnemy!=null &&currentEnemy.getHp() <= 0) { //If the enemy hp equals 0, it is set to null
            currentLevel.killCurrentEnemy(currentEnemy);
            currentEnemy = null;
        }
        if (currentEnemy != null && player.getDefend()!=1) {
            currentEnemy.attack(player, currentEnemy.getDamage(), currentEnemy.getPrecision());
        } else if (currentEnemy != null && player.getDefend()==1) {
            currentEnemy.attack(player, (int)(currentEnemy.getDamage()* player.getDefense()), currentEnemy.getPrecision());

        }
        player.setDefend(0);
        if (player.getHp() <= 0) {
            gameOver = true;
            ds.printText("Game Over");
        }
    }









private void createPlayer(){
    player = new Wizard();
    player.setName(sc.getString("Please enter your name : "));
    player.setPet(null);
    ds.printText("Congratulation "+player.getName()+ " ! You've been accepted to Hogwarts School of Witchcraft and Wizardry !\n");
    ds.printText("You must be excited to start the first year, but first you have to buy a pet and a wand in Diagon Alley");
    ds.printText("Let's start with the pet, you can choose between an owl, a rat, a cat or a toad");

    while (player.getPet()==null){
        int choix = -1;
        while(!(choix<5&&choix>0)){
            choix = sc.getInt2("Enter 1 for owl, 2 for rat, 3 for cat or 4 for a toad");

        }
        player.setPet(Pet.values()[choix-1]);

    }
    ds.printText("So you chose an adorable "+ player.getPet().name()+"\n");
    ds.printText("You must now go to Ollivander's to choose a wand, or, to be exact, to be chosen by a wand");
    player.setWand(new Wand(Core.values()[ThreadLocalRandom.current().nextInt(0,3)], ThreadLocalRandom.current().nextInt(9,15)));
    ds.printText("You were chosen by a "+player.getWand().getSize() + " inches wand, with a " + player.getWand().getCore().name() + " core\n");
    SortingHat.chooseHouse(player,sc, ds);

    //House bonus
    if (player.getHouse()==House.SLYTHERIN){
        player.setDamageMultiplier(1.5);
    }
    if(player.getHouse()==House.GRYFFINDOR){
        player.setDefense(0.5f);
    }
    if(player.getHouse()==House.RAVENCLAW){
        player.setPrecision(1.3f);
    }



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
            int healthPotions = player.getPotions()[0].getUseNumber();

            if(turn ==3 && player.getHouse().equals(House.GRYFFINDOR)){
                ds.printText("You are slowly losing hope of ever defeating this beast, when you suddenly hear a bird's cry beahind you. You turn around, only to discover that :");
                ds.printText("Dumbledore's phoenix, Fawkes, has brought you... GRYFFINDOR's SWORD ?!!?");
                player.setPotions(new Potion[]{new Potion("health potion",0, healthPotions), new Potion("Godric Gryffindor's sword",1, 1)});
            } else if (turn == 5 && !player.getHouse().equals(House.GRYFFINDOR)){
                ds.printText("You grow tired and you are losing hope, there's simply nothing you can do !");
                ds.printText("You throw a rock at the basilisk out of hopelessness, and somehow manage to break one of its fang, which you quickly pick up as it would make a nice souvenir if you ever got out of here alive.");
                player.setPotions(new Potion[]{new Potion("health potion",0, healthPotions), new Potion("A basilisk fang",1, 1)});


            }
            break;
        case 3:
            if (turn == 6){
                player.setKnownSpells(new Spell[]{new Spell("Alohomora", 1,0), new Spell("Reparo",1,0), new Spell("Wingardium Leviosa",50,0), new Spell("Expecto Patronum", 45,3)});

            }

            break;
        case 4:

            if(currentEnemy!=null&&currentEnemy.getName()=="Voldemort"){
                if (player.getWand().getCore()==Core.PHOENIX_FEATHERS) {
                    ds.printText("As you and Voldemort start to battle, right as your spells collide, the most peculiar thing happens : the spells stop, they cancel each other out");
                    ds.printText("You both understand that, being brothers, your wands can't battle each other");
                    ds.printText("During this brief moment of confusion, you take your chance and cast Accio to get the portkey as fast as you can");
                    currentLevel.killCurrentEnemy(currentEnemy);
                }
                player.setKnownSpells(new Spell[]{ new Spell("Wingardium Leviosa",50,0), new Spell("Expecto Patronum", 15,0), new Spell("Expelliarmus", 75,2), new Spell("Accio", 90, 3)});

            }

            break;
        case 5:
            int healthPotion5 = player.getPotions()[0].getUseNumber();

            if(turn>ThreadLocalRandom.current().nextInt(3,7)){
                ds.printText("you got fireworks");
                player.setPotions(new Potion[]{new Potion("Health potion", 0, healthPotion5), new Potion("Fireworks", 1, 1)});
            }

            break;

        case 7:


            break;
        default:
            break;

    }

}

public void playerMove(){
    //the player is asked which move he wants to make. The corresponding method is then called
    if(currentEnemy!=null) {
        ds.printText("\n \nYou're facing a " + currentEnemy.getName() + " at " + currentEnemy.getHp() + " hp");
        ds.printText("You're at " + player.getHp() + " hp");
        int choice = 0;
        while (choice == 0) {
            choice = sc.getInt2("You can : \n1 - Cast spell \n2 - Use object \n3 - Defend");
            if (choice == 1) {
                ds.printText("Here are the spells you know:");
                for (int i = 0; i < player.getKnownSpells().length; i++) {
                    ds.printText(i + " - " + player.getKnownSpells()[i].toString());
                }
                int g = -1;
                while (g >= player.getKnownSpells().length || g < 0) {
                    g = sc.getInt2("Which spell do you choose ?");
                }
                player.getKnownSpells()[g].use(currentEnemy, currentLevel, player, ds);

            } else if (choice == 2) {
                if (player.getPotions() != null) {
                    ds.printText("Here are the objects you have:");
                    for (int i = 0; i < player.getPotions().length; i++) {
                        ds.printText(i + " - " + player.getPotions()[i].toString() + " ("+player.getPotions()[i].getUseNumber()+" uses left)");
                    }
                    int g = -1;
                    while (g >= player.getPotions().length || g < 0) {
                        g = sc.getInt2("Which object do you choose to use ?");
                    }
                    player.getPotions()[g].use(currentEnemy, player, ds);

                } else {
                    ds.printText("You don't have any objects");
                }

            } else if (choice == 3) {
                ds.printText("You chose to defend yourself");
                player.setDefend(1);


            } else {
                choice = 0;
            }
        }
    }

}


/*
THE FOLLOWING METHODS ARE MADE TO INITIALIZE EACH LEVEL
enemies, bosses, available spells and items
 */


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
        player.setPotions(new Potion[]{new Potion("Health potion", 0, 5)});

        currentLevel = new Level(null,bosses);

    }
    public void lev3(){
        Enemy[] enemies = new Enemy[15];
        for(int i =0; i<15;i++){
            enemies[i] = new Enemy("dementor", 100,10,15);

        }
        Boss[] bosses = null;
        player.setKnownSpells(new Spell[]{new Spell("Alohomora", 1,0), new Spell("Reparo",1,0), new Spell("Wingardium Leviosa",50,0), new Spell("Expecto Patronum", 75,2)});
        player.setPotions(new Potion[]{new Potion("Health potion", 0,5)});

        currentLevel = new Level(enemies,bosses);

    }
    public void lev4(){
        Enemy[] enemies = new Enemy[]{new Enemy("Peter Pettigrew",100,25,25)};


        Boss[] bosses = new Boss[]{new Boss("Voldemort", 1500, 100,10)};
        player.setKnownSpells(new Spell[]{ new Spell("Wingardium Leviosa",50,0), new Spell("Expecto Patronum", 15,0), new Spell("Expelliarmus", 75,2), new Spell("Accio", 10, 3)});
        player.setPotions(new Potion[]{new Potion("Health potion", 0, 5)});

        currentLevel = new Level(enemies,bosses);

    }


    public void lev5(){
        Enemy[] enemies = null;

        Boss[] bosses = new Boss[]{new Boss("Dolores Umbridge", 1000, 13,20)};
        player.setKnownSpells(new Spell[]{ new Spell("Wingardium Leviosa",50,0), new Spell("Expecto Patronum", 15,0), new Spell("Expelliarmus", 75,2), new Spell("Accio", 10, 0)});
        player.setPotions(new Potion[]{new Potion("Health potion", 0, 5)});

        currentLevel = new Level(enemies,bosses);

    }
    public void lev6(){
    if(player.getHouse() == House.SLYTHERIN){
        ds.printText("Being a Slytherin, you have always had a sort of affinity with the dark arts. As you watch the death eaters, you are faced with a terrible dilemma :\n"+
                "Will you stay loyal to your friends and Hogwarts, or will you side with the Dark Lord ?");
        int choice = 0;
        while (choice==0) {
            choice = sc.getInt2("Press 1 to stay loyal, or 2 to side with the death eaters");

            if (choice == 2) {
                Enemy[] enemies = new Enemy[]{new Enemy("Student", 60, 5, 50), new Enemy("Student", 60, 5, 50), new Enemy("Student", 60, 5, 50)};

                Boss[] bosses = new Boss[]{new Boss("Hogwarts teacher", 150, 13, 20)};
                player.setKnownSpells(new Spell[]{new Spell("Wingardium Leviosa", 50, 0), new Spell("Expecto Patronum", 15, 0), new Spell("Expelliarmus", 75, 2), new Spell("Accio", 10, 0), new Spell("Sectumsempra", 75, 4)});
                player.setPotions(new Potion[]{new Potion("Health potion", 0, 5)});
                player.setEvil(true);

                currentLevel = new Level(enemies, bosses);
            } else if (choice == 1) {
                lev6Loy();
            } else {
                choice = 0;
            }
        }
    }
    else{lev6Loy();}

    }
    public void lev6Loy(){
        Enemy[] enemies = new Enemy[]{new Enemy("Death Eater", 60,5,50),new Enemy("Death Eater", 60,5,50),new Enemy("Death Eater", 60,5,50)};

        Boss[] bosses = new Boss[]{new Boss("Severus Snape", 150, 13,20)};
        player.setKnownSpells(new Spell[]{ new Spell("Wingardium Leviosa",50,0), new Spell("Expecto Patronum", 15,0), new Spell("Expelliarmus", 75,2), new Spell("Accio", 10, 0), new Spell("Sectumsempra", 75,4)});
        player.setPotions(new Potion[]{new Potion("Health potion", 0, 5)});

        currentLevel = new Level(enemies,bosses);
    }

    public void lev7(){
    if (!player.isEvil()) {


        Enemy[] enemies = new Enemy[]{new Enemy("Death Eater", 60, 5, 50), new Enemy("Death Eater", 60, 5, 50), new Enemy("Death Eater", 60, 5, 50)};

        Boss[] bosses = new Boss[]{new Boss("Bellatrix Lestrange", 200, 20, 20), new Boss("Voldemort", 200, 40, 20)};
        player.setKnownSpells(new Spell[]{new Spell("Wingardium Leviosa", 50, 0), new Spell("Expecto Patronum", 15, 0), new Spell("Expelliarmus", 75, 2), new Spell("Accio", 10, 0), new Spell("Sectumsempra", 75, 4)});
        player.setPotions(new Potion[]{new Potion("Health potion", 0, 5)});

        currentLevel = new Level(enemies, bosses);
    }
    else{
        Enemy[] enemies = new Enemy[]{new Enemy("Ron Weasley", 60, 5, 50), new Enemy("Hermione Granger", 60, 5, 50), new Enemy("Fred Weasley", 60, 5, 50), new Enemy("George Weasley", 60, 5, 50), new Enemy("Ginny Weasley", 60, 5, 50), new Enemy("Rubeus Hagrid", 60, 5, 50)};

        player.setKnownSpells(new Spell[]{new Spell("Wingardium Leviosa", 50, 0), new Spell("Expecto Patronum", 15, 0), new Spell("Expelliarmus", 75, 2), new Spell("Accio", 10, 0), new Spell("Sectumsempra", 75, 4), new Spell("Avada Kedavra", 75, 1)});
        player.setPotions(new Potion[]{new Potion("Health potion", 0, 5)});

        currentLevel = new Level(enemies, null);

    }

    }



}
