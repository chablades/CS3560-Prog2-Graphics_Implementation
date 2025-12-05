package view;

import model.GameState;
import model.GuessResult;
import model.LetterFeedback;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JPanel {

    private final int rows;
    private final int cols;
    private final JLabel[][] cells;

    public BoardView(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.cells = new JLabel[rows][cols];

        setLayout(new GridLayout(rows, cols, 5, 5));
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        initGrid();
    }

    private void initGrid() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                JLabel cell = new JLabel("", SwingConstants.CENTER);
                cell.setOpaque(true);
                cell.setBackground(Color.DARK_GRAY);
                cell.setForeground(Color.WHITE);
                cell.setFont(new Font("Arial", Font.BOLD, 28));
                cell.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
                cells[r][c] = cell;
                add(cell);
            }
        }
    }

    // Called while typing — controller tells us which row
    public void updateRow(int row, String partialGuess) {
        for (int c = 0; c < cols; c++) {
            JLabel cell = cells[row][c];

            if (c < partialGuess.length()) {
                cell.setText(String.valueOf(partialGuess.charAt(c)));
            } else {
                cell.setText("");
            }

            cell.setBackground(Color.DARK_GRAY);
        }
    }

    // Called after submitting a guess
    public void setGuessResult(int row, GuessResult result) {
        String guess = result.getGuess();
        LetterFeedback[] fb = result.getFeedback();

        for (int c = 0; c < cols; c++) {
            JLabel cell = cells[row][c];
            cell.setText(String.valueOf(guess.charAt(c)));

            switch (fb[c]) {
                case CORRECT -> cell.setBackground(new Color(0x6AAA64));
                case PRESENT -> cell.setBackground(new Color(0xC9B458));
                case ABSENT  -> cell.setBackground(new Color(0x787C7E));
            }
        }
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Invalid Guess", JOptionPane.ERROR_MESSAGE);
    }

    public void showEndMessage(boolean won, String secretWord) {
        if (won) {
            JOptionPane.showMessageDialog(this, "You won!", "Victory", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Game over! The word was: " + secretWord,
                    "Game Over",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void reloadFromState(GameState state) {
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++) {
                cells[r][c].setText("");
                cells[r][c].setBackground(Color.DARK_GRAY);
            }

        var guesses = state.getGuessHistory();
        var feedbacks = state.getFeedbackHistory();

        for (int i = 0; i < guesses.size(); i++) {
            setGuessResult(i, new GuessResult(guesses.get(i), feedbacks.get(i)));
        }
    }
}