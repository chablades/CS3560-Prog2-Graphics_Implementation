package model;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Dictionary {
    private final Set<String> words = new HashSet<>();

    public Dictionary() {
        // Try to load dictionary.txt from resources
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("dictionary.txt")) {
            if (in == null) {
                throw new IllegalStateException("dictionary.txt not found in resources!");
            }
            try (Scanner scanner = new Scanner(in)) {
                while (scanner.hasNextLine()) {
                    String word = scanner.nextLine().trim().toUpperCase();
                    if (word.length() == 5 && word.matches("[A-Z]+")) {
                        words.add(word);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Could not load dictionary.txt from resources: " + e.getMessage());
        }
    }

    public boolean isValidWord(String guess) {
        return words.contains(guess.toUpperCase());
    }

    public String getRandomWord() {
        if (words.isEmpty()) {
            return "APPLE"; // fallback if dictionary fails to load
        }
        int index = (int) (Math.random() * words.size());
        return words.stream().skip(index).findFirst().orElse("APPLE");
    }

    public int size() {
        return words.size();
    }
}