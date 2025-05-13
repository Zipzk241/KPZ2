package com.kpz2.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class GameController {
    @FXML private Button cell00;
    @FXML private Button cell01;
    @FXML private Button cell02;
    @FXML private Button cell10;
    @FXML private Button cell11;
    @FXML private Button cell12;
    @FXML private Button cell20;
    @FXML private Button cell21;
    @FXML private Button cell22;

    private boolean xTurn = true;
    private Button[][] cells;

    @FXML
    public void initialize() {
        cells = new Button[][]{
                { cell00, cell01, cell02 },
                { cell10, cell11, cell12 },
                { cell20, cell21, cell22 }
        };

        for (Button[] row : cells) {
            for (Button cell : row) {
                cell.setOnAction(event -> handleMove(cell));
            }
        }
    }

    private void handleMove(Button cell) {
        if (cell.getText().isEmpty()) {
            cell.setText(xTurn ? "X" : "O");
            if (checkWin()) {
                showWinnerAlert(xTurn ? "O" : "X");
                disableBoard();
            } else if (isDraw()) {
                showDrawAlert();
                disableBoard();
            } else {
                xTurn = !xTurn;
            }
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (!cells[i][0].getText().isEmpty() &&
                    cells[i][0].getText().equals(cells[i][1].getText()) &&
                    cells[i][0].getText().equals(cells[i][2].getText())) {
                return true;
            }
            if (!cells[0][i].getText().isEmpty() &&
                    cells[0][i].getText().equals(cells[1][i].getText()) &&
                    cells[0][i].getText().equals(cells[2][i].getText())) {
                return true;
            }
        }
        if (!cells[0][0].getText().isEmpty() &&
                cells[0][0].getText().equals(cells[1][1].getText()) &&
                cells[0][0].getText().equals(cells[2][2].getText())) {
            return true;
        }
        if (!cells[0][2].getText().isEmpty() &&
                cells[0][2].getText().equals(cells[1][1].getText()) &&
                cells[0][2].getText().equals(cells[2][0].getText())) {
            return true;
        }
        return false;
    }

    private boolean isDraw() {
        for (Button[] row : cells) {
            for (Button cell : row) {
                if (cell.getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showWinnerAlert(String winner) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("Winner: " + winner);
        alert.setContentText("Congratulations!");
        alert.showAndWait();
    }

    private void showDrawAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("Draw!");
        alert.setContentText("Try again.");
        alert.showAndWait();
    }

    private void disableBoard() {
        for (Button[] row : cells) {
            for (Button cell : row) {
                cell.setDisable(true);
            }
        }
    }
}
