package model;

public class WordleGame {

    private GameState state;
    private final Dictionary dictionary;

    public WordleGame(String secretWord, Dictionary dictionary) {
        this.state = new GameState(secretWord.toUpperCase(), 6);
        this.dictionary = dictionary;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState newState) {
        this.state = newState;
    }

    public String getSecretWord() {
        return state.getSecretWord();
    }

    public int getMaxAttempts() {
        return state.getMaxAttempts();
    }

    public boolean isGameOver() {
        return state.isGameOver();
    }

    public boolean hasWon() {
        return state.hasWon();
    }

    public int getAttemptsMade() {
        return state.getAttemptsMade();
    }

    public GuessResult submitGuess(String guess) throws InvalidGuessException {
        guess = guess.toUpperCase();

        if (guess.length() != state.getSecretWord().length()) {
            throw new InvalidGuessException("Guess must be " + state.getSecretWord().length() + " letters.");
        }

        if (!dictionary.isValidWord(guess)) {
            throw new InvalidGuessException("Not a valid word.");
        }

        if (state.getGuessHistory().contains(guess)) {
            throw new InvalidGuessException("You already guessed that word.");
        }

        LetterFeedback[] fb = evaluateGuess(guess);
        state.addGuess(guess, fb);

        return new GuessResult(guess, fb);
    }

    private LetterFeedback[] evaluateGuess(String guess) {
        String secret = state.getSecretWord();
        int len = secret.length();

        LetterFeedback[] result = new LetterFeedback[len];
        int[] secretCounts = new int[26];

        for (char c : secret.toCharArray()) {
            secretCounts[c - 'A']++;
        }

        for (int i = 0; i < len; i++) {
            char g = guess.charAt(i);
            if (g == secret.charAt(i)) {
                result[i] = LetterFeedback.CORRECT;
                secretCounts[g - 'A']--;
            }
        }

        for (int i = 0; i < len; i++) {
            if (result[i] != null) continue;

            char g = guess.charAt(i);
            if (secretCounts[g - 'A'] > 0) {
                result[i] = LetterFeedback.PRESENT;
                secretCounts[g - 'A']--;
            } else {
                result[i] = LetterFeedback.ABSENT;
            }
        }

        return result;
    }
}