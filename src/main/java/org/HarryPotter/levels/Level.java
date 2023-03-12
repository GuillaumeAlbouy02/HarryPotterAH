package org.HarryPotter.levels;

import lombok.Getter;
import lombok.Setter;
import org.HarryPotter.Characters.ennemies.Boss;
import org.HarryPotter.Characters.ennemies.Enemy;

public class Level {
    private @Getter Enemy[] enemies;
    private @Getter Boss[] bosses;
    private @Getter Cell[][] map;

    public Level(){

    }



}
