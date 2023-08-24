package codo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HighestAndLowestTest {

    @Test
    public void test1() {
        Assertions.assertEquals("42 -9", HighestAndLowest.highAndLow("8 3 -5 42 -1 0 0 -9 4 7 4 -4"));
    }
    @Test
    public void test2() {
        Assertions.assertEquals("3 1", HighestAndLowest.highAndLow("1 2 3"));
    }
    @Test
    public void test3() {
        Assertions.assertEquals("12 -50", HighestAndLowest.highAndLow("4 12 2 -50 11 2 3"));
    }
}
