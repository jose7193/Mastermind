package VisualFolder;//
/*

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
import ControlFolder.Pegs;
import ch.aplu.jgamegrid.*;

import java.awt.*;

public class Game extends GameGrid implements GGMouseListener {
    private GameController gameController = new GameController();
    private int[] winningPegCombination = new int[4];
    private int currentPegCombinationStep;
    private boolean roundFinished;
    private currentRow marker;
    private int gamePegsOnBoard = 0;

    public Game() {
        super(7, 13, 60, null, "VisualFolder/sprites/mastermind_bg_white.png", false);
        this.addMouseListener(this, GGMouse.lPress | GGMouse.rPress);
        this.setTitle("MasterMind Game - Group 5");
        getBg().setPaintColor(Color.red);
        reset();
        show();

    }

    @Override
    public boolean mouseEvent(GGMouse mouse) {

        if (roundFinished) {
            reset();
            return true;
        }

        // Assigns coordinates where the user clicks on the board to a variable
        Location loc = toLocation(mouse.getX(), mouse.getY());
        boolean isClickOnNewGame = (loc.x == 1 && loc.y == 11);
        boolean isClickOnHelpButton = (loc.x == 1 && loc.y == 10);
        boolean isClickOnGamePegs = (loc.y == currentPegCombinationStep) && (loc.x > 1 && loc.x < 6);

        if (isClickOnNewGame) {
            reset();
        }

        if (isClickOnHelpButton) {
            // Insert pop up for the instructions
        }

        // Coordinates for the "Go!" button which appears when guess slots have been filled up
        if (gamePegsOnBoard == 4 && loc.x == 1 && (loc.y == currentPegCombinationStep)) {
            int[] guess = new int[4];
            for (int i = 0; i < 4; i++) {
                guess[i] = getOneActorAt(new Location(2 + i, currentPegCombinationStep)).getIdVisible();
            }
            gameController.setCodeBreakerGuessPegs(guess, currentPegCombinationStep);
            decodeHintPegs(currentPegCombinationStep);
            currentPegCombinationStep--;
        }

        if (isClickOnGamePegs) {
            if (getOneActorAt(loc) == null) {
                this.addActor(new GamePeg(), loc);
                gamePegsOnBoard++;

                // Go! button appears once 4 GamePeg guesses have been established
                if (gamePegsOnBoard == 4) {
                    addActor(new EvaluateButton(), new Location(1, currentPegCombinationStep));
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
    private void finishRound() {
        removeActor(marker);
        showSolution();
        roundFinished = true;
    }

    // -----------method showHintPegs -----------
    // Show gui pegs for feedback panel
    private void showHintgamePegs(int whitegamePegs, int blackgamePegs) {
        for (int i = 0; i < 4; i++) {
            if (blackgamePegs > 0) {
                EvalgamePeg ep = new EvalgamePeg(0);
                addActor(ep, new Location(1, currentPegCombinationStep));
                ep.turn(90 * i);
                blackgamePegs--;
            } else if (whitegamePegs > 0) {
                EvalgamePeg ep = new EvalgamePeg(1);
                addActor(ep, new Location(1, currentPegCombinationStep));
                ep.turn(90 * i);
                whitegamePegs--;
            }
        }
    }

    private void showSolution() {
        int x = 2;
        for (int spriteNr : winningPegCombination) {
            GamePeg gamePeg = new GamePeg();
            gamePeg.show(spriteNr);
            addActor(gamePeg, new Location(x, 1) {});
            x++;
        }
    }

    public void reset() {
        removeAllActors();
        getBg().clear();
        currentPegCombinationStep = this.nbVertCells - 4;
        roundFinished = false;
        gameController.startNewGame();

        for (int i = 0; i < winningPegCombination.length; i++) {
            winningPegCombination[i] = (int) (Math.random() * GamePeg.gamePegs);
        }

        gameController.setCodeMakerPegColors(winningPegCombination);

        marker = new currentRow();
        addActor(marker, new Location(1, currentPegCombinationStep));

        // Plan to add new actors ; one for each GamePeg in the color selector panel
        addActor(new NewGameButton(), new Location(1, 11));
        addActor(new HelpButton(), new Location(1, 10));
        refresh();
    }

    private void decodeHintPegs(int currentPegRow) {
        int[] hintPegs = gameController.getHintPegs(currentPegRow);
        int blackgamePegs = 0, whitegamePegs = 0;

        for (int i = 0; i < hintPegs.length; i++) {
            if (Pegs.BLACK.getIntValue() == hintPegs[i]) {
                blackgamePegs++;
            }

            if (Pegs.WHITE.getIntValue() == hintPegs[i]) {
                whitegamePegs++;
            }
        }

        showHintgamePegs(whitegamePegs, blackgamePegs);

        if (blackgamePegs == 4) {
            finishRound();
            addActor(new GameWonGif(), new Location(1, 12));
        }

        if (currentPegRow == 2) {
            finishRound();
            addActor(new GameOverGif(), new Location(1, 12));
        }

        marker.setLocation(new Location(1, currentPegRow - 1));
        gamePegsOnBoard = 0;
        removeActors(EvaluateButton.class);
    }
}

class currentRow extends Actor {
    public currentRow() {
        super("VisualFolder/sprites/activeRowMarker.png");
    }
}

class EvalgamePeg extends Actor {
    public EvalgamePeg(int sprite) {
        // sprite 0 = black, sprite 1 = white
        super(true, "VisualFolder/sprites/EvalPeg.png", 2);
        show(sprite);
    }
}

class EvaluateButton extends Actor {
    public EvaluateButton() {
        super("VisualFolder/sprites/EvaluateButton.png");
    }
}

class NewGameButton extends Actor {
    public NewGameButton() {
        super("VisualFolder/sprites/new_game.png");
    }
}

class HelpButton extends Actor {
    public HelpButton() {
        super("VisualFolder/sprites/help.png");
    }
}

class GamePeg extends Actor {
    public static final int gamePegs = 8;

    public GamePeg() {
        super("VisualFolder/sprites/GamePeg.png", gamePegs);
    }
}

class GameWonGif extends Actor {
    public GameWonGif() {
        super("VisualFolder/sprites/game_won.gif");
    }
}

class GameOverGif extends Actor {
    public GameOverGif() {
        super("VisualFolder/sprites/gameover_sign.gif");
    }
}