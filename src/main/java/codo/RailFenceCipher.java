package codo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Objects;

public class RailFenceCipher {

    public static String encode(String toEncode, int n) {
        if (Objects.equals(toEncode, "")) {
            return toEncode;
        }
        ArrayList<Character[]> railFences = initializeRailFences(toEncode, n);
        buildEncoderRailFences(railFences, toEncode);
        Character[][] encoderMatrix = buildEncoderMatrix(n, railFences);
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

    private static ArrayList<Character[]> initializeRailFences(String string, int n) {
        ArrayList<Character[]> railFences = new ArrayList<>();
        int numRemainingLetters = string.length();
        railFences.add(new Character[n]);
        numRemainingLetters -= n;
        while (numRemainingLetters > 0) {
            railFences.add(new Character[n - 1]);
            numRemainingLetters -= n - 1;
        }
        return railFences;
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

    private static Character[][] buildEncoderMatrix(int n, ArrayList<Character[]> railFences) {
        int railFencesSize = railFences.size();
        Character[][] encoderMatrix = new Character[n][railFencesSize];
        for (int r = 0; r < railFencesSize; r++) {
            Character[] railFence = railFences.get(r);
            Character[] workingRailFence = new Character[n];
            if (railFence.length == n) {
                workingRailFence = railFence;
            } else if (r % 2 == 0) {
                for (int j = 0; j < n; j++) {
                    if (j == 0) {
                        workingRailFence[j] = null;
                    } else {
                        workingRailFence[j] = railFence[j - 1];
                    }
                }
            } else {
                Character[] reversedRailFence = new Character[n];
                int index = 0;
                for (int j = railFence.length - 1; j >= 0; j--) {
                    reversedRailFence[index++] = railFence[j];
                }
                for (int j = 0; j < n; j++) {
                    if (j == n - 1) {
                        workingRailFence[j] = null;
                    } else {
                        workingRailFence[j] = reversedRailFence[j];
                    }
                }
            }
            for (int j = 0; j < n; j++) {
                encoderMatrix[j][r] = workingRailFence[j];
            }
        }
        return encoderMatrix;
    }

    public static String decode(String toDecode, int n) {
        if (Objects.equals(toDecode, "")) {
            return toDecode;
        }
        ArrayList<Character[]> railFences = initializeRailFences(toDecode, n);
        Character[][] decoderMatrix = buildDecoderMatrix(toDecode, n, railFences);
        buildDecoderRailFences(n, decoderMatrix, railFences);
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

    private static void buildDecoderRailFences(int n, Character[][] decoderMatrix, ArrayList<Character[]> railFences) {
        int rail = 0;
        for (int x = 0; x < decoderMatrix[0].length; x++) {
            int row;
            if (x % 2 == 0) {
                row = 0;
                for (int y = 0; y < n; y++) {
                    Character c = decoderMatrix[y][x];
                    if (c != null) {
                        railFences.get(rail)[row++] = c;
                    }
                }
            } else {
                row = n - 2;
                for (int y = 0; y < n; y++) {
                    Character c = decoderMatrix[y][x];
                    if (c != null) {
                        railFences.get(rail)[row--] = c;
                    }
                }
            }
            rail++;
        }
    }
    private static Character[][] buildDecoderMatrix(String toDecode, int n, ArrayList<Character[]> railFences) {
        Character[][] decoderMatrix = new Character[n][railFences.size()];
        char[] characters = toDecode.toCharArray();
        int i = 0;
        int toDecodeLength = toDecode.length();
        int overheadOfLastRailFence = 0;
        for (Character[] railFence : railFences) {
            overheadOfLastRailFence += railFence.length;
        }
        overheadOfLastRailFence -= toDecodeLength;
        int overheadIndex = n - overheadOfLastRailFence;
        for (int y = 0; y < decoderMatrix.length; y++) {
            for (int x = 0; x < decoderMatrix[0].length; x++) {
                if (i < toDecodeLength) {
                    if (x == 0) {
                        decoderMatrix[y][x] = Character.valueOf(characters[i++]);
                    } else if (
                            ((x % 2 == 0) && ((y == 0) || (x == decoderMatrix[0].length - 1 && y >= overheadIndex)))
                                    || ((x % 2 != 0) && ((y == n - 1) || (x == decoderMatrix[0].length - 1 && y <= overheadOfLastRailFence - 1)))
                    ) {
                        decoderMatrix[y][x] = null;
                    } else {
                        decoderMatrix[y][x] = Character.valueOf(characters[i++]);
                    }
                }
            }
        }
        return decoderMatrix;
    }
}