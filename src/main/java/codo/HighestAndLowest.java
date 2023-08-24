package codo;

import java.util.ArrayList;
import java.util.Collections;

public class HighestAndLowest {

    public static String highAndLow(String numbers) {
        String[] splits = numbers.split(" ");
        ArrayList<Integer> integers = new ArrayList<>();

        for (String split : splits) {
            integers.add(Integer.parseInt(split));
        }

        Collections.sort(integers);
        Integer low = integers.get(0);
        Integer high = integers.get(integers.size()-1);

        for (int number : integers) {
            if (number > high) {
                high = number;
            } else if (number < low) {
                low = number;
            }
        }
        return high + " " + low;
    }
}
