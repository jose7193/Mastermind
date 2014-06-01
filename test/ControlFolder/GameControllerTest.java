
package ControlFolder;

import org.testng.annotations.Test;

import static ControlFolder.Pegs.*;

/**
 * Created by AlexL on 5/11/14.
 */
public class GameControllerTest {

    @Test
    public void shouldDecrementNumberOfStepsLeft() {
        // Given
        GameController gameController = new GameController();
        int initialNumber = gameController.getNumberOfStepsLeft();
        int finalNumber;

        int[] guessPegs = new int[]{
                BLUE.getIntValue(),
                BLUE.getIntValue(),
                BLUE.getIntValue(),
                BLUE.getIntValue()
        };

        // When
        gameController.setCodeBreakerGuessPegs(guessPegs, gameController.getNumberOfStepsLeft());
        gameController.setCodeBreakerGuessPegs(guessPegs, gameController.getNumberOfStepsLeft());
        finalNumber = gameController.getNumberOfStepsLeft();

        // Then
        assert 8 == initialNumber;
        assert 6 == finalNumber;
    }

    @Test
    public void shouldStartNewGame() {
        // Given
        GameController gameController = new GameController();

        int[] guessPegs = new int[]{
                GREEN.getIntValue(),
                RED.getIntValue(),
                BLUE.getIntValue(),
                ORANGE.getIntValue()
        };

        int[] codePegs = new int[]{
                GREY.getIntValue(),
                GREY.getIntValue(),
                GREY.getIntValue(),
                GREY.getIntValue()
        };

        gameController.setCodeBreakerGuessPegs(codePegs, gameController.getNumberOfStepsLeft());
        gameController.setCodeMakerPegColors(codePegs);

        // When
        gameController.startNewGame();

        // Then
        assert 8 == gameController.getNumberOfStepsLeft();
    }

    @Test
    public void shouldPassCodeMakerPegColorsToBoard() {
        // Given
        GameController gameController = new GameController();

        int[] codePegs = new int[]{
                GREEN.getIntValue(),
                RED.getIntValue(),
                BLUE.getIntValue(),
                ORANGE.getIntValue()
        };

        // When
        gameController.setCodeMakerPegColors(codePegs);
        int[] result = gameController.getCodeMakerPegs();

        // Then
        for (int i = 0; i < codePegs.length; i++) {
            assert codePegs[i] == result[i];
        }
    }

    @Test
    public void shouldPassCodeBreakersGuessPegsToBoard() {
        // Given
        GameController gameController = new GameController();

        int currentRow = 2;
        int[] codePegs = new int[]{
                GREEN.getIntValue(),
                RED.getIntValue(),
                BLUE.getIntValue(),
                ORANGE.getIntValue()
        };

        // When
        gameController.setCodeBreakerGuessPegs(codePegs, currentRow);
        int[] result = gameController.getCodeMakersGuessPegs(currentRow);

        // Then
        for (int i = 0; i < codePegs.length; i++) {
            assert codePegs[i] == result[i];
        }
    }

    @Test
    public void shouldReturnNoHintPegs() {
        // Given
        GameController gameController = new GameController();

        int currentRow = 1;
        int[] codePegs = new int[]{
                GREEN.getIntValue(),
                RED.getIntValue(),
                BLUE.getIntValue(),
                ORANGE.getIntValue()
        };

        int[] guessPegs = new int[]{
                PINK.getIntValue(),
                PINK.getIntValue(),
                PINK.getIntValue(),
                PINK.getIntValue()
        };


        int[] expectedPegs = new int[]{
                NO_VALUE.getIntValue(),
                NO_VALUE.getIntValue(),
                NO_VALUE.getIntValue(),
                NO_VALUE.getIntValue()
        };
        gameController.setCodeMakerPegColors(codePegs);

        // When
        gameController.setCodeBreakerGuessPegs(guessPegs, currentRow);

        // Then
        int[] actualPegs = gameController.getHintPegs(currentRow);
        for (int i = 0; i < codePegs.length; i++) {
            assert expectedPegs[i] == actualPegs[i];
        }
    }

    @Test
    public void shouldReturnOneWhiteHintPegForCorrectColorButWrongPosition() {
        // Given
        GameController gameController = new GameController();

        int currentRow = 1;
        int[] codePegs = new int[]{
                GREEN.getIntValue(),
                RED.getIntValue(),
                BLUE.getIntValue(),
                ORANGE.getIntValue()
        };

        int[] guessPegs = new int[]{
                GREY.getIntValue(),
                GREEN.getIntValue(),
                GREY.getIntValue(),
                GREY.getIntValue()
        };

        int[] expectedPegs = new int[]{
                NO_VALUE.getIntValue(),
                WHITE.getIntValue(),
                NO_VALUE.getIntValue(),
                NO_VALUE.getIntValue()
        };

        gameController.setCodeMakerPegColors(codePegs);

        // When
        gameController.setCodeBreakerGuessPegs(guessPegs, currentRow);

        // Then
        int[] actualPegs = gameController.getHintPegs(currentRow);
        for (int i = 0; i < codePegs.length; i++) {
            assert expectedPegs[i] == actualPegs[i];
        }
    }

