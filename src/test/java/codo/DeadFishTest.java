package codo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeadFishTest {
    @Test
    public void exampleTests() {
        Assertions.assertArrayEquals(new int[] {8, 64}, DeadFish.parse("iiisdoso"));
        Assertions.assertArrayEquals(new int[] {8, 64, 3600}, DeadFish.parse("iiisdosodddddiso"));
    }
}