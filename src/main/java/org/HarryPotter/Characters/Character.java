package org.HarryPotter.Characters;

import lombok.Getter;
import lombok.Setter;

public abstract class Character {
    private @Setter @Getter String name;
    private int hp;

    public void attack(java.lang.Character character){

    }
}
