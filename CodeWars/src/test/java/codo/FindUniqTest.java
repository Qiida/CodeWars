package codo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FindUniqTest {
    private double precision = 0.0000000000001;

    @Test
    public void sampleTestCases() {
        Assertions.assertEquals(1.0, FindUnique.findUniq(new double[]{0, 1, 0}), precision);
        Assertions.assertEquals(2.0, FindUnique.findUniq(new double[]{1, 1, 1, 2, 1, 1}), precision);
        Assertions.assertEquals(2.0, FindUnique.findUniq(new double[]{2, 1, 1, 2, 1, 1}), precision);
    }
}