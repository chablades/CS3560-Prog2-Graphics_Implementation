package model;

public class GuessResult {
    private final String guess;
    private final LetterFeedback[] feedback;

    public GuessResult(String guess, LetterFeedback[] feedback) {
        this.guess = guess;
        this.feedback = feedback;
    }

    public String getGuess() {
        return guess;
    }

    public LetterFeedback[] getFeedback() {
        return feedback;
    }
}