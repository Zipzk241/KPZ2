package com.kpz2.storage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class GameHistoryManager {
    private static final String FILE_PATH = "games_history.csv";

    public static void saveResult(String winner) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            String timestamp = LocalDateTime.now().toString();
            writer.println(timestamp + "," + winner);
        } catch (IOException e) {
            System.err.println("Error writing to game history: " + e.getMessage());
        }
    }
}
