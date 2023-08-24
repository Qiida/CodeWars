package codo;

import java.util.ArrayList;
import java.util.List;

class SumDigPower {

    public static List<Long> sumDigPow(long a, long b) {
        List<Long> list = new ArrayList<>();
        for (long i=a; i<b; i++) {
            String[] digitStrings = Long.toString(i).split("");
            long sum = 0L;
            for (int j=0; j<digitStrings.length; j++) {
                String digitString = digitStrings[j];
                long digitLong = Long.parseLong(digitString);
                sum += Math.pow(digitLong, j+1);
            }
            if (sum == i) {
                list.add(i);
            }
        }

        return list;
    }
}