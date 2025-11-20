package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class WordleGame {
    private static int maxAttempts = 6;
    private int attemptsMade = 0;
    private final List<String> guessHistory;
    private final String secretWord;
    private boolean gameOver;
    private boolean hasWon;

    public WordleGame(String word) {
        this.secretWord = word.toUpperCase();
        this.guessHistory = new ArrayList<>();
        this.attemptsMade = 0;
        this.gameOver = false;
        this.hasWon = false;
    }

    public void runGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wordle Game");
        System.out.println("** = Green (Word is in the right place), * = Yellow (Word exists but not in right place");
        while (!gameOver) {
            printCurrentBoard();
            System.out.print("Enter your guess: ");
            String guess = scanner.nextLine().trim().toUpperCase();

            if (guess.length() != secretWord.length()) {
                System.out.println("Invalid guess length. Try again.");
                continue;
            }

            if (inGuessList(guess)) {
                System.out.println(guess + " has already been guessed. Try again.");
                continue;
            }

            submitGuess(guess);

            if (hasWon) {
                printCurrentBoard();
                System.out.println("Congratulations! You guessed the word: " + secretWord);
            }
        }
        scanner.close();
    }


    private String formatFeedback(String guess) {
        StringBuilder result = new StringBuilder();
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


    public void printCurrentBoard() {
        for (String guess : guessHistory) {
            System.out.println(formatFeedback(guess));
        }
        for (int i = guessHistory.size(); i < maxAttempts; i++) {
            System.out.println("_ _ _ _ _");
        }
    }

    public void submitGuess(String guess) {
        guessHistory.add(guess);
        attemptsMade++;
        if (guess.equals(secretWord)) {
            hasWon = true;
            gameOver = true;
        } else if (attemptsMade >= maxAttempts) {
            System.out.println("Game over. The word was: " + secretWord);
            gameOver = true;
        }
    }

    public static int getMaxAttempts() {
        return maxAttempts;
    }

    public static void setMaxAttempts(int maxAttempts) {
        WordleGame.maxAttempts = maxAttempts;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public boolean inGuessList(String guess) {
        for (String pastGuess : guessHistory) {
            if (Objects.equals(pastGuess, guess)) {
                return true;
            }
        }
        return false;
    }
}