package org.HarryPotter.Characters.wizards;

import lombok.Getter;
import lombok.Setter;
import org.HarryPotter.Characters.Character;
import org.HarryPotter.display.Display;

public class Potion {
    private @Getter @Setter String name;
    private @Getter int effect;
    public Potion(String name, int effect){
        this.name = name;
        this.effect = effect;

    }

    public void use(Character enemy, Wizard player, Display ds){
        switch (effect) {
            case 0: //health potion
                ds.printText("You drink a healing potion");
                heal(player,ds);
                break;
            case 1: //case of the Basilic's fang and Gryffindor's sword
                enemy.setHp(0);

        }

    }
    public void heal(Wizard player, Display ds){
        if(player.getHp()<player.getMaxHealth()){
            if (player.getHouse()== House.HUFFLEPUFF){
                if (player.getHp()< player.getMaxHealth()-10){
                    player.setHp(player.getHp()+10);
                }
                else{
                    player.setHp(player.getMaxHealth());
                }
            }
            else {
                if (player.getHp() < player.getMaxHealth() - 10) {
                    player.setHp(player.getHp() + 10);
                } else {
                    player.setHp(player.getMaxHealth());
                }
            }
        }
        else{
            ds.printText("You cannot heal yourself as you are not wounded... yet");
        }
    }
    public String toString(){
        return name;
    }
}
