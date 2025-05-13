package com.kpz2.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.kpz2.storage.GameHistoryManager;
import com.kpz2.game.Player;
import com.kpz2.game.AIPlayer;
import com.kpz2.game.Game;
import com.kpz2.game.Board;
import com.kpz2.util.Constants;


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

    private Button[][] cells;
    private boolean isAIMode = false;

    private Game game;

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

        modeChoiceBox.getItems().addAll("Player vs Player", "Player vs AI");
        modeChoiceBox.setValue("Player vs Player");
        modeChoiceBox.setOnAction(e -> {
            isAIMode = modeChoiceBox.getValue().equals("Player vs AI");
            setupGame();
        });

        setupGame();
    }

    private void setupGame() {
        Player playerX = new Player("X") {
            @Override
            public int[] makeMove(String[][] board) {
                return null;
            }
        };

        Player playerO;
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

        game = new Game(playerX, playerO);
        renderBoard();
    }

    private void handleMove(Button cell) {
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

        if (!game.playMove(row, col)) return;

        renderBoard();

        if (game.isGameOver()) {
            String winner = game.getWinnerSymbol();
            if (winner != null) {
                GameHistoryManager.saveResult(winner);
                showWinnerAlert(winner);
            } else {
                showDrawAlert();
            }
            disableBoard();
            return;
        }

        if (isAIMode && game.getCurrentPlayer() instanceof AIPlayer) {
            int[] move = game.getCurrentPlayer().makeMove(game.getBoard().getCopy());
            if (move != null) {
                handleMove(cells[move[0]][move[1]]);
            }
        }
    }

    private void renderBoard() {
        Board board = game.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j].setText(board.get(i, j));
            }
        }
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
        game.reset();
        for (Button[] row : cells) {
            for (Button cell : row) {
                cell.setDisable(false);
            }
        }
        renderBoard();
    }
}
