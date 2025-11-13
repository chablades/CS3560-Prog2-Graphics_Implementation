package model;

import java.util.ArrayList;
import java.util.List;

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
        while(!GameOver){


            GameOver = true;

        }
    }

    /*


     */

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
