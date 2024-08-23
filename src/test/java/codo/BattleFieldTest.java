package codo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BattleFieldTest {
    private static final int[][] BATTLEFIELD_1 = {
            {1, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {1, 0, 1, 0, 0, 0, 0, 0, 1, 0},
            {1, 0, 1, 0, 1, 1, 1, 0, 1, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    private static final int[][] BATTLEFIELD_2 = {
            {1, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {1, 0, 1, 0, 0, 0, 0, 0, 1, 0},
            {1, 0, 1, 0, 1, 1, 1, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    private static final int[][] BATTLEFIELD_3 = {
            {1, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {1, 0, 1, 0, 0, 0, 0, 0, 1, 0},
            {1, 0, 1, 0, 1, 1, 1, 0, 1, 0},
            {1, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    private static final int[][] BATTLEFIELD_4 = {
            {1,0,0,0,0,0,0,0,0,0},
            {1,0,0,0,0,0,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,0},
            {0,0,1,0,0,0,0,0,1,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,1,0,1,1,1},
            {0,0,1,1,0,0,0,0,0,0},
            {0,0,0,0,0,1,1,0,0,0},
            {0,0,1,0,0,0,0,0,0,0},
            {0,0,1,0,0,0,1,0,0,0}
    };

    private static final int[][] BATTLEFIELD_5 = {
            {0,0,0,0,0,0,0,0,0,0},
            {0,1,0,0,0,0,0,0,0,0},
            {0,1,0,1,1,0,0,0,0,1},
            {0,1,0,0,0,0,0,1,0,0},
            {0,1,0,0,0,0,0,1,0,1},
            {0,0,0,1,0,0,0,0,0,1},
            {1,1,0,0,0,1,0,0,0,1},
            {0,0,0,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,1,1,1,0}
    };

    @Test
    public void SampleTest() {
        Assertions.assertTrue(BattleField.fieldValidator(BATTLEFIELD_1));
        Assertions.assertFalse(BattleField.fieldValidator(BATTLEFIELD_2));
        Assertions.assertFalse(BattleField.fieldValidator(BATTLEFIELD_3));
        Assertions.assertTrue(BattleField.fieldValidator(BATTLEFIELD_4));
        Assertions.assertTrue(BattleField.fieldValidator(BATTLEFIELD_5));
    }


    @Test
    public void getShipTypeTest() {
        Assertions.assertEquals("battleship", BattleField.getShipType(BATTLEFIELD_2, 4, 2));
        Assertions.assertEquals("battleship", BattleField.getShipType(BATTLEFIELD_2, 0, 0));
        Assertions.assertEquals("cruiser", BattleField.getShipType(BATTLEFIELD_2, 4, 5));
        Assertions.assertEquals("destroyer", BattleField.getShipType(BATTLEFIELD_2, 5, 0));
        Assertions.assertEquals("submarine", BattleField.getShipType(BATTLEFIELD_2, 8, 1));
    }

    @Test
    public void checkVonNeumannTest() {
        Assertions.assertFalse(BattleField.checkVonNeumann(BATTLEFIELD_3, 5, 2));
        Assertions.assertTrue(BattleField.checkVonNeumann(BATTLEFIELD_3, 0, 0));
        Assertions.assertTrue(BattleField.checkVonNeumann(BATTLEFIELD_3, 7, 8));
    }
}
