package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordleGame {
    //Variable Initializers
    private static int maxAttempts = 6;
    private int attemptsMade = 0;
    private final List<String> guessHistory;
    private final String secretWord;
    private boolean GameOver;
    private boolean hasWon;

    //Constructor
    public WordleGame(String word) {
        this.secretWord = word.toUpperCase();
        this.guessHistory = new ArrayList<>();
        this.attemptsMade = 0;
        this.GameOver = false;
        this.hasWon = false;
    }
    //Run Game

    public void runGame(){
        //While statement to run game, stops loop after the game is finished.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wordle Game");

        while(!GameOver){
            printCurrentBoard();
            System.out.println("Enter your guess: ");
            String guess = scanner.nextLine().trim().toUpperCase();

            if (guess.length() != secretWord.length())
            {
                System.out.println("Invalid guess length. Try again.");
                continue;
            }

            submitGuess(guess);

            if (hasWon) {
                printCurrentBoard();
                System.out.println("Congratulations! You have guessed the word: " + secretWord);
            }

        }
        scanner.close();

    }



    public void printCurrentBoard(){
        for (String guess : guessHistory) {
            System.out.println(guess); // Placeholder: later show colored feedback
        }
        for (int i = guessHistory.size(); i < maxAttempts; i++) {
            System.out.println("_ _ _ _ _");
        }
    }

    public void submitGuess(String guess){
        guessHistory.add(guess);
        attemptsMade++;
        if (guess.equals(secretWord)) {
            hasWon = true;
            GameOver = true;
        }
        else if (attemptsMade >= maxAttempts)
        {
            System.out.println("Game over. The word was: " + secretWord);
            GameOver = true;
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

}
