package model;

import java.util.List;
import java.util.Objects;

public class WordleGame {
    private GameState state;
    private final Dictionary dictionary;

    public WordleGame(String secretWord) {
        this.state = new GameState(secretWord, 6); // default maxAttempts = 6
        this.dictionary = new Dictionary();
    }

    // --- Core game loop methods ---
    public void submitGuess(String guess) {
        guess = guess.toUpperCase();
        state.addGuess(guess);
    }

    public boolean isValidWord(String guess) {
        guess = guess.toUpperCase();

        if (guess.length() != state.getSecretWord().length()) {
            System.out.println("Invalid guess length. Try again.");
            return false;
        }

        if (inGuessList(guess)) {
            System.out.println(guess + " has already been guessed. Try again.");
            return false;
        }

        if (!dictionary.isValidWord(guess)) {
            System.out.println("The word is not in this dictionary. Try again.");
            return false;
        }

        return true;
    }

    // --- Feedback formatting ---
    public String formatFeedback(String guess) {
        StringBuilder result = new StringBuilder();
        String secretWord = state.getSecretWord();

        for (int i = 0; i < guess.length(); i++) {
            char g = guess.charAt(i);
            if (g == secretWord.charAt(i)) {
                result.append("**").append(g).append("** "); // correct position
            } else if (secretWord.contains(String.valueOf(g))) {
                result.append("*").append(g).append("* ");   // wrong position
            } else {
                result.append(g).append(" ");                // not in word
            }
        }
        return result.toString().trim();
    }

    // --- Helpers ---
    public boolean inGuessList(String guess) {
        for (String pastGuess : state.getGuessHistory()) {
            if (Objects.equals(pastGuess, guess)) {
                return true;
            }
        }
        return false;
    }

    // --- Getters for UI/Controller ---
    public List<String> getGuessHistory() { return state.getGuessHistory(); }
    public String getSecretWord() { return state.getSecretWord(); }
    public int getAttemptsMade() { return state.getAttemptsMade(); }
    public int getMaxAttempts() { return state.getMaxAttempts(); }
    public boolean isGameOver() { return state.isGameOver(); }
    public boolean hasWon() { return state.hasWon(); }

    // --- Persistence support ---
    public GameState getState() { return state; }
    public void setState(GameState newState) { this.state = newState; }
}
