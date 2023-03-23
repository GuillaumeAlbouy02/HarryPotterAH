package org.HarryPotter.Characters.wizards;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.HarryPotter.Characters.Character;
import org.HarryPotter.Characters.spells.Spell;

import java.lang.annotation.Target;

public class Wizard extends Character {

    private @Getter @Setter Pet pet;
    private @Getter @Setter Wand wand;
    private @Getter @Setter House house;
    private @Getter @Setter Spell[] knownSpells;
    private @Getter @Setter Potion[] potions;
    private @Getter @Setter int maxHealth;
    private @Getter @Setter double damageMultiplier;


    @Builder
    public Wizard(){
        this.setHp(100);
        this.setMaxHealth(100);
        this.setDamageMultiplier(1);


    }



    public void defend(){

    }


}
