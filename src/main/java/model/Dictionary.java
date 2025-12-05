package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dictionary {

    private final List<String> words = new ArrayList<>();
    private final Random random = new Random();

    public Dictionary() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("/dictionary.txt")))) {

            String line;
            while ((line = br.readLine()) != null) {
                String w = line.trim();
                if (!w.isEmpty()) {
                    words.add(w.toUpperCase());
                }
            }
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Could not load dictionary.txt", e);
        }

        if (words.isEmpty()) {
            throw new RuntimeException("Dictionary is empty.");
        }
    }

    public boolean isValidWord(String word) {
        return words.contains(word.toUpperCase());
    }

    public String getRandomWord() {
        return words.get(random.nextInt(words.size()));
    }
}