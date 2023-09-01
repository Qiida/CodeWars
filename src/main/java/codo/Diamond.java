package codo;

public class Diamond {
    public static String print(int size) {
        if (size % 2 == 0 || size < 0) {
            return null;
        }


        StringBuilder diamond = new StringBuilder();
        int middle = size / 2;

        for (int layer=0; layer<middle; layer++) {
                diamond.append(" ".repeat(middle-layer));
                diamond.append("*".repeat(1+layer*2));
                diamond.append("\n");
        }
        diamond.append("*".repeat(size));
        diamond.append("\n");
        String[] diamondLayers = diamond.toString().split("\n");

        for (int i=diamondLayers.length-2; i>=0; i--) {
            diamond.append(diamondLayers[i]).append("\n");
        }
        // TODO your code here
        return diamond.toString();
    }


}
