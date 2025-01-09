package codo;

import java.util.Vector;

public class RangeExtraction {
    public static String rangeExtraction(int[] arr) {
        StringBuilder rangeStringBuilder = new StringBuilder();
        Vector<Integer> range = new Vector<>();
        range.add(arr[0]);
        for (int i=1; i<arr.length; i++) {
            int current = arr[i];
            int last = range.get(range.size()-1);
            if (current == last + 1) {
                range.add(current);
            } else if (range.size() > 1) {
                addRangeToStringBuilder(range, rangeStringBuilder);
                if (i != arr.length - 1) {
                    rangeStringBuilder.append(",");
                }
                range = new Vector<>();
                range.add(current);
            } else {
                rangeStringBuilder.append(last);
                if (i != arr.length - 1) {
                    rangeStringBuilder.append(",");
                }
                range.remove(range.size()-1);
                range.add(current);
            }
        }
        if (range.size() > 1) {
            addRangeToStringBuilder(range, rangeStringBuilder);
        } else {
            rangeStringBuilder.append(",").append(range.get(0));
        }
        return rangeStringBuilder.toString();
    }

    private static void addRangeToStringBuilder(Vector<Integer> range, StringBuilder rangeStringBuilder) {
        int lastValue = range.get(range.size()-1);
        int firstValue = range.get(0);
        rangeStringBuilder.append(firstValue);
        if (range.size() > 2) {
            rangeStringBuilder.append("-");
        } else {
            rangeStringBuilder.append(",");
        }
        rangeStringBuilder.append(lastValue);
    }
}
