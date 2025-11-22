package model;

import java.util.Scanner;

public class modelMain {
    public static void main(String[] args) {
        WordleGame game = new WordleGame("HELLO");
        Scanner scanner = new Scanner(System.in);

        while (!game.isGameOver()) {
            System.out.print("Enter guess: ");
            String guess = scanner.nextLine().trim().toUpperCase();

            if (game.isValidWord(guess)) {
                game.submitGuess(guess);
                System.out.println(game.formatFeedback(guess));
            } else {
                System.out.println("Invalid guess.");
            }
        }

        System.out.println(game.hasWon() ? "You won!" : "Game over. The word was: " + game.getSecretWord());
        scanner.close();
    }
}