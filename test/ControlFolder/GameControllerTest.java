package ControlFolder;

import VisualFolder.Game;
import moduleFolder.HintPeg;
import moduleFolder.Peg;
import moduleFolder.Player;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;

/**
 * Created by AlexL on 5/11/14.
 */
public class GameControllerTest {

    Player player1 = new Player();
    Player player2 = new Player();

    @Test
    public void shouldDecrementNumberOfStepsLeft() {
        // Given
        GameController gameController = new GameController(player1, player2);
        int initialNumber = gameController.getNumberOfStepsLeft();
        int finalNumber;

        Peg[] guessPegs = new Peg[]{
                new Peg(Color.CYAN),
                new Peg(Color.CYAN),
                new Peg(Color.CYAN),
                new Peg(Color.CYAN)
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
        GameController gameController = new GameController(player1, player2);

        Peg[] codePegs = new Peg[]{
                new Peg(Color.GREEN),
                new Peg(Color.RED),
                new Peg(Color.BLUE),
                new Peg(Color.YELLOW)
        };

        Peg[] guessPegs = new Peg[]{
                new Peg(Color.CYAN),
                new Peg(Color.CYAN),
                new Peg(Color.CYAN),
                new Peg(Color.CYAN)
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
        GameController gameController = new GameController(player1, player2);

        Peg[] codePegs = new Peg[]{
                new Peg(Color.GREEN),
                new Peg(Color.RED),
                new Peg(Color.BLUE),
                new Peg(Color.YELLOW)
        };

        // When
        gameController.setCodeMakerPegColors(codePegs);
        Peg[] result = gameController.getCodeMakerPegs();

        // Then
        for (int i = 0; i < codePegs.length; i++) {
            assert codePegs[i].getPegColor() == result[i].getPegColor();
        }
    }

    @Test
    public void shouldPassCodeBreakersGuessPegsToBoard() {
        // Given
        GameController gameController = new GameController(player1, player2);

        int currentRow = 2;
        Peg[] codePegs = new Peg[]{
                new Peg(Color.GREEN),
                new Peg(Color.RED),
                new Peg(Color.BLUE),
                new Peg(Color.YELLOW)
        };

        // When
        gameController.setCodeBreakerGuessPegs(codePegs, currentRow);
        Peg[] result = gameController.getCodeMakersGuessPegs(currentRow);

        // Then
        for (int i = 0; i < codePegs.length; i++) {
            assert codePegs[i].getPegColor() == result[i].getPegColor();
        }
    }

    @Test
    public void shouldReturnNoHintPegs() {
        // Given
        GameController gameController = new GameController(player1, player2);

        int currentRow = 1;
        Peg[] codePegs = new Peg[]{
                new Peg(Color.GREEN),
                new Peg(Color.RED),
                new Peg(Color.BLUE),
                new Peg(Color.YELLOW)
        };

        Peg[] guessPegs = new Peg[]{
                new Peg(Color.CYAN),
                new Peg(Color.CYAN),
                new Peg(Color.CYAN),
                new Peg(Color.CYAN)
        };

        HintPeg[] expectedPegs = new HintPeg[]{null, null, null, null};
        gameController.setCodeMakerPegColors(codePegs);

        // When
        gameController.setCodeBreakerGuessPegs(guessPegs, currentRow);

        // Then
        HintPeg[] actualPegs = gameController.getHintPegs(currentRow);
        for (int i = 0; i < codePegs.length; i++) {
            assert expectedPegs[i] == actualPegs[i];
        }
    }

    @Test
    public void shouldReturnOneWhiteHintPegForCorrectColorButWrongPosition() {
        // Given
        GameController gameController = new GameController(player1, player2);

        int currentRow = 1;
        Peg[] codePegs = new Peg[]{
                new Peg(Color.GREEN),
                new Peg(Color.RED),
                new Peg(Color.BLUE),
                new Peg(Color.YELLOW)
        };

        Peg[] guessPegs = new Peg[]{
                new Peg(Color.CYAN),
                new Peg(Color.GREEN),
                new Peg(Color.CYAN),
                new Peg(Color.CYAN)
        };

        HintPeg[] expectedPegs = new HintPeg[]{null, new HintPeg(Color.WHITE), null, null};
        gameController.setCodeMakerPegColors(codePegs);

        // When
        gameController.setCodeBreakerGuessPegs(guessPegs, currentRow);

        // Then
        HintPeg[] actualPegs = gameController.getHintPegs(currentRow);
        for (int i = 0; i < codePegs.length; i++) {
            if ((actualPegs[i] != null)) {
                assert expectedPegs[i].getPegColor() == actualPegs[i].getPegColor();
            }
        }
    }

    @Test
    public void shouldReturnOneBlackHintPegForCorrectColorAndCorrectPosition() {
        // Given
        GameController gameController = new GameController(player1, player2);

        int currentRow = 1;
        Peg[] codePegs = new Peg[]{
                new Peg(Color.RED),
                new Peg(Color.GREEN),
                new Peg(Color.BLUE),
                new Peg(Color.YELLOW)
        };

        Peg[] guessPegs = new Peg[]{
                new Peg(Color.GREEN),
                new Peg(Color.GREEN),
                new Peg(Color.CYAN),
                new Peg(Color.CYAN)
        };

        HintPeg[] expectedPegs = new HintPeg[]{null, new HintPeg(Color.BLACK), null, null};
        gameController.setCodeMakerPegColors(codePegs);

        // When
        gameController.setCodeBreakerGuessPegs(guessPegs, currentRow);

        // Then
        HintPeg[] actualPegs = gameController.getHintPegs(currentRow);
        for (int i = 0; i < codePegs.length; i++) {
            if ((actualPegs[i] != null)) {
                assert expectedPegs[i].getPegColor() == actualPegs[i].getPegColor();
            }
        }
    }

    @Test
    public void shouldReturnOneBlackHintPegForSameCodeColorsScenario() {
        // Given
        GameController gameController = new GameController(player1, player2);

        int currentRow = 1;
        Peg[] codePegs = new Peg[]{
                new Peg(Color.RED),
                new Peg(Color.RED),
                new Peg(Color.RED),
                new Peg(Color.RED)
        };

        Peg[] guessPegs = new Peg[]{
                new Peg(Color.GREEN),
                new Peg(Color.GREEN),
                new Peg(Color.RED),
                new Peg(Color.CYAN)
        };

        HintPeg[] expectedPegs = new HintPeg[]{null, null, new HintPeg(Color.BLACK), null};
        gameController.setCodeMakerPegColors(codePegs);

        // When
        gameController.setCodeBreakerGuessPegs(guessPegs, currentRow);

        // Then
        HintPeg[] actualPegs = gameController.getHintPegs(currentRow);
        for (int i = 0; i < codePegs.length; i++) {
            if ((actualPegs[i] != null)) {
                actualPegs[i].getPegColor();
                assert expectedPegs[i].getPegColor() == actualPegs[i].getPegColor();
            }
        }
    }

    @Test
    public void shouldReturnOneBlackHintPegAndWhiteHingPeg() {
        // Given
        GameController gameController = new GameController(player1, player2);

        int currentRow = 1;
        Peg[] codePegs = new Peg[]{
                new Peg(Color.BLUE),
                new Peg(Color.RED),
                new Peg(Color.RED),
                new Peg(Color.RED)
        };

        Peg[] guessPegs = new Peg[]{
                new Peg(Color.RED),
                new Peg(Color.GREEN),
                new Peg(Color.RED),
                new Peg(Color.CYAN)
        };

        HintPeg[] expectedPegs = new HintPeg[]{null, new HintPeg(Color.WHITE), new HintPeg(Color.BLACK), null};
        gameController.setCodeMakerPegColors(codePegs);

        // When
        gameController.setCodeBreakerGuessPegs(guessPegs, currentRow);

        // Then
        HintPeg[] actualPegs = gameController.getHintPegs(currentRow);
        for (int i = 0; i < codePegs.length; i++) {
            if ((actualPegs[i] != null)) {
                actualPegs[i].getPegColor();
                assert expectedPegs[i].getPegColor() == actualPegs[i].getPegColor();
            }
        }
    }

    @Test
    public void shouldReturnAllBlackHintPegs() {
        // Given
        GameController gameController = new GameController(player1, player2);

        int currentRow = 1;
        Peg[] codePegs = new Peg[]{
                new Peg(Color.RED),
                new Peg(Color.RED),
                new Peg(Color.RED),
                new Peg(Color.RED)
        };

        Peg[] guessPegs = new Peg[]{
                new Peg(Color.RED),
                new Peg(Color.RED),
                new Peg(Color.RED),
                new Peg(Color.RED)
        };

        HintPeg[] expectedPegs = new HintPeg[]{new HintPeg(Color.BLACK), new HintPeg(Color.BLACK), new HintPeg(Color.BLACK), new HintPeg(Color.BLACK)};
        gameController.setCodeMakerPegColors(codePegs);

        // When
        gameController.setCodeBreakerGuessPegs(guessPegs, currentRow);

        // Then
        HintPeg[] actualPegs = gameController.getHintPegs(currentRow);
        for (int i = 0; i < codePegs.length; i++) {
            if ((actualPegs[i] != null)) {
                actualPegs[i].getPegColor();
                assert expectedPegs[i].getPegColor() == actualPegs[i].getPegColor();
            }
        }
    }

    @Test
    public void shouldReturnAllWhiteHintPegs() {
        // Given
        GameController gameController = new GameController(player1, player2);

        int currentRow = 1;
        Peg[] codePegs = new Peg[]{
                new Peg(Color.RED),
                new Peg(Color.BLUE),
                new Peg(Color.CYAN),
                new Peg(Color.RED)
        };

        Peg[] guessPegs = new Peg[]{
                new Peg(Color.BLUE),
                new Peg(Color.RED),
                new Peg(Color.RED),
                new Peg(Color.CYAN)
        };

        HintPeg[] expectedPegs = new HintPeg[]{new HintPeg(Color.WHITE), new HintPeg(Color.WHITE), new HintPeg(Color.WHITE), new HintPeg(Color.WHITE)};
        gameController.setCodeMakerPegColors(codePegs);

        // When
        gameController.setCodeBreakerGuessPegs(guessPegs, currentRow);

        // Then
        HintPeg[] actualPegs = gameController.getHintPegs(currentRow);
        for (int i = 0; i < codePegs.length; i++) {
            if ((actualPegs[i] != null)) {
                actualPegs[i].getPegColor();
                assert expectedPegs[i].getPegColor() == actualPegs[i].getPegColor();
            }
        }
    }
}
