package view;

import model.GameState;
import model.LetterFeedback;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class KeyboardView extends JPanel {

    private final Map<Character, JButton> keys = new HashMap<>();

    public KeyboardView() {
        setLayout(new GridLayout(3, 1, 5, 5));
        setBackground(Color.BLACK);

        addRow("QWERTYUIOP");
        addRow("ASDFGHJKL");
        addRow("ZXCVBNM");
    }

    private void addRow(String letters) {
        JPanel row = new JPanel(new GridLayout(1, letters.length(), 5, 5));
        row.setBackground(Color.BLACK);

        for (char c : letters.toCharArray()) {
            JButton btn = new JButton(String.valueOf(c));
            btn.setFocusable(false);
            btn.setBackground(Color.LIGHT_GRAY);
            btn.setFont(new Font("Arial", Font.BOLD, 16));
            keys.put(c, btn);
            row.add(btn);
        }

        add(row);
    }

    public void updateLetter(char letter, LetterFeedback fb) {
        JButton btn = keys.get(Character.toUpperCase(letter));
        if (btn == null) return;

        switch (fb) {
            case CORRECT -> btn.setBackground(new Color(0x6AAA64));
            case PRESENT -> btn.setBackground(new Color(0xC9B458));
            case ABSENT  -> btn.setBackground(new Color(0x787C7E));
        }
    }

    public void reloadFromState(GameState state) {
        for (JButton btn : keys.values()) {
            btn.setBackground(Color.LIGHT_GRAY);
        }

        var guesses = state.getGuessHistory();
        var feedbacks = state.getFeedbackHistory();

        for (int i = 0; i < guesses.size(); i++) {
            String guess = guesses.get(i);
            LetterFeedback[] fb = feedbacks.get(i);

            for (int j = 0; j < guess.length(); j++) {
                updateLetter(guess.charAt(j), fb[j]);
            }
        }
    }
}