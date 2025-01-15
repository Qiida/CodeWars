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
            chessBoard.play(move);
        }
        return chessBoard.getArray();
    }
}

enum Color {
    BLACK, WHITE
}

class Pawn {
    public String position;

    boolean hasMoved = false;
    final Color color;

    public Pawn(Color color, String position) {
        this.color = color;
        this.position = position;
    }

    public String move() {
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

    public String double_move() {
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

    public boolean take(Pawn pawn) {
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

class ChessBoard {
    static final public String[] ROWS = new String[] {
            "8", "7", "6", "5", "4", "3", "2", "1"
    };

    static final public String[] COLUMNS = new String[] {
            "a", "b", "c", "d", "e", "f", "g", "h"
    };

    final Player black = new Player(Color.BLACK, this);
    final Player white = new Player(Color.WHITE, this);

    int num_moves = 0;
    private String invalid_move = null;

    final public LinkedHashMap<String, Pawn> position_pawn_map = new LinkedHashMap<>();

    public ChessBoard() {
        for (String column : ChessBoard.COLUMNS) {
            Pawn whitePawn =  new Pawn(Color.BLACK, column+ChessBoard.ROWS[1]);
            black.set_up(whitePawn);
            position_pawn_map.put(whitePawn.position, whitePawn);
        }
        for (String column : ChessBoard.COLUMNS) {
            Pawn blackPawn = new Pawn(Color.WHITE, column+ChessBoard.ROWS[6]);
            white.set_up(blackPawn);
            position_pawn_map.put(blackPawn.position, blackPawn);
        }
    }

    public String[][] getArray() {
        if (invalid_move != null) {
            return new String[][]{{invalid_move+" is invalid"}};
        }
        final String[][] chessBoardArray = new String[ChessBoard.COLUMNS.length][ChessBoard.ROWS.length];
        for (int y=0; y<ChessBoard.ROWS.length; y++) {
            for (int x=0; x<ChessBoard.COLUMNS.length; x++) {
                chessBoardArray[y][x] = ".";
            }
        }
        for (Pawn pawn : position_pawn_map.values()) {
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

    private int getIndexFromArray(String string, String[] array) {
        for (int i=0; i<array.length; i++) {
            if (Objects.equals(array[i], string)) {
                return i;
            }
        }
        return -1;
    }

    public void play(String move) {
        if (invalid_move != null) {
            return;
        }
        if (num_moves % 2 == 0) {
            if (!white.play(move)) {
                invalid_move = move;
            }
        } else {
            if (!black.play(move)) {
                invalid_move = move;
            }
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

    void set_up(Pawn pawn) {
        chessBoard.position_pawn_map.put(pawn.position, pawn);
    }

    boolean play(String move) {
        Matcher moveMatcher = MOVE_PATTERN.matcher(move);
        if (moveMatcher.find()) {
            if (moveMatcher.group(1) != null) {
                String column = move.substring(0, 1);
                ArrayList<Pawn> pawns_on_column = getPawnsOnColumn(column);
                for (Pawn pawn : pawns_on_column) {
                    if (!pawn.hasMoved && Objects.equals(pawn.double_move(), move) &&
                            !chessBoard.position_pawn_map.containsKey(move)) {
                        return executeMove(move, pawn);
                    }
                    if (Objects.equals(pawn.move(), move) && !chessBoard.position_pawn_map.containsKey(move)) {
                        return executeMove(move, pawn);
                    }
                }
            }
            if (moveMatcher.group(2) != null) {
                String[] split = move.split("x");
                String from_column = split[0];
                String position_to_take = split[1];
                ArrayList<Pawn> pawns_on_column = getPawnsOnColumn(from_column);
                for (Pawn pawn : pawns_on_column) {
                    String moved_from = pawn.position;
                    if (pawn.take(chessBoard.position_pawn_map.get(position_to_take))) {
                        chessBoard.position_pawn_map.remove(moved_from);
                        chessBoard.position_pawn_map.remove(position_to_take);
                        chessBoard.position_pawn_map.put(pawn.position, pawn);
                        pawn.hasMoved = true;
                        chessBoard.num_moves++;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean executeMove(String move, Pawn pawn) {
        chessBoard.position_pawn_map.remove(pawn.position);
        pawn.hasMoved = true;
        pawn.position = move;
        chessBoard.position_pawn_map.put(pawn.position, pawn);
        ++chessBoard.num_moves;
        return true;
    }

    private ArrayList<Pawn> getPawnsOnColumn(String column) {
        ArrayList<Pawn> pawns_on_column = new ArrayList<>();
        for (String position : chessBoard.position_pawn_map.keySet()) {
            if (position.contains(column)) {
                Pawn pawn = chessBoard.position_pawn_map.get(position);
                if (pawn.color == color) {
                    pawns_on_column.add(pawn);
                }
            }
        }
        return pawns_on_column;
    }
}