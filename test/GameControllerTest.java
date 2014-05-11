import ControlFolder.GameController;
import junit.framework.Assert;
import moduleFolder.DecodingBoard;
import moduleFolder.HintPeg;
import moduleFolder.Peg;
import moduleFolder.Player;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by AlexL on 5/10/14.
 */
public class GameControllerTest {
    Player player1 = new Player();
    Player player2 = new Player();

    GameController gameController = new GameController(player1, player2);

    @Test
    public void shouldReturnNoHintPegs() {
        // Given
        DecodingBoard decodingBoard = new DecodingBoard();
        decodingBoard.setCMakeRowColor(new Color[]{Color.CYAN, Color.CYAN, Color.CYAN, Color.CYAN});
        decodingBoard.setCBreakRowColor(new Color[]{Color.CYAN, Color.CYAN, Color.CYAN, Color.CYAN}, 1);
        // When
        HintPeg[] result = gameController.getHintColors();

        // Then
    }

    @Test
    public void shouldPassCodeMakerPegColorsToBoard() {
        // Given
        Peg[] codePegs = new Peg[] {
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

}
