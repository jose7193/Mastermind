package VisualFolder;//
/*
since backend will be sending a Color array.
We must create a method decodeColortoNumber & decode NumbertoColor
for frontend (unless backend can be chaged

0:blue
1:red
2:black
3:white
4:green
5:orange
6:pink
7:gray



/*
/ @author Anne_Caballero
/
*/

import ControlFolder.GameController;
import ch.aplu.jgamegrid.*;
import java.awt.*;
import moduleFolder.Player;


public class Game extends GameGrid implements GGMouseListener
{
    GameController gameController = new GameController(new Player(), new Player());
    int[] winningPegCombination = new int[4];
    int gamePegCombinationPanel;
    boolean roundFinished;
    currentRow marker;
    private int gamePegsOnBoard = 0;

    public Game()
    {

        super(7, 13, 60, null, "VisualFolder/sprites/mastermind_bg_white.png", false);
        this.addMouseListener(this, GGMouse.lPress | GGMouse.rPress);
        this.setTitle("MasterMind Game - Group 5");
        getBg().setPaintColor(Color.red);
        reset();
        show();

    }

    // -----------method decodeHontPegs -----------
    // records all user interaction with board
    @Override
    public boolean mouseEvent(GGMouse mouse)
    {
        if (roundFinished)
        {
            reset();
            return true;
        }

        // Assigns coordinates where the user clicks on the board to a vairable
        Location loc = toLocation(mouse.getX(), mouse.getY());

        // Coordinates for the "New Game" button
        if(loc.x == 1 && loc.y == 11){
            reset();
        }
        // Coordinates for the "Help" buttom
        if(loc.x == 1 && loc.y == 10){

            // Insert pop up for the instructions

        }

        // Coordinates for the "Go!" button which appears when guess slots have been filled up
        if (gamePegsOnBoard == 4 && loc.x == 1 && loc.y == gamePegCombinationPanel)
        {
            int[] guess = new int[4];
            for (int i = 0; i < 4; i++)

                // Stores 4 player gamePeg guesses to an array. Stores as an integer
                guess[i] = getOneActorAt(new Location(2 + i, gamePegCombinationPanel)).getIdVisible();

            /////////////////////// THE METHOD BELOW WILL PASS THE GUESS PEGS TO GAME CONTROLLER AS INT/////////////
            gameController.setCodeBreakerGuessPegs(guess, loc.y);
            /////////////////////////////////////////////

            decodeHintPegs(gamePegCombinationPanel);
        }

        if (loc.y == gamePegCombinationPanel && loc.x > 1 && loc.x < 6)
        {
            if (getOneActorAt(loc) == null)
            {
                this.addActor(new gamePeg(), loc);
                gamePegsOnBoard++;

                // Go! button appears once 4 gamePeg guesses have been established
                if (gamePegsOnBoard == 4)
                {
                    addActor(new EvaluateButton(), new Location(1, gamePegCombinationPanel));
                }
            }

            //insert code for drag and drop, used left and right click as temporary mouse event (see code below)

            else if (mouse.getEvent() == GGMouse.lPress)
                getOneActorAt(loc).showNextSprite();
            else
                getOneActorAt(loc).showPreviousSprite();

        }
        refresh();
        return true;
    }

    // -----------method finishRound -----------
    private void finishRound(String reason)
    {
        removeActor(marker);
        showSolution();
        roundFinished = true;
    }

    // -----------method showHintPegs -----------
    // Show gui pegs for feedback panel
    private void showHintgamePegs(int whitegamePegs, int blackgamePegs)
    {
        for (int i = 0; i < 4; i++)
        {
            if (blackgamePegs > 0)
            {
                EvalgamePeg ep = new EvalgamePeg(0);
                addActor(ep, new Location(1, gamePegCombinationPanel));
                ep.turn(90 * i);
                blackgamePegs--;
            }
            else if (whitegamePegs > 0)
            {
                EvalgamePeg ep = new EvalgamePeg(1);
                addActor(ep, new Location(1, gamePegCombinationPanel));
                ep.turn(90 * i);
                whitegamePegs--;
            }
        }
    }

