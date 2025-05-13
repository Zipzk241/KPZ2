package com.kpz2.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import com.kpz2.storage.GameHistoryManager;
import com.kpz2.game.Player;
import com.kpz2.game.AIPlayer;

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
    @FXML private Button restartButton;
    @FXML private ChoiceBox<String> modeChoiceBox;

    private Player playerX;
    private Player playerO;

    private boolean xTurn = true;
    private Button[][] cells;
    private String[][] currentBoard;
    private boolean isAIMode = false;

    @FXML
    public void initialize() {
        cells = new Button[][]{
                { cell00, cell01, cell02 },
                { cell10, cell11, cell12 },
                { cell20, cell21, cell22 }
        };

        currentBoard = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                currentBoard[i][j] = "";
            }
        }

        for (Button[] row : cells) {
            for (Button cell : row) {
                cell.setOnAction(event -> handleMove(cell));
            }
        }

        modeChoiceBox.getItems().addAll("Player vs Player", "Player vs AI");
        modeChoiceBox.setValue("Player vs Player");
        modeChoiceBox.setOnAction(e -> {
            isAIMode = modeChoiceBox.getValue().equals("Player vs AI");
            setupPlayers();
        });

        setupPlayers();
    }

    private void setupPlayers() {
        playerX = new Player("X") {
            @Override
            public int[] makeMove(String[][] board) {
                return null;
            }
        };

        if (isAIMode) {
            playerO = new AIPlayer("O");
        } else {
            playerO = new Player("O") {
                @Override
                public int[] makeMove(String[][] board) {
                    return null;
                }
            };
        }
    }

    private void handleMove(Button cell) {
        if (!cell.getText().isEmpty()) return;

        int row = -1, col = -1;
        outer:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j] == cell) {
                    row = i;
                    col = j;
                    break outer;
                }
            }
        }

        if (row == -1 || col == -1) return;

        String currentSymbol = xTurn ? "X" : "O";
        cell.setText(currentSymbol);
        currentBoard[row][col] = currentSymbol;

        if (checkWin()) {
            GameHistoryManager.saveResult(currentSymbol);
            showWinnerAlert(currentSymbol);
            disableBoard();
            return;
        } else if (isDraw()) {
            showDrawAlert();
            disableBoard();
            return;
        }

        xTurn = !xTurn;

        if (isAIMode && !xTurn) {
            int[] move = playerO.makeMove(currentBoard);
            if (move != null) {
                handleMove(cells[move[0]][move[1]]);
            }
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (!currentBoard[i][0].isEmpty() &&
                    currentBoard[i][0].equals(currentBoard[i][1]) &&
                    currentBoard[i][0].equals(currentBoard[i][2])) return true;
            if (!currentBoard[0][i].isEmpty() &&
                    currentBoard[0][i].equals(currentBoard[1][i]) &&
                    currentBoard[0][i].equals(currentBoard[2][i])) return true;
        }
        if (!currentBoard[0][0].isEmpty() &&
                currentBoard[0][0].equals(currentBoard[1][1]) &&
                currentBoard[0][0].equals(currentBoard[2][2])) return true;
        if (!currentBoard[0][2].isEmpty() &&
                currentBoard[0][2].equals(currentBoard[1][1]) &&
                currentBoard[0][2].equals(currentBoard[2][0])) return true;
        return false;
    }

    private boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (currentBoard[i][j] == null || currentBoard[i][j].isEmpty()) {
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

    @FXML
    private void restartGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j].setText("");
                cells[i][j].setDisable(false);
                currentBoard[i][j] = "";
            }
        }
        xTurn = true;
    }
}