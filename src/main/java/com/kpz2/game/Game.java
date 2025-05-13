package com.kpz2.game;

import com.kpz2.util.Constants;

public class Game {
    private final Board board;
    private final Player playerX;
    private final Player playerO;
    private boolean xTurn;

    public Game(Player playerX, Player playerO) {
        this.board = new Board();
        this.playerX = playerX;
        this.playerO = playerO;
        this.xTurn = true;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return xTurn ? playerX : playerO;
    }

    public String getCurrentSymbol() {
        return xTurn ? "X" : "O";
    }

    public boolean playMove(int row, int col) {
        boolean success = board.set(row, col, getCurrentSymbol());
        if (success) xTurn = !xTurn;
        return success;
    }

    public boolean isGameOver() {
        return board.checkWin() || board.isFull();
    }

    public String getWinnerSymbol() {
        return board.checkWin() ? (!xTurn ? "X" : "O") : null;
    }

    public void reset() {
        board.clear();
        xTurn = true;
    }
}
