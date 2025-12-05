# Project Report — Wordle Clone

## Design Decisions

### Architecture (MVC)
The project follows a strict MVC architecture:
- **Model**: WordleGame, GameState, GuessResult, Dictionary
- **View**: BoardView, KeyboardView, MainFrame
- **Controller**: GameController, InputHandler

The controller manages game flow and row tracking. The view is passive and only renders what the controller tells it. This prevents UI desynchronization.

Swing was chosen for simplicity and portability.

### Data Structures
- GameState stores guess history using:
    - List<String> for guesses
    - List<LetterFeedback[]> for feedback
- Dictionary uses an ArrayList for fast random access
- BoardView uses a 2D array of JLabels to represent tiles

### Algorithms
- Wordle evaluation algorithm:
    - Pass 1: mark CORRECT letters and decrement counts
    - Pass 2: mark PRESENT letters using remaining counts
    - Pass 3: mark ABSENT letters
- Complexity: O(n) per guess

## Challenges Faced

### 1. Row Tracking Bug
- **Problem**: Letters jumped to the next row while typing.
- **Solution**: Moved row tracking into the controller (Option A), removing guesswork from the view.

### 2. Duplicate Letter Logic
- **Problem**: Incorrect PRESENT/ABSENT behavior.
- **Solution**: Implemented true Wordle two-pass evaluation with letter counts.

## What We Learned
- Importance of MVC separation
- How to design testable model logic
- Swing event handling and focus management
- Writing robust JUnit tests

## If We Had More Time
- Add animations (flip, shake)
- Add daily mode and statistics
- Add colorblind mode
- Port to JavaFX