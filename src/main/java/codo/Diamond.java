package codo;

public class Diamond {
    public static String print(int size) {

        if (size % 2 == 0 || size < 0) {
            return null;
        }

        StringBuilder diamond = new StringBuilder();
        int middle = size / 2;
        for (int i=0; i<middle; i++) {
                diamond.append(" ".repeat(middle-i));
                diamond.append("*".repeat(1+i*2));
                diamond.append("\n");
        }

        diamond.append("*".repeat(size)).append("\n");

        String[] diamondLayers = diamond.toString().split("\n");
        for (int i=diamondLayers.length-2; i>=0; i--) {
            diamond.append(diamondLayers[i]).append("\n");
        }
        return diamond.toString();
    }

}
