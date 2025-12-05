package view;

import control.GameController;
import control.InputHandler;
import model.Dictionary;
import model.GameState;
import model.WordleGame;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Wordle Clone");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Dictionary dict = new Dictionary();
        WordleGame game = new WordleGame(dict.getRandomWord(), dict);

        BoardView board = new BoardView(game.getMaxAttempts(), game.getSecretWord().length());
        KeyboardView keyboard = new KeyboardView();

        GameController controller = new GameController(game, board, keyboard);

        add(board, BorderLayout.CENTER);
        add(keyboard, BorderLayout.SOUTH);

        addKeyListener(new InputHandler(controller));
        setFocusable(true);
        requestFocusInWindow();

        setupMenu(controller);

        setSize(700, 1000);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setupMenu(GameController controller) {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem saveItem = new JMenuItem("Save Game");
        JMenuItem loadItem = new JMenuItem("Load Game");
        JMenuItem newItem  = new JMenuItem("New Game");

        saveItem.addActionListener(e -> {
            try {
                io.GameSaver.save(controller.getState(), "savegame.dat");
                JOptionPane.showMessageDialog(this, "Game saved.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Could not save game.");
            }
        });

        loadItem.addActionListener(e -> {
            try {
                GameState loaded = io.GameLoader.load("savegame.dat");
                controller.loadState(loaded);
                JOptionPane.showMessageDialog(this, "Game loaded.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Could not load game.");
            }
        });

        newItem.addActionListener(e -> {
            dispose();
            new MainFrame();
        });

        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        fileMenu.add(newItem);
        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
    }
}