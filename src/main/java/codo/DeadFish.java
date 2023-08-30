package codo;



public class DeadFish {
    public static int[] parse(String data) {
        int size = getSize(data);
        int value = 0;

        int[] output = new int[size];
        int outputIndex = 0;
        for (char c : data.toCharArray()) {
            value = computeNewValue(c, value);
            if (c == 'o') {
                output[outputIndex++] = value;
            }
        }
        return output;
    }


    private static int computeNewValue(char command, int value) {
        return switch (command) {
            case 'i' -> ++value;
            case 'd' -> --value;
            case 's' -> value * value;
            default -> value;
        };
    }

    private static int getSize(String data) {
        int size = 0;
        for (char c : data.toCharArray()) {
            if (c == 'o') {
                size++;
            }
        }
        return size;
    }
}