    // -----------method showSolution -----------
    // Gui pops out the right combination after game ends
    private void showSolution()
    {
        int x = 2;
        for (int spriteNr : winningPegCombination)
        {
            gamePeg gamePeg = new gamePeg();
            gamePeg.show(spriteNr);
            addActor(gamePeg, new Location(x, 1) {});
            x++;
        }
    }

    // -----------method reset -----------
    // reset the whole board
    // refreshes actors
    public void reset()
    {
        removeAllActors();
        getBg().clear();
        gamePegCombinationPanel = this.nbVertCells - 4;
        roundFinished = false;

        for (int i = 0; i < winningPegCombination.length; i++)
            winningPegCombination[i] = (int)(Math.random() * gamePeg.gamePegs);


        /////////////////////// THE METHOD BELOW WILL PASS THE SECRET CODE PEGS TO GAME CONTROLLER AS INT/////////////
        gameController.setCodeMakerPegColors(winningPegCombination);
        ///////////////////////////////////

        marker = new currentRow();
        addActor(marker, new Location(1, gamePegCombinationPanel));

        // Plan to add new actors ; one for each gamePeg in the color selector panel
        addActor(new newGameButton(), new Location(1, 11));
        addActor(new helpButton(), new Location(1, 10));
        refresh();
    }

    // -----------method decodeHontPegs -----------
    // This is test code. back end programmers must replace it
    // CheckIfPlayer won must call showHintgamePegs method from the frontend design
    private void decodeHintPegs( int gamePegCombinationPanel)
    {

        int blackgamePegs = 0, whitegamePegs = 0;

        ///////////////////////MUST GET ARRAY OF HINTPEGS AS INT FROM GAME CONTROLLER /////////////

        gameController.getHintPegs(gamePegCombinationPanel);

        /////////////////////////////

        //INSERT CODE TO:
        //iterate over the array of hint pegs
        //Make counter for blackgamePegs and whitegamepegs


        whitegamePegs -= blackgamePegs;
        showHintgamePegs(whitegamePegs, blackgamePegs);

        if (blackgamePegs == 4) //correct combination
            finishRound("done");
        else
            gamePegCombinationPanel--; // go to next row

        if (gamePegCombinationPanel == 1) //no more guesses left
            finishRound("done");

        marker.setLocation(new Location(1, gamePegCombinationPanel));
        gamePegsOnBoard = 0;
        removeActors(EvaluateButton.class);
    }


public static void main(String[] args)
  {
      Game mastermind = new Game();
  }
}

// -----------class currentRow -----------
// Assigns GUI for the class
class currentRow extends Actor
{
    public currentRow ()
    {
        super("VisualFolder/sprites/activeRowMarker.png");
    }
}

// ----------class EvalgamePeg -----------------
// Assigns GUI for the class

class EvalgamePeg extends Actor
{
    public EvalgamePeg(int sprite)
    {
        // sprite 0 = black, sprite 1 = white
        super(true, "VisualFolder/sprites/EvalPeg.png", 2);
        show(sprite);
    }
}

// -----------class EvaluateButton------------
// Assigns GUI for the class

class EvaluateButton extends Actor
{
    public EvaluateButton()
    {
        super("VisualFolder/sprites/EvaluateButton.png");
    }
}

//----------class newGameButton----------
// Assigns GUI for the class
class newGameButton extends Actor
{
    public newGameButton()
    {
        super("VisualFolder/sprites/new_game.png");
    }
}

//----------class newGameButton----------
// Assigns GUI for the class

class helpButton extends Actor
{
    public helpButton()
    {
        super("VisualFolder/sprites/help.png");
    }
}
//-------- class gamePeg-------------------------
// Assigns GUI for the class

class gamePeg extends Actor
{
    public static final int gamePegs = 8;
    public gamePeg()
    {
        super("VisualFolder/sprites/gamePeg.png", gamePegs);
    }
}