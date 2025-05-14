package codo;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CarMileageTest {
    @Test
    public void testTooSmall() {
        assertEquals(0, CarMileage.isInteresting(3, new int[]{1337, 256}));
    }

    @Test
    public void testAlmostAwesome() {
//        assertEquals(1, CarMileage.isInteresting(1336, new int[]{1337, 256}));
        assertEquals(1, CarMileage.isInteresting(98, new int[]{}));
    }

    @Test
    public void testAwesome() {
//        assertEquals(2, CarMileage.isInteresting(1337, new int[]{1337, 256}));
        assertEquals(1, CarMileage.isInteresting(80084, new int[]{80085}));
        assertEquals(2, CarMileage.isInteresting(80085, new int[]{80085}));
    }

    @Test
    public void testIncrementing() {
        assertEquals(1, CarMileage.isInteresting(1232, new int[]{}));
        assertEquals(1, CarMileage.isInteresting(1234567889, new int[]{}));
        assertEquals(2, CarMileage.isInteresting(1234, new int[]{}));
        assertEquals(2, CarMileage.isInteresting(1234567890, new int[]{}));
    }

    @Test
    public void testDecrementing() {
        assertEquals(1, CarMileage.isInteresting(4320, new int[]{}));
        assertEquals(1, CarMileage.isInteresting(876543209, new int[]{}));
        assertEquals(2, CarMileage.isInteresting(4321, new int[]{}));
        assertEquals(2, CarMileage.isInteresting(876543210, new int[]{}));
    }

    @Test
    public void testPalindrome() {
        assertEquals(1, CarMileage.isInteresting(1220, new int[]{}));
        assertEquals(1, CarMileage.isInteresting(1234320, new int[]{}));
        assertEquals(2, CarMileage.isInteresting(1221, new int[]{}));
        assertEquals(2, CarMileage.isInteresting(1234321, new int[]{}));
    }

    @Test
    public void testFarNotInteresting() {
        assertEquals(0, CarMileage.isInteresting(11208, new int[]{1337, 256}));
    }

    @Test
    public void testAlmostInteresting() {
        assertEquals(1, CarMileage.isInteresting(99, new int[]{1337, 256}));
        assertEquals(1, CarMileage.isInteresting(8999, new int[]{1337, 256}));
        assertEquals(1, CarMileage.isInteresting(4442, new int[]{1337, 256}));
        assertEquals(1, CarMileage.isInteresting(11209, new int[]{1337, 256}));
    }

    @Test
    public void testInteresting() {
        assertEquals(2, CarMileage.isInteresting(100, new int[]{1337, 256}));
        assertEquals(2, CarMileage.isInteresting(9000, new int[]{1337, 256}));
        assertEquals(2, CarMileage.isInteresting(4444, new int[]{1337, 256}));
//        assertEquals(2, CarMileage.isInteresting(11211, new int[]{1337, 256}));
    }

    @Test
    public void orderedYetStillBoringTest() {
        assertEquals(0, CarMileage.isInteresting(1590, new int[]{1337, 256}));
    }
}
