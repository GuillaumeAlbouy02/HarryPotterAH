package org.HarryPotter.Characters.wizards;

import lombok.Getter;
import lombok.Setter;
import org.HarryPotter.Characters.Character;

public class Potion {
    private @Getter @Setter String name;
    private @Getter int effect;
    public Potion(String name, int effect){
        this.name = name;
        this.effect = effect;

    }

    public void use(Character enemy, Character player){
        switch (effect) {
            case 0: //health potion
                System.out.println("You drink a healing potion");
                heal(player);
                break;
            case 1: //case of the Basilic's fang and Gryffindor's sword
                enemy.setHp(0);

        }

    }
    public void heal(Character player){
        if(player.getHp()<100){
            if (player.getHp()<90){
                player.setHp(player.getHp()+10);
            }
            else{
                player.setHp(100);
            }
        }
        else{
            System.out.println("You cant heal yourself as you are not wounded... yet");
        }
    }
    public String toString(){
        return name;
    }
}
