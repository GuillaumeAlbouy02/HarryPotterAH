package org.HarryPotter.Characters.ennemies;

import lombok.Getter;
import lombok.Setter;
import org.HarryPotter.Characters.Character;
import org.HarryPotter.Characters.wizards.Wizard;

public abstract class AbstractEnemy extends Character {
    private @Getter @Setter int damage;
    private @Getter @Setter int precision;


}
