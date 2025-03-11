package codo;

import java.util.HashSet;

public class Isogram {
    public static boolean isIsogram(String str) {
        final HashSet<Character> hasSeen = new HashSet<>();
        String lowerStr = str.toLowerCase();
        for (Character c : lowerStr.toCharArray()) {
            if (hasSeen.contains(c)) {
                return false;
            } else {
                hasSeen.add(c);
            }
        }
        return true;
    }
}
