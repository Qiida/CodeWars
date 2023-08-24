package codo;

import java.util.Arrays;

public class FindUnique {
    public static double findUniq(double[] doubles) {
        double[] filtered = Arrays.stream(doubles).filter(d -> d != doubles[0]).toArray();
        if (filtered.length == 1) {
            return filtered[0];
        } else {
            return doubles[0];
        }
    }
}
