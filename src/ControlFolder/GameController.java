package ControlFolder;

import moduleFolder.DecodingBoard;
import moduleFolder.Player;

/*
 * Description: The GameController class will be the driver,and controller object, of the class.
 */
public class GameController {

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

    public void calcuteScore() {

    }

}
