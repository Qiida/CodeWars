package codo;

import java.util.HashMap;

public class CountingDuplicates {
    public static int duplicateCount(String text) {
        HashMap<Character, Integer> characterShortHashMap = new HashMap<>();

        for (Character character : text.toLowerCase().toCharArray()) {
            if (!characterShortHashMap.containsKey(character)) {
                characterShortHashMap.put(character, 1);
            } else {
                Integer value = characterShortHashMap.get(character);
                characterShortHashMap.replace(character, value, ++value);
            }
        }

        int resultCounter = 0;
        for (Integer value : characterShortHashMap.values()) {
            if (value > 1) {
                resultCounter++;
            }
        }
        return resultCounter;
    }
}