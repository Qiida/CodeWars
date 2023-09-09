package codo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.util.stream.Collectors.joining;

public class SnailTest {

    @Test
    public void SnailTestGoRight() {
        int[][] array
                = {{}};
        int[] position = {0 , 0};
        Snail.snail(array);

    }



    @Test
    public void SnailTest1() {
        int[][] array
                = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        int[] r = {1, 2, 3, 6, 9, 8, 7, 4, 5};
        test(array, r);
    }

    public String int2dToString(int[][] a) {
        return Arrays.stream(a).map(Arrays::toString).collect(joining("\n"));
    }

    public void test(int[][] array, int[] result) {
        String text = int2dToString(array) + " should be sorted to " + Arrays.toString(result);
        System.out.println(text);
        Assertions.assertArrayEquals( result, Snail.snail(array));
    }


}