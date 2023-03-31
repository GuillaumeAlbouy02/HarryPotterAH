import org.HarryPotter.Characters.ennemies.Boss;
import org.HarryPotter.Characters.ennemies.Enemy;
import org.HarryPotter.Characters.wizards.Potion;
import org.HarryPotter.Characters.wizards.Wizard;
import org.HarryPotter.Game;
import org.HarryPotter.display.Display;
import org.HarryPotter.levels.Level;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharactersTests {
    @Test
    void testAttack(){
        Wizard player = new Wizard();
        Enemy enemy = new Enemy("testEnemy", 100, 50, 100);
        player.attack(enemy,10,100);
        assertEquals(90, enemy.getHp());
        player.attack(enemy,10,0);
        assertEquals(90,enemy.getHp());

    }

    @Test
    void testEndTurnEnemy(){
        Game testGame = new Game();
        testGame.setPlayer(new Wizard());
        Enemy enemy = new Enemy("testEnemy", 0, 50, 100);
        Level level = new Level(new Enemy[]{enemy}, null);
        testGame.setCurrentLevel(level);
        testGame.endTurn();

        assertArrayEquals(testGame.getCurrentLevel().getEnemies(), new Enemy[]{null});

    }

    @Test
    void testEndTurnPlayer(){
        Game testGame = new Game();
        testGame.setPlayer(new Wizard());
        testGame.getPlayer().setHp(0);
        Enemy enemy = new Enemy("testEnemy", 100, 50, 100);
        Level level = new Level(new Enemy[]{enemy}, null);
        testGame.setCurrentLevel(level);
        testGame.endTurn();

        assertEquals(true, testGame.isGameOver());

    }

    @Test
    void testKillEnemy(){

        Enemy enemy = new Enemy("testEnemy", 100, 50, 100);
        Level level = new Level(new Enemy[]{enemy}, null);
        level.killCurrentEnemy(enemy);


        assertEquals(null, level.getEnemies()[0]);
    }

}
