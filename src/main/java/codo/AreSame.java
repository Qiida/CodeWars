package codo;

import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Math.abs;

public class AreSame {

    public static Boolean comp(int[] a, int[] b) {
        if (a==null) {
            return false;
        }
        if (b==null) {
            return false;
        }
        if (a.length != b.length) {
            return false;
        }
        if (a.length == 0) {
            return true;
        }

        ArrayList<Integer> listA = new ArrayList<>();
        ArrayList<Integer> listB = new ArrayList<>();

        fillLists(a, b, listA, listB);
        Collections.sort(listA);
        Collections.sort(listB);

        for (int i=0; i<listA.size(); i++) {
            Integer valueA = listA.get(i);
            Integer valueB = listB.get(i);
            if (valueA * valueA != valueB) {
                return false;
            }
        }
        return true;
    }

    private static void fillLists(int[] a, int[] b, ArrayList<Integer> listA, ArrayList<Integer> listB) {
        for (int valueA : a) {
            listA.add(abs(valueA));
        }
        for (int valueB : b) {
            listB.add(valueB);
        }
    }
}