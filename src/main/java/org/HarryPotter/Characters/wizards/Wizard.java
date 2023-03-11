package org.HarryPotter.Characters.wizards;
import lombok.Builder;
import lombok.Getter;
import org.HarryPotter.Characters.Character;
import org.HarryPotter.Characters.spells.Spell;

import java.lang.annotation.Target;

public class Wizard extends Character {

    private @Getter Pet pet;
    private @Getter Wand wand;
    private @Getter House house;
    private @Getter Spell[] knownSpells;
    private @Getter Potion[] potions;


    @Builder
    public Wizard(int hp, String name, Pet pet){


    }

    public void defend(){

    }


}
