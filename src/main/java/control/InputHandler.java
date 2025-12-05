package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandler extends KeyAdapter {

    private final GameController controller;

    public InputHandler(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();

        if (Character.isLetter(c)) {
            controller.onLetter(c);
        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            controller.onBackspace();
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            controller.onEnter();
        }
    }
}