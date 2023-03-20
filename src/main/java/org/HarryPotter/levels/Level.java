package org.HarryPotter.levels;

import lombok.Getter;
import lombok.Setter;
import org.HarryPotter.Characters.ennemies.AbstractEnemy;
import org.HarryPotter.Characters.ennemies.Boss;
import org.HarryPotter.Characters.ennemies.Enemy;

import java.util.Scanner;

public class Level {
    private @Getter @Setter Enemy[] enemies;
    private @Getter @Setter Boss[] bosses;
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
        if(bosses!=null && enemy==null){
            for (int i = bosses.length - 1; i >= 0; i--) {
                if (bosses[i] != null) {
                    enemy = bosses[i];

                }
            }
        }
        return enemy;
    }

public void killCurrentEnemy(){
        int enemy = -1;
    if (enemies != null) {
        for (int i = enemies.length - 1; i >= 0; i--) {
            if (enemies[i] != null) {
                enemy = i;

            }
        }
        enemies[enemy] = null;
    }
    else if(bosses!=null){
        for (int i = bosses.length - 1; i >= 0; i--) {
            if (bosses[i] != null) {
                enemy = i;

            }
        }
        bosses[enemy] = null;
    }

}

public void display(int level, String state){
    Scanner fileSc = new Scanner(getClass().getResourceAsStream("/"+String.valueOf(level)+state+"Text.txt"));
    while (fileSc.hasNextLine()){
        System.out.println(fileSc.nextLine());
    }
}


}
