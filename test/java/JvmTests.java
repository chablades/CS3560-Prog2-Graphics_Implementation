package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class JvmTests {

    @Test
    void testGameStateWinCondition() {
        GameState state = new GameState("APPLE", 6);
        state.addGuess("APPLE");
        assertTrue(state.hasWon());
        assertTrue(state.isGameOver());
        assertEquals(1, state.getAttemptsMade());
    }

    @Test
    void testGameStateLoseCondition() {
        GameState state = new GameState("APPLE", 2);
        state.addGuess("BERRY");
        state.addGuess("MANGO");
        assertFalse(state.hasWon());
        assertTrue(state.isGameOver());
        assertEquals(2, state.getAttemptsMade());
    }

    @Test
    void testWordleGameValidGuess() {
        WordleGame game = new WordleGame("APPLE");
        assertTrue(game.isValidWord("APPLE"));
        game.submitGuess("APPLE");
        assertTrue(game.hasWon());
    }

    @Test
    void testWordleGameFeedback() {
        WordleGame game = new WordleGame("APPLE");
        String feedback = game.formatFeedback("APPLY");
        // Example: "**A** **P** **P** L Y"
        assertTrue(feedback.contains("**A**"));
        assertTrue(feedback.contains("L"));
    }

    @Test
    void testGuessHistoryUpdates() {
        WordleGame game = new WordleGame("APPLE");
        game.submitGuess("BERRY");
        List<String> history = game.getGuessHistory();
        assertEquals(1, history.size());
        assertEquals("BERRY", history.get(0));
    }
}