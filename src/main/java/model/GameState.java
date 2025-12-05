package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameState implements Serializable {

    private String secretWord;
    private int maxAttempts;

    private final List<String> guessHistory = new ArrayList<>();
    private final List<LetterFeedback[]> feedbackHistory = new ArrayList<>();

    public GameState(String secretWord, int maxAttempts) {
        this.secretWord = secretWord.toUpperCase();
        this.maxAttempts = maxAttempts;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public int getAttemptsMade() {
        return guessHistory.size();
    }

    public boolean hasWon() {
        if (guessHistory.isEmpty()) return false;
        return guessHistory.get(guessHistory.size() - 1).equals(secretWord);
    }

    public boolean isGameOver() {
        return hasWon() || getAttemptsMade() >= maxAttempts;
    }

    public void addGuess(String guess, LetterFeedback[] fb) {
        guessHistory.add(guess.toUpperCase());
        feedbackHistory.add(fb);
    }

    public List<String> getGuessHistory() {
        return guessHistory;
    }

    public List<LetterFeedback[]> getFeedbackHistory() {
        return feedbackHistory;
    }
}