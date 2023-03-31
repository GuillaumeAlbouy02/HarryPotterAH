
import org.HarryPotter.Characters.Character;
import org.HarryPotter.Characters.ennemies.Boss;
import org.HarryPotter.Characters.ennemies.Enemy;
import org.HarryPotter.Characters.spells.Spell;
import org.HarryPotter.Characters.wizards.Wizard;
import org.HarryPotter.display.Display;
import org.HarryPotter.levels.Level;
import org.junit.jupiter.api.Test ;
import static org.junit.jupiter.api.Assertions.*;
public class SpellTest {
    @Test
    void testSpellEffect2(){
        Display ds = new Display();
        Wizard player = new Wizard();
        Enemy enemy = new Enemy("testEnemy", 100, 50, 100);
        int expected = enemy.getHp()-(int)(30*player.getDamageMultiplier());
        Level level = new Level(new Enemy[]{enemy}, null);
        player.setKnownSpells(new Spell[]{new Spell("Spell", 100, 2)});
        player.getKnownSpells()[0].use(enemy,level,player,ds);
        assertEquals(expected, enemy.getHp());

    }
    @Test
    void testSpellEffect4(){
        Display ds = new Display();
        Wizard player = new Wizard();
        Enemy enemy = new Enemy("testEnemy", 100, 50, 100);
        int expected = enemy.getHp()-(int)(50* player.getDamageMultiplier());
        Level level = new Level(new Enemy[]{enemy}, null);
        player.setKnownSpells(new Spell[]{new Spell("Spell", 100, 4)});
        player.getKnownSpells()[0].use(enemy,level,player,ds);
        assertEquals(expected, enemy.getHp());

    }

    @Test
    void testSpellEffect3(){
        Display ds = new Display();
        Wizard player = new Wizard();
        Enemy enemy = new Enemy("testEnemy", 100, 50, 100);
        Character[][] expected = new Character[][]{{null}, {null}};
        Level level = new Level(new Enemy[]{enemy}, new Boss[]{new Boss("test",100,10,100)});
        player.setKnownSpells(new Spell[]{new Spell("Spell", 100, 3)});
        player.getKnownSpells()[0].use(enemy,level,player,ds);
        Character[][] result = new Character[][]{level.getEnemies(), level.getBosses()};
        assertArrayEquals(expected, result);

    }

    @Test
    void testSpellEffect1(){
        Display ds = new Display();
        Wizard player = new Wizard();
        Enemy enemy = new Enemy("testEnemy", 100, 50, 100);
        Level level = new Level(new Enemy[]{enemy}, new Boss[]{new Boss("test",100,10,100)});
        player.setKnownSpells(new Spell[]{new Spell("Spell", 100, 1)});
        player.getKnownSpells()[0].use(enemy,level,player,ds);
        assertEquals(0, enemy.getHp());

    }


}
