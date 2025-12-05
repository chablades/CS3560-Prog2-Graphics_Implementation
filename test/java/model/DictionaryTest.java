package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DictionaryTest {

    @Test
    void testDictionaryLoads() {
        Dictionary dict = new Dictionary();
        assertFalse(dict.getRandomWord().isEmpty());
    }

    @Test
    void testValidWord() {
        Dictionary dict = new Dictionary();
        assertTrue(dict.isValidWord("HELLO")); // ensure HELLO is in dictionary.txt
    }

    @Test
    void testInvalidWord() {
        Dictionary dict = new Dictionary();
        assertFalse(dict.isValidWord("ZZZZZ"));
    }
}