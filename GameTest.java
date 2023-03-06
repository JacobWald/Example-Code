package edu.virginia.cs.hw2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private GameState testGame;

    @Test
    public void testConstructorWithIllegalAnswer() {
        assertThrows(IllegalWordException.class,
                () -> new GameState("QZXYX"));
    }

    @Test
    public void testGameInitializationWithOneGuess() {

        GameState game = new GameState("ruins");

        game.submitGuess("crane");

        assertFalse(game.isGameOver());

    }

    @Test
    public void testCorrectRemainingGuesses() {

        GameState game = new GameState("ruins");

        game.submitGuess("crane");

        assertEquals(5, game.getRemainingGuesses());

    }

    @Test
    public void testFirstGuessCorrectGameState() {

        GameState game = new GameState("crane");

        game.submitGuess("crane");

        assertTrue(game.isWin());

    }

    @Test
    public void testIfGameEndsAfterSixGuesses() {

        GameState game = new GameState("crane");

        game.submitGuess("apple");
        game.submitGuess("ruins");
        game.submitGuess("trunk");
        game.submitGuess("pizza");
        game.submitGuess("thumb");
        game.submitGuess("crank");

        assertTrue(game.isGameOver());

    }

    @Test
    public void testIfGameThrowsExceptionWhenYouTryToSumbitAfterGameIsOver() {

        GameState game = new GameState("crane");

        game.submitGuess("apple");
        game.submitGuess("ruins");
        game.submitGuess("trunk");
        game.submitGuess("pizza");
        game.submitGuess("thumb");
        game.submitGuess("crank");

        assertThrows(GameAlreadyOverException.class, () -> game.submitGuess("cheek"));

    }

    @Test
    public void testIfIllegalWordExceptionIsThrownGuess() {

        GameState game = new GameState("crane");

        assertThrows(IllegalWordException.class, () -> game.submitGuess("62gj1"));

    }

    @Test
    public void testIfIllegalWordExceptionIsThrownAnswer() {
        assertThrows(IllegalWordException.class, () -> new GameState("62gj1"));
    }
}