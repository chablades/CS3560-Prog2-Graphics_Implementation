

import model.Dictionary;
import model.WordleGame;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Combined test suite for Dictionary and WordleGame.
 * Place this file in src/test/java/model/
 * Requires JUnit 5 (org.junit.jupiter.api).
 */
public class JvmTests {

    // ---------------- Dictionary Tests ----------------

    @Test
    void testDictionaryLoadsWords() {
        Dictionary dict = new Dictionary();
        assertTrue(dict.size() > 0, "Dictionary should contain words");
    }

    @Test
    void testValidWord() {
        Dictionary dict = new Dictionary();
        assertTrue(dict.isValidWord("APPLE"), "APPLE should be valid if in dictionary");
    }

    @Test
    void testRandomWordIsFiveLetters() {
        Dictionary dict = new Dictionary();
        String word = dict.getRandomWord();
        assertEquals(5, word.length(), "Random word should be 5 letters long");
    }

    // ---------------- WordleGame Tests ----------------

    @Test
    void testCorrectGuessWinsGame() {
        WordleGame game = new WordleGame("APPLE"); // fixed secret word
        game.submitGuess("APPLE");
        assertTrue(game.inGuessList("APPLE"));
        assertEquals("APPLE", game.getSecretWord());
    }

    @Test
    void testWrongGuessDoesNotWin() {
        WordleGame game = new WordleGame("APPLE");
        game.submitGuess("BERRY");
        assertFalse(game.inGuessList("APPLE"));
        assertTrue(game.inGuessList("BERRY"));
    }

    @Test
    void testMaxAttemptsEndsGame() {
        WordleGame game = new WordleGame("APPLE");
        for (int i = 0; i < WordleGame.getMaxAttempts(); i++) {
            game.submitGuess("BERRY");
        }
        assertTrue(game.inGuessList("BERRY"));
    }

    @Test
    void testInvalidWordRejected() {
        WordleGame game = new WordleGame("APPLE");
        assertFalse(game.isValidWord("ZZZZZ"), "ZZZZZ should not be valid");
    }
}