    @Test
    public void shouldReturnOneBlackHintPegForCorrectColorAndCorrectPosition() {
        // Given
        GameController gameController = new GameController();

        int currentRow = 1;
        int[] codePegs = new int[]{
                RED.getIntValue(),
                GREEN.getIntValue(),
                BLUE.getIntValue(),
                GREY.getIntValue()
        };

        int[] guessPegs = new int[]{
                GREEN.getIntValue(),
                GREEN.getIntValue(),
                ORANGE.getIntValue(),
                ORANGE.getIntValue()
        };

        int[] expectedHintPegs = new int[]{
                NO_VALUE.getIntValue(),
                BLACK.getIntValue(),
                NO_VALUE.getIntValue(),
                NO_VALUE.getIntValue()
        };
        gameController.setCodeMakerPegColors(codePegs);

        // When
        gameController.setCodeBreakerGuessPegs(guessPegs, currentRow);

        // Then
        int[] actualPegs = gameController.getHintPegs(currentRow);
        for (int i = 0; i < codePegs.length; i++) {
            assert expectedHintPegs[i] == actualPegs[i];
        }
    }

    @Test
    public void shouldReturnOneBlackHintPegForSameCodeColorsScenario() {
        // Given
        GameController gameController = new GameController();

        int currentRow = 1;
        int[] codePegs = new int[]{
                RED.getIntValue(),
                RED.getIntValue(),
                RED.getIntValue(),
                RED.getIntValue()
        };

        int[] guessPegs = new int[]{
                GREEN.getIntValue(),
                GREEN.getIntValue(),
                RED.getIntValue(),
                GREY.getIntValue()
        };

        int[] expectedHintPegs = new int[]{
                NO_VALUE.getIntValue(),
                NO_VALUE.getIntValue(),
                BLACK.getIntValue(),
                NO_VALUE.getIntValue()
        };
        gameController.setCodeMakerPegColors(codePegs);

        // When
        gameController.setCodeBreakerGuessPegs(guessPegs, currentRow);

        // Then
        int[] actualHintPegs = gameController.getHintPegs(currentRow);
        for (int i = 0; i < codePegs.length; i++) {
            assert expectedHintPegs[i] == actualHintPegs[i];
        }
    }


    @Test
    public void shouldReturnOneBlackHintPegAndWhiteHingPeg() {
        // Given
        GameController gameController = new GameController();

        int currentRow = 1;
        int[] codePegs = new int[]{
                BLUE.getIntValue(),
                RED.getIntValue(),
                RED.getIntValue(),
                RED.getIntValue()
        };

        int[] guessPegs = new int[]{
                RED.getIntValue(),
                GREEN.getIntValue(),
                RED.getIntValue(),
                GREY.getIntValue()
        };

        int[] expectedHintPegs = new int[]{
                WHITE.getIntValue(),
                NO_VALUE.getIntValue(),
                BLACK.getIntValue(),
                NO_VALUE.getIntValue()
        };
        gameController.setCodeMakerPegColors(codePegs);

        // When
        gameController.setCodeBreakerGuessPegs(guessPegs, currentRow);

        // Then
        int[] actualHintPegs = gameController.getHintPegs(currentRow);
        for (int i = 0; i < codePegs.length; i++) {
            assert expectedHintPegs[i] == actualHintPegs[i];
        }
    }


    @Test
    public void shouldReturnAllBlackHintPegs() {
        // Given
        GameController gameController = new GameController();

        int currentRow = 1;
        int[] codePegs = new int[]{
                RED.getIntValue(),
                RED.getIntValue(),
                RED.getIntValue(),
                RED.getIntValue()
        };

        int[] guessPegs = new int[]{
                RED.getIntValue(),
                RED.getIntValue(),
                RED.getIntValue(),
                RED.getIntValue()
        };

        int[] expectedHintPegs = new int[]{
                BLACK.getIntValue(),
                BLACK.getIntValue(),
                BLACK.getIntValue(),
                BLACK.getIntValue()};
        gameController.setCodeMakerPegColors(codePegs);

        // When
        gameController.setCodeBreakerGuessPegs(guessPegs, currentRow);

        // Then
        int[] actualHintPegs = gameController.getHintPegs(currentRow);
        for (int i = 0; i < codePegs.length; i++) {
            assert expectedHintPegs[i] == actualHintPegs[i];
        }

    }

    @Test
    public void shouldReturnAllWhiteHintPegs() {
        // Given
        GameController gameController = new GameController();

        int currentRow = 1;
        int[] codePegs = new int[]{
                RED.getIntValue(),
                BLUE.getIntValue(),
                GREY.getIntValue(),
                RED.getIntValue()
        };

        int[] guessPegs = new int[]{
                BLUE.getIntValue(),
                RED.getIntValue(),
                RED.getIntValue(),
                GREY.getIntValue()
        };

        int[] expectedHintPegs = new int[]{
                WHITE.getIntValue(),
                WHITE.getIntValue(),
                WHITE.getIntValue(),
                WHITE.getIntValue()
        };
        gameController.setCodeMakerPegColors(codePegs);

        // When
        gameController.setCodeBreakerGuessPegs(guessPegs, currentRow);

        // Then
        int[] actualHintPegs = gameController.getHintPegs(currentRow);
        for (int i = 0; i < codePegs.length; i++) {
             assert expectedHintPegs[i] == actualHintPegs[i];
        }
    }
}
