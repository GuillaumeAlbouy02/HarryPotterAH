package org.HarryPotter.Characters.spells;

import org.HarryPotter.Characters.Character;
import org.HarryPotter.Characters.ennemies.Boss;
import org.HarryPotter.Characters.ennemies.Enemy;
import org.HarryPotter.Characters.wizards.Wizard;
import org.HarryPotter.levels.Level;

import java.util.concurrent.ThreadLocalRandom;

public class Spell extends AbstractSpell{

    private String name;
    private int effect;
    private int probability;


    public Spell(String name, int probability, int effect){
        this.name=name;
        this.probability = probability;
        this.effect = effect;
    }

    public void use(Character target, Level level, Wizard player){
        if (ThreadLocalRandom.current().nextInt(0,101)<=probability) {
            switch (effect) {
                case 0:
                    System.out.println("You stand defenseless as your spell deals little to no damage to your foe");
                    break;
                case 1:
                    leviosa(target);
                    break;
                case 2:
                    target.setHp(target.getHp()-(int)(30*player.getDamageMultiplier()));
                    break;
                case 3:
                    level.setEnemies(new Enemy[]{null});
                    level.setBosses(new Boss[]{null});
                    break;
                case 4:
                    target.setHp(target.getHp()-(int)(50* player.getDamageMultiplier()));
            }

        }
        else{
            System.out.println("You fail to cast the spell !");
        }
    }

    public static void leviosa(Character enemy){
        System.out.println("You decide to use Wingardium Leviosa on the troll's club and knock him out");
        enemy.setHp(0);

    }
    @Override
    public String toString() {
        return name;
    }
}
