package com.kpz2.game;

import java.util.Random;

public class AIPlayer extends Player {
    private Random random;

    public AIPlayer(String symbol) {
        super(symbol);
        this.random = new Random();
    }

    @Override
    public int[] makeMove(String[][] board) {
        // Very basic: pick the first empty cell randomly
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null || board[i][j].isEmpty()) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
