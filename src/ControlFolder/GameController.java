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
        Color[] colors = new Color[codePegs.length];
        for (int i = 0; i < codePegs.length; i++) {
            colors[i] = codePegs[i].getPegColor();
        }

        decodingBoard.setCMakeRowColor(colors);
    }

    public Peg[] getCodeMakerPegs() {
        Color[] colors = decodingBoard.getCMakeRowColor();

        Peg[] pegs = new Peg[colors.length];
        for (int i = 0; i < colors.length; i++) {
            pegs[i] = new Peg(colors[i]);
        }

        return pegs;
    }

    public HintPeg[] getHintColors() {

        return null;
    }

}
