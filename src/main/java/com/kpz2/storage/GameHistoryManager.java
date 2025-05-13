package com.kpz2.storage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import com.kpz2.util.Constants;

public class GameHistoryManager {
    public static void saveResult(String winner) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(Constants.HISTORY_FILE, true))) {
            String timestamp = LocalDateTime.now().toString();
            writer.println(timestamp + "," + winner);
        } catch (IOException e) {
            System.err.println("Error writing to game history: " + e.getMessage());
        }
    }
}