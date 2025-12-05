package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameStateTest {

    @Test
    void testInitialState() {
        GameState state = new GameState("HELLO", 6);

        assertEquals("HELLO", state.getSecretWord());
        assertEquals(6, state.getMaxAttempts());
        assertEquals(0, state.getAttemptsMade());
        assertFalse(state.hasWon());
        assertFalse(state.isGameOver());
    }

    @Test
    void testAddGuessUpdatesState() {
        GameState state = new GameState("HELLO", 6);

        LetterFeedback[] fb = {
                LetterFeedback.CORRECT,
                LetterFeedback.ABSENT,
                LetterFeedback.ABSENT,
                LetterFeedback.ABSENT,
                LetterFeedback.ABSENT
        };

        state.addGuess("HXXXX", fb);

        assertEquals(1, state.getAttemptsMade());
        assertEquals("HXXXX", state.getGuessHistory().get(0));
        assertArrayEquals(fb, state.getFeedbackHistory().get(0));
    }

    @Test
    void testWinCondition() {
        GameState state = new GameState("HELLO", 6);

        LetterFeedback[] fb = {
                LetterFeedback.CORRECT,
                LetterFeedback.CORRECT,
                LetterFeedback.CORRECT,
                LetterFeedback.CORRECT,
                LetterFeedback.CORRECT
        };

        state.addGuess("HELLO", fb);

        assertTrue(state.hasWon());
        assertTrue(state.isGameOver());
    }

    @Test
    void testLoseCondition() {
        GameState state = new GameState("HELLO", 1);

        LetterFeedback[] fb = {
                LetterFeedback.ABSENT,
                LetterFeedback.ABSENT,
                LetterFeedback.ABSENT,
                LetterFeedback.ABSENT,
                LetterFeedback.ABSENT
        };

        state.addGuess("WORLD", fb);

        assertFalse(state.hasWon());
        assertTrue(state.isGameOver());
    }
}