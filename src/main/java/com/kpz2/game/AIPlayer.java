package com.kpz2.game;

import java.util.Random;
import com.kpz2.util.Constants;

public class AIPlayer extends Player {
    private Random random;

    public AIPlayer(String symbol) {
        super(symbol);
        this.random = new Random();
    }

    @Override
    public int[] makeMove(String[][] board) {
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.BOARD_SIZE; j++) {
                if (board[i][j] == null || board[i][j].equals(Constants.EMPTY_CELL)) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
