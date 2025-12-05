package control;

import model.*;
import view.BoardView;
import view.KeyboardView;

public class GameController {

    private final WordleGame game;
    private final BoardView boardView;
    private final KeyboardView keyboardView;

    private final StringBuilder currentGuess = new StringBuilder();
    private int currentRow = 0;

    public GameController(WordleGame game, BoardView boardView, KeyboardView keyboardView) {
        this.game = game;
        this.boardView = boardView;
        this.keyboardView = keyboardView;
    }

    public void onLetter(char c) {
        if (game.isGameOver()) return;

        if (currentGuess.length() < game.getSecretWord().length()) {
            currentGuess.append(Character.toUpperCase(c));
            boardView.updateRow(currentRow, currentGuess.toString());
        }
    }

    public void onBackspace() {
        if (game.isGameOver()) return;

        if (currentGuess.length() > 0) {
            currentGuess.deleteCharAt(currentGuess.length() - 1);
            boardView.updateRow(currentRow, currentGuess.toString());
        }
    }

    public void onEnter() {
        if (game.isGameOver()) return;

        try {
            GuessResult result = game.submitGuess(currentGuess.toString());

            boardView.setGuessResult(currentRow, result);
            updateKeyboard(result);
            currentGuess.setLength(0);
            currentRow++;

            if (game.isGameOver()) {
                boardView.showEndMessage(game.hasWon(), game.getSecretWord());
            }

        } catch (InvalidGuessException e) {
            boardView.showError(e.getMessage());
        }
    }

    private void updateKeyboard(GuessResult result) {
        String guess = result.getGuess();
        LetterFeedback[] fb = result.getFeedback();

        for (int i = 0; i < guess.length(); i++) {
            keyboardView.updateLetter(guess.charAt(i), fb[i]);
        }
    }

    public GameState getState() {
        return game.getState();
    }

    public void loadState(GameState state) {
        game.setState(state);
        boardView.reloadFromState(state);
        keyboardView.reloadFromState(state);
        currentRow = game.getAttemptsMade();
        currentGuess.setLength(0);
    }
}