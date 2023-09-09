package codo;

public class Snail {
    public static int[] snail(int[][] array) {
        if (array[0].length == 0) {
            return new int[]{};
        }

        int[] snailTrail = new int[array.length * array[0].length];
        int snailTrailIndex = 0;

        int direction = 0;
        int[] position = {0 , 0};

        snailTrail[snailTrailIndex++] = getArrayValueOfPosition(array, position);

        int numberOfSteps = array.length-1;
        for (int i=0; i<2 * (array.length-1) + 1; i++) {
            for (int j=0; j<numberOfSteps; j++) {
                position = Snail.goInDirection(direction, position);
                snailTrail[snailTrailIndex++] = getArrayValueOfPosition(array, position);
            }
            direction = Snail.getNextDirection(direction);
            if (i % 2 == 0 && i != 0) {
                numberOfSteps--;
            }
        }
        return snailTrail;
    }

    static private int[] goInDirection(int direction, int[] position) {
        return switch (direction) {
            case 0 -> goRight(position);
            case 1 -> goDown(position);
            case 2 -> goLeft(position);
            case 3 -> goUp(position);
            default -> null;
        };
    }

    static private int getNextDirection(int direction) {
        return switch (direction) {
            case 0 -> 1;
            case 1 -> 2;
            case 2 -> 3;
            default -> 0;
        };
    }

    private static int[] goRight(int[] position) {
        return new int[] {position[0], position[1]+1};
    }

    private static int[] goDown(int[] position) {
        return new int[] {position[0]+1, position[1]};
    }

    private static int[] goLeft(int[] position) {
        return new int[] {position[0], position[1]-1};
    }

    private static int[] goUp(int[] position) {
        return new int[] {position[0]-1, position[1]};
    }

    private static int getArrayValueOfPosition(int[][] array, int[] position) {
        return array[position[0]][position[1]];
    }

}
