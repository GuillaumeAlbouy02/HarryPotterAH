package org.HarryPotter.Characters.ennemies;

import org.HarryPotter.Characters.Character;
import org.HarryPotter.Characters.wizards.Wizard;

public class Boss extends AbstractEnemy{

    public Boss(String name, int hp, int damage, int precision){
        this.setName(name);
        this.setHp(hp);
        this.setDamage(damage);
        this.setPrecision(precision);

    }



}
