package com.kpz2.ai;

import javafx.scene.control.Button;

public class SimpleAI {
    public static Button chooseMove(Button[][] cells) {
        for (Button[] row : cells) {
            for (Button cell : row) {
                if (cell.getText().isEmpty()) {
                    return cell;
                }
            }
        }
        return null;
    }
}
