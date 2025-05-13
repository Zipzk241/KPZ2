package com.kpz2.game;

import com.kpz2.util.Constants;

public class Board {
    private final String[][] grid;

    public Board() {
        grid = new String[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        clear();
    }

    public void clear() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = "";
            }
        }
    }

    public boolean isEmpty(int row, int col) {
        return grid[row][col].isEmpty();
    }

    public boolean set(int row, int col, String symbol) {
        if (isEmpty(row, col)) {
            grid[row][col] = symbol;
            return true;
        }
        return false;
    }

    public String get(int row, int col) {
        return grid[row][col];
    }

    public String[][] getCopy() {
        String[][] copy = new String[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(grid[i], 0, copy[i], 0, 3);
        }
        return copy;
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (!grid[i][0].isEmpty() && grid[i][0].equals(grid[i][1]) && grid[i][0].equals(grid[i][2])) return true;
            if (!grid[0][i].isEmpty() && grid[0][i].equals(grid[1][i]) && grid[0][i].equals(grid[2][i])) return true;
        }
        if (!grid[0][0].isEmpty() && grid[0][0].equals(grid[1][1]) && grid[0][0].equals(grid[2][2])) return true;
        if (!grid[0][2].isEmpty() && grid[0][2].equals(grid[1][1]) && grid[0][2].equals(grid[2][0])) return true;
        return false;
    }
}