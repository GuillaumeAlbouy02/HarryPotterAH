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


    @Builder
    public Wizard(){
        this.setHp(100);


    }

    public void addSpell(Spell spell){
        Spell[] newSpells = new Spell[knownSpells.length+1];
        if(this.knownSpells != null){
            for(int i=0;i<knownSpells.length;i++){
                newSpells[i] = knownSpells[i];
            }
            newSpells[knownSpells.length] = spell;
        }
    }

    public void defend(){

    }


}
