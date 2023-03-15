package org.HarryPotter.levels;

import lombok.Getter;
import lombok.Setter;
import org.HarryPotter.Characters.ennemies.AbstractEnemy;
import org.HarryPotter.Characters.ennemies.Boss;
import org.HarryPotter.Characters.ennemies.Enemy;

public class Level {
    private @Getter Enemy[] enemies;
    private @Getter Boss[] bosses;
    private @Getter Cell[][] map;

    private @Getter boolean won;


    public Level(Enemy[] enemies, Boss[] bosses){
        this.enemies=enemies;
        this.bosses=bosses;
        this.won=false;

    }

    public AbstractEnemy getCurrentEnemy() {
        AbstractEnemy enemy = null;
        if (enemies != null) {
            for (int i = enemies.length - 1; i >= 0; i--) {
                if (enemies[i] != null) {
                    enemy = enemies[i];

                }
            }
        }
        else if(bosses!=null){
            for (int i = bosses.length - 1; i >= 0; i--) {
                if (bosses[i] != null) {
                    enemy = bosses[i];

                }
            }
        }
        return enemy;
    }





}