package codo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DiamondTest {
    @Test
    public void testDiamond3() {
        StringBuffer expected = new StringBuffer();
        expected.append(" *\n");
        expected.append("***\n");
        expected.append(" *\n");

        Assertions.assertEquals(expected.toString(), Diamond.print(3));
    }

    @Test
    public void testDiamond5() {
        StringBuffer expected = new StringBuffer();
        expected.append("  *\n");
        expected.append(" ***\n");
        expected.append("*****\n");
        expected.append(" ***\n");
        expected.append("  *\n");

        Assertions.assertEquals(expected.toString(), Diamond.print(5));
    }

    @Test
    public void testDiamond1() {
        StringBuffer expected = new StringBuffer();
        expected.append("*\n");
        Assertions.assertEquals(expected.toString(), Diamond.print(1));
    }

    @Test
    public void testDiamond0() {
        Assertions.assertNull(Diamond.print(0));
    }

    @Test
    public void testDiamondMinus2() {
        Assertions.assertNull(Diamond.print(-2));
    }

    @Test
    public void testDiamond2() {
        Assertions.assertNull(Diamond.print(2));
    }
}