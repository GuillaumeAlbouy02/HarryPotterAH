package org.HarryPotter.Characters;

import lombok.Getter;
import lombok.Setter;
import org.HarryPotter.Position;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Character {
    private  @Getter @Setter Position position;
    private @Setter @Getter String name;
    private @Getter @Setter int hp;

    public void attack(Character character, int damage, int precision) {
        if (ThreadLocalRandom.current().nextInt(0, 101) <= precision) {
            character.setHp(character.getHp() - damage);
        }
    }
}
