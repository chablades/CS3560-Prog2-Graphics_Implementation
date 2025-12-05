package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LetterFeedbackTest {

    @Test
    void testEnumValues() {
        assertEquals(3, LetterFeedback.values().length);
        assertNotNull(LetterFeedback.valueOf("CORRECT"));
        assertNotNull(LetterFeedback.valueOf("PRESENT"));
        assertNotNull(LetterFeedback.valueOf("ABSENT"));
    }
}