package codo;

public class TenMinWalk {
    public static boolean isValid(char[] walk) {
        if (walk.length != 10) {
            return false;
        }
        int dx = 0;
        int dy = 0;
        for (char direction : walk) {
            switch (direction) {
                case 'n': dy++; break;
                case 'e': dx++; break;
                case 's': dy--; break;
                case 'w': dx--; break;
                default: break;
            }
        }
        return dx == 0 && dy == 0;
    }
}
