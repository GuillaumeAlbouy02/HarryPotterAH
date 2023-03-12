package org.HarryPotter.Characters;

import lombok.Getter;
import lombok.Setter;
import org.HarryPotter.Position;

public abstract class Character {
    private  @Getter @Setter Position position;
    private @Setter @Getter String name;
    private int hp;

    public void attack(java.lang.Character character){

    }
}
