package codo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Objects;

public class RailFenceCipher {

    public static String encode(String stringToEncode, int rails) {
        if (Objects.equals(stringToEncode, "")) {
            return stringToEncode;
        }
        ArrayList<Character[]> railFences = initializeRailFences(stringToEncode, rails);
        buildEncoderRailFences(railFences, stringToEncode);
        Character[][] encoderMatrix = buildEncoderMatrix(rails, railFences);
        StringBuilder encoded = new StringBuilder();
        for (Character[] characters : encoderMatrix) {
            for (int j = 0; j < encoderMatrix[0].length; j++) {
                Character c = characters[j];
                if (c != null) {
                    encoded.append(c);
                }
            }
        }
        return encoded.toString();
    }

    private static Character[][] buildEncoderMatrix(int rails, ArrayList<Character[]> railFences) {
        Character[][] encoderMatrix = new Character[rails][railFences.size()];
        for (int r = 0; r < railFences.size(); r++) {
            Character[] railFence = railFences.get(r);
            Character[] workingRailFence = new Character[rails];
            if (railFence.length == rails) {
                workingRailFence = railFence;
            } else if (r % 2 == 0) {
                for (int j = 0; j < rails; j++) {
                    if (j == 0) {
                        workingRailFence[j] = null;
                    } else {
                        workingRailFence[j] = railFence[j - 1];
                    }
                }
            } else {
                Character[] reversedRailFence = new Character[rails];
                int index = 0;
                for (int j = railFence.length - 1; j >= 0; j--) {
                    reversedRailFence[index++] = railFence[j];
                }
                for (int j = 0; j < rails; j++) {
                    if (j == rails - 1) {
                        workingRailFence[j] = null;
                    } else {
                        workingRailFence[j] = reversedRailFence[j];
                    }
                }
            }
            for (int j = 0; j < rails; j++) {
                encoderMatrix[j][r] = workingRailFence[j];
            }
        }
        return encoderMatrix;
    }

    private static void buildEncoderRailFences(ArrayList<Character[]> railFences, String stringToEncode) {
        Deque<Character> characterStack = new ArrayDeque<>();
        char[] chars = stringToEncode.toCharArray();
        for (int i = 0; i < stringToEncode.length(); i++) {
            characterStack.add(chars[i]);
        }
        for (Character[] railFence : railFences) {
            for (int i = 0; i < railFence.length; i++) {
                if (!characterStack.isEmpty()) {
                    railFence[i] = characterStack.pop();
                }
            }
        }
    }

    private static ArrayList<Character[]> initializeRailFences(String stringToEncode, int rails) {
        ArrayList<Character[]> railFences = new ArrayList<>();
        int numRemainingLetters = stringToEncode.length();
        railFences.add(new Character[rails]);
        numRemainingLetters -= rails;
        while (numRemainingLetters > 0) {
            railFences.add(new Character[rails - 1]);
            numRemainingLetters -= rails - 1;
        }
        return railFences;
    }

    static String decode(String stringToDecode, int rails) {
        if (Objects.equals(stringToDecode, "")) {
            return stringToDecode;
        }
        ArrayList<Character[]> railFences = initializeRailFences(stringToDecode, rails);
        Character[][] decoderMatrix = buildDecoderMatrix(stringToDecode, rails, railFences);
        buildDecoderRailFences(rails, decoderMatrix, railFences);
        StringBuilder decoded = new StringBuilder();
        for (Character[] railFence : railFences) {
            for (Character c : railFence) {
                if (c != null) {
                    decoded.append(c);
                }
            }
        }
        return decoded.toString();
    }

    private static void buildDecoderRailFences(int rails, Character[][] decoderMatrix, ArrayList<Character[]> railFences) {
        int rail = 0;
        for (int x = 0; x < decoderMatrix[0].length; x++) {
            int row;
            if (x % 2 == 0) {
                row = 0;
                for (int y = 0; y < rails; y++) {
                    Character c = decoderMatrix[y][x];
                    if (c != null) {
                        railFences.get(rail)[row++] = c;
                    }
                }
            } else {
                row = rails - 2;
                for (int y = 0; y < rails; y++) {
                    Character c = decoderMatrix[y][x];
                    if (c != null) {
                        railFences.get(rail)[row--] = c;
                    }
                }
            }
            rail++;
        }
    }

    private static Character[][] buildDecoderMatrix(String stringToDecode, int rails, ArrayList<Character[]> railFences) {
        Character[][] decoderMatrix = new Character[rails][railFences.size()];
        char[] characters = stringToDecode.toCharArray();
        int index = 0;
        for (int y = 0; y < decoderMatrix.length; y++) {
            for (int x = 0; x < decoderMatrix[0].length; x++) {
                if (index < stringToDecode.length()) {
                    if (x == 0) {
                        decoderMatrix[y][x] = characters[index++];
                    } else if (
                            (x % 2 == 0 && y == 0) ||
                                    (x % 2 != 0 && y == rails - 1)
                    ) {
                        decoderMatrix[y][x] = null;
                    } else {
                        decoderMatrix[y][x] = characters[index++];
                    }
                }
            }
        }
        return decoderMatrix;
    }
}
