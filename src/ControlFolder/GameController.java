package ControlFolder;

import moduleFolder.DecodingBoard;
import moduleFolder.HintPeg;
import moduleFolder.Peg;
import moduleFolder.Player;

import java.awt.*;
import java.util.ArrayList;

/*
 * Description: The GameController class will be the driver,and controller object, of the class.
 */
public class GameController {

    // TODO: remove Pegs and just have colors or have decodiing board store pegs not colors?
    private DecodingBoard decodingBoard;
    private Player p1;
    private Player p2;
    private final int NUMBER_OF_TURNS_ALLOWED = 8;
    private int numOfTurnsLeft = NUMBER_OF_TURNS_ALLOWED;

    public GameController(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        decodingBoard = new DecodingBoard();
    }

    public void setCodeMakerPegColors(Peg[] codePegs) {
        Color[] colors = changeArrayOfPegsToArrayOfColors(codePegs);
        decodingBoard.setCMakeRowColor(colors);
    }

    public Peg[] getCodeMakerPegs() {
        Color[] colors = decodingBoard.getCMakeRowColor();
        return changeArrayOfColorsToArrayOfPegs(colors);
    }

    public void setCodeBreakerGuessPegs(Peg[] pegs, int row) {
        Color[] colors = changeArrayOfPegsToArrayOfColors(pegs);
        decodingBoard.setCBreakRowColor(colors, row);
    }

    public Peg[] getCodeMakersGuessPegs(int row) {
        Color[] colors = decodingBoard.getCBreakRowColor(row);
        return changeArrayOfColorsToArrayOfPegs(colors);
    }

    private Color[] changeArrayOfPegsToArrayOfColors(Peg[] pegs) {
        Color[] colors = new Color[pegs.length];
        for (int i = 0; i < pegs.length; i++) {
            colors[i] = pegs[i].getPegColor();
        }

        return colors;
    }

    private Peg[] changeArrayOfColorsToArrayOfPegs(Color[] colors) {
        Peg[] pegs = new Peg[colors.length];
        for (int i = 0; i < colors.length; i++) {
            pegs[i] = new Peg(colors[i]);
        }

        return pegs;
    }

    public HintPeg[] getHintPegs(int row) {
        Color[] codedPegs = decodingBoard.getCMakeRowColor();
        Color[] guessPegs = decodingBoard.getCBreakRowColor(row);
        int numOfPegs = codedPegs.length;

        HintPeg[] hintPegs = new HintPeg[numOfPegs];
        HintPeg hintPeg = null;


        for ( int i = 0, j ;i < codedPegs.length; i++) {
            int lastPositionOfMatchingColor = 0;
            j = 0;
            for ( ; j < guessPegs.length; j++) {
                if ((codedPegs[i]) == guessPegs[j] && (i == j)) {
                    hintPegs[j] = new HintPeg(Color.BLACK);
                    lastPositionOfMatchingColor = j;
                    break;
                } else if (codedPegs[i] == guessPegs[j] ) {
                    hintPeg = new HintPeg(Color.WHITE);
                    lastPositionOfMatchingColor = j;
                }
            }

            if (hintPegs[lastPositionOfMatchingColor] == null) {
                hintPegs[lastPositionOfMatchingColor] = hintPeg;
            }
            hintPeg = null;
        }

        return hintPegs;
    }

}
