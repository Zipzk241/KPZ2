package com.kpz2.game;

import com.kpz2.util.Constants;
public abstract class Player {
    protected String symbol;

    public Player(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public abstract int[] makeMove(String[][] board);
}
