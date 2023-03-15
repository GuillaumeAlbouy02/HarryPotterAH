package org.HarryPotter.Characters.spells;

public class Spell extends AbstractSpell{

    private String name;

    public Spell(String name){
        this.name=name;
    }
    @Override
    public String toString() {
        return name;
    }
}
