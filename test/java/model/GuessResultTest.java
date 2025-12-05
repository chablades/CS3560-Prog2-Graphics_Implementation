package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GuessResultTest {

    @Test
    void testGuessResultStoresData() {
        LetterFeedback[] fb = {
                LetterFeedback.CORRECT,
                LetterFeedback.PRESENT,
                LetterFeedback.ABSENT,
                LetterFeedback.ABSENT,
                LetterFeedback.CORRECT
        };

        GuessResult result = new GuessResult("HELLO", fb);

        assertEquals("HELLO", result.getGuess());
        assertArrayEquals(fb, result.getFeedback());
    }
}