package codo;

public class Diamond {
    public static String print(int size) {
        if (size % 2 == 0 || size < 0) {
            return null;
        }


        StringBuilder diamond = new StringBuilder();
        for (int layer=0; layer<size; layer++) {
            diamond.append(buildDiamondLayer(layer, size));
        }

        // TODO your code here
        return diamond.toString();
    }

    private static String buildDiamondLayer(int layer, int size) {
        StringBuilder diamondLayer = new StringBuilder();
        int middle = size / 2;

        for (int i=0; i<middle-(layer); i++) {
            diamondLayer.append(" ");
        }

        for (int i=middle-(layer); i<=middle+(layer); i++) {
            diamondLayer.append("*");
        }

        for (int i=0; i<middle-(layer); i++) {
            diamondLayer.append(" ");
        }
        diamondLayer.append("\n");
        return diamondLayer.toString();
    }
}
