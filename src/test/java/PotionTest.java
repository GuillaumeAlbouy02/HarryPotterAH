import org.HarryPotter.Characters.ennemies.Boss;
import org.HarryPotter.Characters.ennemies.Enemy;
import org.HarryPotter.Characters.spells.Spell;
import org.HarryPotter.Characters.wizards.Potion;
import org.HarryPotter.Characters.wizards.Wizard;
import org.HarryPotter.display.Display;
import org.HarryPotter.levels.Level;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PotionTest {


    @Test
    void testPotionHeal(){
        Display ds = new Display();
        Wizard player = new Wizard();
        Enemy enemy = new Enemy("testEnemy", 100, 50, 100);
        Level level = new Level(new Enemy[]{enemy}, new Boss[]{new Boss("test",100,10,100)});
        player.setPotions(new Potion[]{new Potion("testPotion", 0, 10)});
        player.getPotions()[0].use(enemy,player,ds);
        assertEquals(100, player.getHp());
        player.setHp(90);
        player.getPotions()[0].use(enemy,player,ds);
        assertEquals(100, player.getHp());


    }

    @Test
    void testPotionKill(){
        Display ds = new Display();
        Wizard player = new Wizard();
        Enemy enemy = new Enemy("testEnemy", 100, 50, 100);
        Level level = new Level(new Enemy[]{enemy}, new Boss[]{new Boss("test",100,10,100)});
        player.setPotions(new Potion[]{new Potion("testPotion", 1, 10)});
        player.getPotions()[0].use(enemy,player,ds);
        assertEquals(0, enemy.getHp());



    }
    @Test
    void testPotionUse(){
        Display ds = new Display();
        Wizard player = new Wizard();
        Enemy enemy = new Enemy("testEnemy", 100, 50, 100);
        Level level = new Level(new Enemy[]{enemy}, new Boss[]{new Boss("test",100,10,100)});
        player.setPotions(new Potion[]{new Potion("testPotion", 1, 10)});
        player.getPotions()[0].use(enemy,player,ds);
        assertEquals(9, player.getPotions()[0].getUseNumber());
        for(int i=0;i<10;i++){
            player.getPotions()[0].use(enemy,player,ds);
        }
        assertEquals(0, player.getPotions()[0].getUseNumber());



    }
}
