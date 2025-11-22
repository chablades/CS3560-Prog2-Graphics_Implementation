package view;

import model.WordleGame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class WordleUI extends JFrame {
    private WordleGame game;
    private JPanel gridPanel;
    private JTextField inputField;
    private JLabel feedbackLabel;

    public WordleUI(String word){
        //Initialize Game
        game = new WordleGame(word);
        setTitle("Wordle Clone");
        setSize(400, 600);
        setDefaultCloseOperation((EXIT_ON_CLOSE));
        setLayout(new BorderLayout());


        gridPanel = new JPanel(new GridLayout(6, 5));
        for(int i = 0; i < 30; i ++){
            JLabel cell = new JLabel(" ", SwingConstants.CENTER);
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            gridPanel.add(cell);
        }

        inputField = new JTextField();
        JButton submitButton = new JButton("SUBMIT");
        feedbackLabel = new JLabel("Enter a 5-Letter word");

        //Game Logic
        submitButton.addActionListener(e -> {
            String guess = inputField.getText().trim().toUpperCase();
            if (game.isValidWord(guess)) {
                game.submitGuess(guess);
                updateGrid();
                feedbackLabel.setText(game.hasWon() ? "You won!" : game.isGameOver() ? "Game over!" : "");
            } else {
                feedbackLabel.setText("Invalid guess. Try again.");
            }
            inputField.setText("");
        });

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(submitButton, BorderLayout.EAST);

        add(gridPanel, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
        add(feedbackLabel, BorderLayout.NORTH);

        setVisible(true);


    }

    private void updateGrid() {
        java.util.List<String> guesses = game.getGuessHistory();
        Component[] cells = gridPanel.getComponents();

        // Reset all cells
        for (Component c : cells) {
            JLabel cell = (JLabel) c;
            cell.setText(" ");
            cell.setBackground(null);
            cell.setOpaque(false);
        }

        // Fill in guesses
        for (int row = 0; row < guesses.size(); row++) {
            String guess = guesses.get(row);
            for (int col = 0; col < guess.length(); col++) {
                JLabel cell = (JLabel) cells[row * 5 + col];
                char g = guess.charAt(col);
                cell.setText(String.valueOf(g));

                if (g == game.getSecretWord().charAt(col)) {
                    cell.setBackground(Color.GREEN);
                    cell.setOpaque(true);
                } else if (game.getSecretWord().contains(String.valueOf(g))) {
                    cell.setBackground(Color.YELLOW);
                    cell.setOpaque(true);
                } else {
                    cell.setBackground(Color.LIGHT_GRAY);
                    cell.setOpaque(true);
                }
            }
        }

        gridPanel.revalidate();
        gridPanel.repaint();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WordleUI("HELLO"));

    }


}
