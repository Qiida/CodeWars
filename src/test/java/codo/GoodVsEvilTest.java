package codo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GoodVsEvilTest {
    @Test
    public void testEvilWin() {
        Assertions.assertEquals("Battle Result: Evil eradicates all trace of Good", GoodVsEvil.battle("1 1 1 1 1 1", "1 1 1 1 1 1 1"));
    }

    @Test
    public void testGoodWin() {
        Assertions.assertEquals("Battle Result: Good triumphs over Evil",
                GoodVsEvil.battle("0 0 0 0 0 10", "0 1 1 1 1 0 0"));
    }

    @Test
    public void testTie() {
        Assertions.assertEquals("Battle Result: No victor on this battle field",
                GoodVsEvil.battle("1 0 0 0 0 0", "1 0 0 0 0 0 0"));
    }
}