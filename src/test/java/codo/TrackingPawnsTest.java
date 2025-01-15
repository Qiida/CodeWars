package codo;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertArrayEquals;

public class TrackingPawnsTest {

    @Test
    public void exampleTest1() {
        String[][] expected = {
                {".",".",".",".",".",".",".","."}, // 8
                {".","p","p",".","p","p","p","p"}, // 7
                {"p",".",".","p",".",".",".","."}, // 6
                {".",".",".",".",".",".",".","."}, // 5
                {".",".",".",".","P",".",".","."}, // 4
                {".",".",".",".",".",".",".","."}, // 3
                {"P","P","P","P",".","P","P","P"}, // 2
                {".",".",".",".",".",".",".","."}  // 1
        };  //    a   b   c   d   e   f   g   h
        assertArrayEquals(expected, PawnMoveTracker.movePawns(
                new String[] {"e3", "d6", "e4", "a6"})
        );
    }

    @Test
    public void exampleTest2() {
        String[][] expected = {
                {".",".",".",".",".",".",".","."}, // 8
                {"p","p","p",".","p","p","p","p"}, // 7
                {".",".",".",".",".",".",".","."}, // 6
                {".",".",".",".",".",".",".","."}, // 5
                {".",".",".",".","p",".",".","."}, // 4
                {".",".",".","P",".",".",".","."}, // 3
                {"P","P","P",".",".","P","P","P"}, // 2
                {".",".",".",".",".",".",".","."}
        };  //    a   b   c   d   e   f   g   h
        assertArrayEquals(expected, PawnMoveTracker.movePawns(
                new String[] {"e4", "d5", "d3", "dxe4"}));
    }

    @Test
    public void exampleTest3() {
        String[][] expected = {
                {".",".",".",".",".",".",".","."}, // 8
                {"p","p","p","p","p","p","p","p"}, // 7
                {".",".",".",".",".",".",".","."}, // 6
                {".",".",".",".",".",".",".","."}, // 5
                {".",".",".",".","P",".",".","."}, // 4
                {".",".",".",".",".",".",".","."}, // 3
                {"P","P","P","P",".","P","P","P"}, // 2
                {".",".",".",".",".",".",".","."}  // 1
        };  //    a   b   c   d   e   f   g   h
        assertArrayEquals(expected, PawnMoveTracker.movePawns(
                new String[] {"e4"})
        );
        expected = new String[][]{
                {".", ".", ".", ".", ".", ".", ".", "."}, // 8
                {"p", "p", "p", "p", "p", ".", "p", "p"}, // 7
                {".", ".", ".", ".", ".", ".", ".", "."}, // 6
                {".", ".", ".", ".", ".", "p", ".", "."}, // 5
                {".", ".", ".", ".", "P", ".", ".", "."}, // 4
                {".", ".", ".", ".", ".", ".", ".", "."}, // 3
                {"P", "P", "P", "P", ".", "P", "P", "P"}, // 2
                {".", ".", ".", ".", ".", ".", ".", "."}  // 1
        };  //    a    b    c    d    e    f    g    h
        assertArrayEquals(expected, PawnMoveTracker.movePawns(
                new String[] {"e4", "f5"})
        );
        expected = new String[][]{
                {".", ".", ".", ".", ".", ".", ".", "."}, // 8
                {"p", "p", "p", "p", "p", ".", "p", "p"}, // 7
                {".", ".", ".", ".", ".", ".", ".", "."}, // 6
                {".", ".", ".", ".", ".", "P", ".", "."}, // 5
                {".", ".", ".", ".", ".", ".", ".", "."}, // 4
                {".", ".", ".", ".", ".", ".", ".", "."}, // 3
                {"P", "P", "P", "P", ".", "P", "P", "P"}, // 2
                {".", ".", ".", ".", ".", ".", ".", "."}  // 1
        };  //    a    b    c    d    e    f    g    h
        assertArrayEquals(expected, PawnMoveTracker.movePawns(
                new String[] {"e4", "f5", "exf5"})
        );
    }

    @Test
    public void exampleTest4() {
        String[][] expected = {
                {".",".",".",".",".",".",".","."}, // 8
                {"p","p","p","p","p","p",".","."}, // 7
                {".",".",".",".",".",".",".","."}, // 6
                {".",".",".",".",".",".","P","."}, // 5
                {".",".",".",".",".",".",".","p"}, // 4
                {".",".",".",".",".",".",".","."}, // 3
                {"P","P","P","P","P","P","P","."}, // 2
                {".",".",".",".",".",".",".","."}  // 1
        };  //    a   b   c   d   e   f   g   h
        String[][] results = PawnMoveTracker.movePawns(
                new String[] {"h3", "h5", "h4", "g5", "hxg5", "h4"});
        assertArrayEquals(expected, results);
    }

    @Test
    public void invalidTest1() {
        String[][] expected = {{"e5 is invalid"}};
        assertArrayEquals(expected, PawnMoveTracker.movePawns(
                new String[]{"e5"})
        );
    }

    @Test
    public void invalidTest2() {
        String[][] expected = {{"exf6 is invalid"}};
        assertArrayEquals(expected, PawnMoveTracker.movePawns(
                new String[]{"e4", "f6", "exf6"})
        );
    }

    @Test
    public void invalidTest3() {
        String[][] expected = {{"f6 is invalid"}};
        assertArrayEquals(expected, PawnMoveTracker.movePawns(
                new String[] {"d4","f6","d5","f6","dxe5","f4"}));

        expected = new String[][]{{"dxe7 is invalid"}};
        assertArrayEquals(expected, PawnMoveTracker.movePawns(
                new String[] {"d4","a5","d5","f6","dxe7","f4"}));

        expected = new String[][]{{"b4 is invalid"}};
        assertArrayEquals(expected, PawnMoveTracker.movePawns(
                new String[] {"a4","a5","b4","b5","c4","b4"}));

    }
}
