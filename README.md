# Tic-Tac-Toe Game

## Project Description

This is a full-featured desktop application of the classic **Tic-Tac-Toe** game, developed in **Java** using object-oriented programming (OOP) principles. 
The game offers modes for **Player vs Player** and **Player vs Computer** gameplay. 
Game results are saved to a local **file**, and users can view overall statistics.

---

## Features

- **Graphical User Interface** built using **JavaFX**
- Game Modes:
  - Player vs Player
  - Player vs Computer (AI using Minimax algorithm)
- Data Persistence:
  - Game history (date, players, result) is saved to a `.csv` file
- Statistics:
  - View win/loss/draw statistics
- Restart game without restarting the application
- Clean and user-friendly UI with responsive layout
- Unit-tested game logic (separated from UI)

---

## Project Structure
TicTacToe/
│
├── src/
│ ├── game/ // Core game logic (model)
│ │ ├── Board.java
│ │ ├── Game.java
│ │ ├── Player.java
│ │ └── AIPlayer.java
│ │
│ ├── ui/ // User interface
│ │ ├── MainApp.java
│ │ └── GameController.java
│ │
│ ├── storage/ // File-based data storage
│ │ └── GameHistoryManager.java
│ │
│ └── util/ // Utility classes
│ └── Constants.java
│
├── resources/
│ └── style.css
│
├── games_history.csv // Game results archive
│
└── README.md

---

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/yourname/tictactoe-java.git
2. Open the project in your favorite Java IDE (e.g., IntelliJ IDEA, Eclipse)
3. Ensure you have Java 11+ and JavaFX installed and configured
4. Run the MainApp.java class

## Technologies Used
- Java 17
- JavaFX
- OOP principles (MVC-based architecture)
- File-based persistence (CSV)
- AI logic using Minimax algorithm
