package codo;


public class BattleField {
    final private static int DIM_X = 10;
    final private static int DIM_Y = 10;

    final private static int TARGET_OF_BATTLESHIPS = 1;
    final private static int TARGET_OF_CRUISERS = 2;
    final private static int TARGET_OF_DESTROYERS = 3;
    final private static int TARGET_OF_SUBMARINES = 4;

    final private static int SIZE_OF_BATTLESHIP = 4;
    final private static int SIZE_OF_CRUISER = 3;
    final private static int SIZE_OF_DESTROYER = 2;
    final private static int SIZE_OF_SUBMARINE = 1;


    public static boolean fieldValidator(int[][] field) {
        int numberOfBattleships = 0;
        int numberOfCruisers = 0;
        int numberOfDestroyers = 0;
        int numberOfSubmarines = 0;
        int[][] workingField = prettyPrintAndCopyField(field);
        if (hasCollisions(field)) {
            System.out.println("Invalid Field");
            return false;
        }
        for (int y=0; y<DIM_Y; y++) {
            for (int x=0; x<DIM_X; x++) {
                if (workingField[y][x] == 1) {
                    String shipType = getShipType(workingField, x, y);
                    if (shipType != null) {
                        switch (shipType) {
                            case "battleship" -> numberOfBattleships++;
                            case "cruiser" -> numberOfCruisers++;
                            case "destroyer" -> numberOfDestroyers++;
                            case "submarine" -> numberOfSubmarines++;
                        }
                    } else {
                        System.out.println("Broken at ("+x+","+y+")");
                        return false;
                    }
                    System.out.println("Ship at ("+x+","+y+") -> "+shipType);
                }
            }
        }
        System.out.println(numberOfBattleships+" / "+TARGET_OF_BATTLESHIPS+" Battleships found");
        System.out.println(numberOfCruisers+" / "+TARGET_OF_CRUISERS+" Cruisers found");
        System.out.println(numberOfDestroyers+" / "+TARGET_OF_DESTROYERS+" Destroyers found");
        System.out.println(numberOfSubmarines+" / "+TARGET_OF_SUBMARINES+" Submarines found");
        return numberOfBattleships == TARGET_OF_BATTLESHIPS &&
                numberOfCruisers == TARGET_OF_CRUISERS &&
                numberOfDestroyers == TARGET_OF_DESTROYERS &&
                numberOfSubmarines == TARGET_OF_SUBMARINES;
    }

    private static boolean hasCollisions(int[][] field) {
        for (int y=0; y<DIM_Y; y++) {
            for (int x=0; x<DIM_X; x++) {
                int value = field[y][x];
                if (value == 1) {
                    boolean hasValidNeighbours = checkVonNeumann(field, x, y);
                    if (!hasValidNeighbours) {
                        System.out.println("Collision at ("+x+","+y+")");
                        return true;
                    }
                }

            }
        }
        return false;
    }

    private static int[][] prettyPrintAndCopyField(int[][] field) {
        int[][] workingField = new int[DIM_Y][DIM_X];
        System.out.println("   0 1 2 3 4 5 6 7 8 9 ");
        for (int y=0; y<DIM_Y; y++) {
            StringBuilder rowStringBuilder = new StringBuilder().append(y).append(" ").append("{");
            for (int x=0; x<DIM_X; x++) {
                int value = field[y][x];
                workingField[y][x] = value;
                rowStringBuilder.append(value);
                if (x<DIM_X-1) {
                    rowStringBuilder.append(",");
                } else {
                    rowStringBuilder.append("}");
                }
            }
            System.out.println(rowStringBuilder);
        }
        return workingField;
    }

    public static boolean checkVonNeumann(int[][] field, int x, int y) {
        int[][] vonNeumann = new int[][] {
                {0,0,0},
                {0,1,0},
                {0,0,0}
        };
        if (y>0) {
            if (x>0) {
                vonNeumann[0][0] = field[y-1][x-1];
            }
            vonNeumann[0][1] = field[y-1][x];
            if (x<DIM_X-1) {
                vonNeumann[0][2] = field[y-1][x+1];
            }
        }
        if (x>0) {
            vonNeumann[1][0] = field[y][x-1];
        }
        if (x<DIM_X-1) {
            vonNeumann[1][2] = field[y][x+1];
        }
        if (y<DIM_Y-1) {
            if (x>0) {
                vonNeumann[2][0] = field[y+1][x-1];
            }
            vonNeumann[2][1] = field[y+1][x];
            if (x<DIM_X-1) {
                vonNeumann[2][2] = field[y+1][x+1];
            }
        }
        if (vonNeumann[0][0] == 1 || vonNeumann[0][2] == 1 || vonNeumann[2][0] == 1 || vonNeumann[2][2] == 1) {
            return false;
        }
        if ((vonNeumann[0][1] == 1 || vonNeumann[2][1] == 1) && (vonNeumann[1][0] == 0 && vonNeumann[1][2] == 0)) {
            return true;
        }
        if ((vonNeumann[1][0] == 1 || vonNeumann[1][2] == 1) && (vonNeumann[0][1] == 0 && vonNeumann[0][2] == 0)) {
            return true;
        }
        return vonNeumann[0][1] == 0 && vonNeumann[1][0] == 0 && vonNeumann[1][2] == 0 && vonNeumann[2][1] == 0;
    }

    public static String getShipType(int[][] field, int x, int y) {
        int length_x = 0;
        for (int i=0; i<5; i++) {
            if (x+i < DIM_X) {
                int value = field[y][x+i];
                if (value == 1) {
                    if (i > 0) {
                        field[y][x+i] = 0;
                    }
                    length_x += value;
                } else {
                    break;
                }
            }
        }
        int length_y = 0;
        for (int i=0; i<5; i++) {
            if (y+i < DIM_Y) {
                int value = field[y+i][x];
                if (value == 1) {
                    if (i > 0) {
                        field[y+i][x] = 0;
                    }
                    length_y += value;
                } else {
                    break;
                }
            }
        }
        if ((length_x == SIZE_OF_BATTLESHIP && length_y == 1) ||
                (length_x == 1 && length_y == SIZE_OF_BATTLESHIP)) {
            return "battleship";
        }
        if ((length_x == SIZE_OF_CRUISER && length_y == 1) ||
                (length_x == 1 && length_y == SIZE_OF_CRUISER)) {
            return "cruiser";
        }
        if ((length_x == SIZE_OF_DESTROYER && length_y == 1) ||
                (length_x == 1 && length_y == SIZE_OF_DESTROYER)) {
            return "destroyer";
        }
        if ((length_x == SIZE_OF_SUBMARINE && length_y == SIZE_OF_SUBMARINE)) {
            return "submarine";
        }
        return null;
    }
}
