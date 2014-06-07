package VisualFolder;

import ControlFolder.GameController;
import ControlFolder.Pegs;
import ch.aplu.jgamegrid.*;

import javax.swing.*;
import java.awt.*;

public class Game extends GameGrid implements GGMouseListener {
    private GameController gameController = new GameController();
    private int[] winningPegCombination = new int[4];
    private int currentPegCombinationStep;
    private boolean roundFinished;
    int spriteColor;
    private currentRow marker;
    private int gamePegsOnBoard = 0;

    public Game() {
        super(7, 13, 60, null, "sprites/mastermind_bg_white.png", false);

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
        if (loc.x == 2 && loc.y == 11) {
            spriteColor = 1;
        }
        if (loc.x == 3 && loc.y == 11) {
            spriteColor = 2;
        }
        if (loc.x == 4 && loc.y == 11) {
            spriteColor = 4;
        }
        if (loc.x == 5 && loc.y == 11) {
            spriteColor = 3;
        }

        if (loc.x == 2 && loc.y == 10) {
            spriteColor = 5;
        }
        if (loc.x == 3 && loc.y == 10) {
            spriteColor = 0;
        }
        if (loc.x == 4 && loc.y == 10) {
            spriteColor = 6;
        }
        if (loc.x == 5 && loc.y == 10) {
            spriteColor = 7;
        }
        boolean isClickOnNewGame = (loc.x == 1 && loc.y == 11);
        boolean isClickOnHelpButton = (loc.x == 1 && loc.y == 10);
        boolean isClickOnGamePegs = (loc.y == currentPegCombinationStep) && (loc.x > 1 && loc.x < 6);

        if (isClickOnNewGame) {
            reset();
        }

        if (isClickOnHelpButton) {
            showHelp();
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
                this.addActor(new GamePeg(spriteColor), loc);
                gamePegsOnBoard++;

                // Go! button appears once 4 GamePeg guesses have been established
                if (gamePegsOnBoard == 4) {
                    addActor(new EvaluateButton(), new Location(1, currentPegCombinationStep));
                }
            }

            //insert code for drag and drop, used left and right click as temporary mouse event (see code below)
            else if (mouse.getEvent() == GGMouse.lPress) {
                getOneActorAt(loc).removeSelf();
                gamePegsOnBoard--;
            } else {
                getOneActorAt(loc).removeSelf();
                gamePegsOnBoard--;
            }

        }
        refresh();
        return true;
    }

    private void finishRound() {
        removeActor(marker);
        showSolution();
        roundFinished = true;
    }

    private void showHelp() {
        JPanel contentPanel = new JPanel();
        JFrame frame = new JFrame("Mastermind Help");
        frame.setSize(1300, 500);
        javax.swing.border.Border padding = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        contentPanel.setBorder(padding);
        frame.setContentPane(contentPanel);
        contentPanel.add(new JLabel("<html>\n" +
        "Hello and welcome to MasterMind Help!<br/><br/>\n" +
        "The game is played using:<br/><br/></div>\n" +
        "<ul>\n" +
        "<li>a <em>decoding board</em>, with a <em>shield</em> at one end covering a row of four large holes, and eight additional rows containing four large holes next to a set of four small holes;</li>\n" +
        "<li><em>code pegs</em> of eight different colors, with round heads, which will be placed in the large holes on the board; and</li>\n" +
        "<li><em>key pegs</em>, some colored or black, some white, which are flat-headed and smaller than the code pegs; they will be placed in the small holes on the board.</li>\n" +
        "</ul>\n" + "<p> To set the code pegs, just click on a colored peg. The peg will automatically show in the first available place.<br/> \n" +
        "Once you've set all 4 colors, click on the Go! to confirm and the key pegs will automatically show up.\n" +
        " <br/><br/>\n" +
        "  The key pegs are simple to read: <br/><br/> \n" +
        " A <em>black</em> key peg is placed for each code peg from the guess which is correct in both color and position.<br/>\n" +
        " A <em>white</em> key peg indicates the existence of a correct color code peg placed in the wrong position.<br/><br/>\n" +
        "\n" +
        "The goal is to guess the correct code the computer has generated using the four code pegs.<br/><br/>\n" +
        "Keep guessing until you run out of tries. <br/><br/>\n" +
        "Advanced Hint: Use the code pegs wisely, they are a great help! <br/><br/>\n" +
        "\n" +
        "Good Luck!!\n" +
        "</p>" +
        "</html>",SwingConstants.CENTER));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

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
            GamePeg gamePeg = new GamePeg(spriteColor);
            gamePeg.show(spriteNr);
            addActor(gamePeg, new Location(x, 1) {
            });
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
        } else if (currentPegRow == 2 ) {
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
        super("sprites/activeRowMarker.png");
    }
}

class EvalgamePeg extends Actor {
    public EvalgamePeg(int sprite) {
        // sprite 0 = black, sprite 1 = white
        super(true, "sprites/evalPeg.png", 2);
        show(sprite);
    }
}

class EvaluateButton extends Actor {
    public EvaluateButton() {
        super("sprites/evaluateButton.png");
    }
}

class NewGameButton extends Actor {
    public NewGameButton() {
        super("sprites/new_game.png");
    }
}

class HelpButton extends Actor {
    public HelpButton() {
        super("sprites/help.png");
    }
}

class GamePeg extends Actor {
    public static final int gamePegs = 8;

    public GamePeg(int sprite) {
        super("sprites/gamePeg.png", gamePegs);
        show(sprite);
    }
}

class GameWonGif extends Actor {
    public GameWonGif() {
        super("sprites/game_won.gif");
    }
}

class GameOverGif extends Actor {
    public GameOverGif() {
        super("sprites/gameover_sign.gif");
    }
}
