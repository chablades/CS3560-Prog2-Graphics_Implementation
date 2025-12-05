package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WordleGameTest {

    private Dictionary dict;

    @BeforeEach
    void setup() {
        dict = new Dictionary();
    }

    @Test
    void testCorrectGuessEndsGame() throws Exception {
        WordleGame game = new WordleGame("HELLO", dict);

        GuessResult result = game.submitGuess("HELLO");

        assertTrue(game.hasWon());
        assertTrue(game.isGameOver());
        assertEquals("HELLO", result.getGuess());
    }

    @Test
    void testIncorrectGuessDoesNotEndGame() throws Exception {
        WordleGame game = new WordleGame("HELLO", dict);

        GuessResult result = game.submitGuess("WORLD");

        assertFalse(game.hasWon());
        assertFalse(game.isGameOver());
        assertEquals("WORLD", result.getGuess());
    }

    @Test
    void testInvalidLengthThrows() {
        WordleGame game = new WordleGame("HELLO", dict);

        assertThrows(InvalidGuessException.class,
                () -> game.submitGuess("HI"));
    }

    @Test
    void testInvalidWordThrows() {
        WordleGame game = new WordleGame("HELLO", dict);

        assertThrows(InvalidGuessException.class,
                () -> game.submitGuess("XXXXX")); // must not be in dictionary
    }

    @Test
    void testDuplicateGuessThrows() throws Exception {
        WordleGame game = new WordleGame("HELLO", dict);

        game.submitGuess("WORLD");

        assertThrows(InvalidGuessException.class,
                () -> game.submitGuess("WORLD"));
    }

    @Test
    void testFeedbackCorrectPresentAbsent() throws Exception {
        WordleGame game = new WordleGame("HELLO", dict);

        GuessResult result = game.submitGuess("WORLD");
        LetterFeedback[] fb = result.getFeedback();

        assertEquals(LetterFeedback.ABSENT, fb[0]); // W
        assertEquals(LetterFeedback.PRESENT, fb[1]); // O
        assertEquals(LetterFeedback.ABSENT, fb[2]); // R
        assertEquals(LetterFeedback.CORRECT, fb[3]); // L
        assertEquals(LetterFeedback.ABSENT, fb[4]); // D
    }

    @Test
    void testDuplicateLetterHandling() throws Exception {
        WordleGame game = new WordleGame("LEVEL", dict);

        GuessResult result = game.submitGuess("LEMON");
        LetterFeedback[] fb = result.getFeedback();

        assertEquals(LetterFeedback.CORRECT, fb[0]); // L
        assertEquals(LetterFeedback.CORRECT, fb[1]); // E
        assertEquals(LetterFeedback.ABSENT,  fb[2]); // M
        assertEquals(LetterFeedback.ABSENT,  fb[3]); // O
        assertEquals(LetterFeedback.ABSENT, fb[4]);
    }
}