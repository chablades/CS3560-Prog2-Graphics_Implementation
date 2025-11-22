package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameState implements Serializable {
    private final String secretWord;
    private final List<String> guessHistory;
    private final int maxAttempts;
    private int attemptsMade;
    private boolean gameOver;
    private boolean hasWon;

    public GameState(String secretWord, int maxAttempts) {
        this.secretWord = secretWord.toUpperCase();
        this.maxAttempts = maxAttempts;
        this.guessHistory = new ArrayList<>();
        this.attemptsMade = 0;
        this.gameOver = false;
        this.hasWon = false;
    }

    public void addGuess(String guess) {
        guessHistory.add(guess.toUpperCase());
        attemptsMade++;
        if (guess.equalsIgnoreCase(secretWord)) {
            hasWon = true;
            gameOver = true;
        } else if (attemptsMade >= maxAttempts) {
            gameOver = true;
        }
    }

    // --- Getters ---
    public String getSecretWord() { return secretWord; }
    public List<String> getGuessHistory() { return new ArrayList<>(guessHistory); }
    public int getAttemptsMade() { return attemptsMade; }
    public int getMaxAttempts() { return maxAttempts; }
    public boolean isGameOver() { return gameOver; }
    public boolean hasWon() { return hasWon; }
}
