package codo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PawnMoveTracker {
    public static String[][] movePawns(String[] moves) {
        ChessBoard chessBoard = new ChessBoard();
        for (String move : moves) {
            if (!chessBoard.play(move)) {
                return chessBoard.getErrorArray();
            }
        }
        return chessBoard.getArray();
    }
}

enum Color {
    BLACK, WHITE
}

class ChessBoard {
    static final String[] ROWS = new String[] {
            "8", "7", "6", "5", "4", "3", "2", "1"
    };
    static final String[] COLUMNS = new String[] {
            "a", "b", "c", "d", "e", "f", "g", "h"
    };
    final Player black = new Player(Color.BLACK, this);
    final Player white = new Player(Color.WHITE, this);
    int numberOfMoves = 0;
    private String invalidMove = null;
    final LinkedHashMap<String, Pawn> positionPawnMap = new LinkedHashMap<>();

    ChessBoard() {
        for (String column : ChessBoard.COLUMNS) {
            Pawn whitePawn = new Pawn(Color.WHITE, column+ChessBoard.ROWS[6]);
            white.setUpPiece(whitePawn);
        }
        for (String column : ChessBoard.COLUMNS) {
            Pawn blackPawn =  new Pawn(Color.BLACK, column+ChessBoard.ROWS[1]);
            black.setUpPiece(blackPawn);
        }
    }

    boolean play(String move) {
        switch(getPlayersTurn()) {
            case WHITE -> {
                if (white.play(move)) {
                    return true;
                } else {
                    invalidMove = move;
                }
            }
            case BLACK -> {
                if (black.play(move)) {
                    return true;
                } else {
                    invalidMove = move;
                }
            }
        }
        return false;
    }

    String[][] getArray() {
        final String[][] chessBoardArray = new String[ChessBoard.COLUMNS.length][ChessBoard.ROWS.length];
        for (int y=0; y<ChessBoard.ROWS.length; y++) {
            for (int x=0; x<ChessBoard.COLUMNS.length; x++) {
                chessBoardArray[y][x] = ".";
            }
        }
        for (Pawn pawn : positionPawnMap.values()) {
            String column = pawn.position.substring(0,1);
            int columnIndex = getIndexFromArray(column, ChessBoard.COLUMNS);
            String row = pawn.position.substring(1,2);
            int rowIndex = getIndexFromArray(row, ChessBoard.ROWS);
            switch (pawn.color) {
                case BLACK -> chessBoardArray[rowIndex][columnIndex] = "p";
                case WHITE -> chessBoardArray[rowIndex][columnIndex] = "P";
            }
        }
        return chessBoardArray;
    }

    String[][] getErrorArray() {
        return new String[][]{{invalidMove +" is invalid"}};
    }

    ArrayList<Pawn> getPawnsOnColumn(String column, Color color) {
        ArrayList<Pawn> pawns_on_column = new ArrayList<>();
        for (String position : positionPawnMap.keySet()) {
            if (position.contains(column)) {
                Pawn pawn = positionPawnMap.get(position);
                if (pawn.color == color) {
                    pawns_on_column.add(pawn);
                }
            }
        }
        return pawns_on_column;
    }

    void executeMove(String move, Pawn pawn) {
        positionPawnMap.remove(pawn.position);
        pawn.hasMoved = true;
        pawn.position = move;
        positionPawnMap.put(pawn.position, pawn);
        ++numberOfMoves;
    }

    void executeTake(Pawn pawn, String movedFrom, String positionToTake) {
        positionPawnMap.remove(movedFrom);
        positionPawnMap.remove(positionToTake);
        positionPawnMap.put(pawn.position, pawn);
        pawn.hasMoved = true;
        numberOfMoves++;
    }

    private int getIndexFromArray(String string, String[] array) {
        for (int i=0; i<array.length; i++) {
            if (Objects.equals(array[i], string)) {
                return i;
            }
        }
        return -1;
    }

    private Color getPlayersTurn() {
        if (numberOfMoves % 2 == 0) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }
}

class Player {

    final Color color;
    final static private Pattern MOVE_PATTERN = Pattern.compile("([a-h][1-8])|([a-h]x[a-h][1-8])");
    final private ChessBoard chessBoard;

    Player(Color color, ChessBoard chessBoard) {
        this.color = color;
        this.chessBoard = chessBoard;
    }

    void setUpPiece(Pawn pawn) {
        chessBoard.positionPawnMap.put(pawn.position, pawn);
    }

    boolean play(String move) {
        Matcher moveMatcher = MOVE_PATTERN.matcher(move);
        if (moveMatcher.find()) {
            if (moveMatcher.group(1) != null) {
                String column = move.substring(0, 1);
                ArrayList<Pawn> pawns_on_column = chessBoard.getPawnsOnColumn(column, color);
                for (Pawn pawn : pawns_on_column) {
                    if (!pawn.hasMoved && Objects.equals(pawn.double_move(), move) &&
                            !chessBoard.positionPawnMap.containsKey(move)) {
                        chessBoard.executeMove(move, pawn);
                        return true;
                    }
                    if (Objects.equals(pawn.move(), move) &&
                            !chessBoard.positionPawnMap.containsKey(move)) {
                        chessBoard.executeMove(move, pawn);
                        return true;
                    }
                }
            }
            if (moveMatcher.group(2) != null) {
                String[] split = move.split("x");
                String fromColumn = split[0];
                String positionToTake = split[1];
                ArrayList<Pawn> pawnsOnColumn = chessBoard.getPawnsOnColumn(fromColumn, color);
                for (Pawn pawn : pawnsOnColumn) {
                    String movedFrom = pawn.position;
                    if (pawn.take(chessBoard.positionPawnMap.get(positionToTake))) {
                        chessBoard.executeTake(pawn, movedFrom, positionToTake);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

class Pawn {
    String position;
    boolean hasMoved = false;
    final Color color;

    Pawn(Color color, String position) {
        this.color = color;
        this.position = position;
    }

    String move() {
        String column = position.substring(0,1);
        String row = position.substring(1,2);
        int row_int = Integer.parseInt(row);
        switch (color) {
            case BLACK -> {
                return column + --row_int;
            }
            case WHITE -> {
                return column + ++row_int;
            }
        }
        return null;
    }

    String double_move() {
        String column = position.substring(0,1);
        String row = position.substring(1,2);
        int row_int = Integer.parseInt(row);
        switch (color) {
            case BLACK -> {
                --row_int;
                return column + --row_int;
            }
            case WHITE -> {
                ++row_int;
                return column + ++row_int;
            }
        }
        return null;
    }

    boolean take(Pawn pawn) {
        String row = pawn.position.substring(1,2);
        int row_int = Integer.parseInt(row);
        String column = position.substring(0,1);
        switch (color) {
            case WHITE -> --row_int;
            case BLACK -> ++row_int;
        }
        if (Objects.equals(position, column+row_int)) {
            this.position = pawn.position;
            return true;
        }
        return false;
    }
}

