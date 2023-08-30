package codo;

import java.util.HashMap;

public class PangramChecker {
    private HashMap<Integer, Boolean> letterFoundHashmap;

    public PangramChecker() {
        initializeLetterFoundHashmap();
    }

    private void initializeLetterFoundHashmap() {
        letterFoundHashmap = new HashMap<>();
        for (int key=97; key<123; key++) {
            letterFoundHashmap.put(key, Boolean.FALSE);
        }
    }
    public boolean check(String sentence) {
        resetLetterWasFoundHashmap();
        for (Character character : sentence.toLowerCase().toCharArray()) {
            checkCharacter(character);
        }
        return allLettersAreFound();
    }

    private void checkCharacter(Character character) {
        Integer characterInt = (int) character;
        if (letterFoundHashmap.containsKey(characterInt)) {
            if (!letterFoundHashmap.get(characterInt)) {
                letterFoundHashmap.replace(characterInt, false, true);
            }
        }
    }

    private boolean allLettersAreFound() {
        for (Boolean letterWasFound : letterFoundHashmap.values()) {
            if (!letterWasFound) {
                return false;
            }
        }
        return true;
    }

    private void resetLetterWasFoundHashmap() {
        for (Integer key : letterFoundHashmap.keySet()) {
            Boolean letterWasFound = letterFoundHashmap.get(key);
            if (letterWasFound) {
                letterFoundHashmap.replace(key, true, false);
            }
        }
    }
}
