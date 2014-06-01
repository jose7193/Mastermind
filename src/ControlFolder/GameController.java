package ControlFolder;

import moduleFolder.DecodingBoard;

import java.awt.*;

import static ControlFolder.Pegs.*;

/*
 * Description: The GameController class will be the driver,and controller object, of the class.
 */
public class GameController {

    // TODO: remove Pegs and just have colors or have decodiing board store pegs not colors?
    private DecodingBoard decodingBoard;
    private final int NUMBER_OF_TURNS_ALLOWED = 8;
    private int numOfTurnsLeft = NUMBER_OF_TURNS_ALLOWED;

    public GameController() {
        decodingBoard = new DecodingBoard();
    }

    /**
     * Clears all the pegs on the board and resets the number of turns to 8
     */
    public void startNewGame() {
        numOfTurnsLeft = NUMBER_OF_TURNS_ALLOWED;
        decodingBoard.clearBoard();
    }

    public int getNumberOfStepsLeft() {
        return numOfTurnsLeft;
    }

    /**
     * Stores the CodeMaker pegs in the DecodoingBoard modal
     * @param codePegs
     */
    public void setCodeMakerPegColors(int[] codePegs) {
        Color[] colors = changeIntToColor(codePegs);
        decodingBoard.setCMakeRowColor(colors);
    }

    /**
     * Retrieves array of integers from decoding board. The conversion is given in the Pegs enum
     *
     * @return
     */
    public int[] getCodeMakerPegs() {
        Color[] colors = decodingBoard.getCMakeRowColor();
        return changeColorToInt(colors);
    }

    /**
     * Sets Pegs in a specific row of the decoding board and decrements
     * the number of turns the player has
     *
     * @param pegs
     * @param row
     */
    public void setCodeBreakerGuessPegs(int[] pegs, int row) {
        if (numOfTurnsLeft > 0) {
            Color[] colors = changeIntToColor(pegs);
            decodingBoard.setCBreakRowColor(colors, row);
            numOfTurnsLeft--;
        }
    }

    /**
     * Return int array with conversion given in the Pegs enum
     * @param row
     * @return
     */
    public int[] getCodeMakersGuessPegs(int row) {
        Color[] colors = decodingBoard.getCBreakRowColor(row);
        return changeColorToInt(colors);
    }

    /**
     * Get hint pegs from decoding board
     *
     * @param row
     * @return
     */
    public int[] getHintPegs(int row) {
        calculateHintPegs(row);
        return changeColorToInt(decodingBoard.getHintColor(row));
    }

    private Color[] changeIntToColor(int[] intArray) {
        Color[] colors = new Color[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
           colors[i] = Pegs.convertIntToColor(intArray[i]);
        }

        return colors;
    }

    private int[] changeColorToInt(Color[] colorArray) {
        int[] intArray = new int[colorArray.length];
        for (int i = 0; i < colorArray.length; i++) {
            intArray[i] = Pegs.convertColorToInt(colorArray[i]);
        }

        return intArray;
    }

    private void calculateHintPegs(int row) {
        Color[] codedPegs = decodingBoard.getCMakeRowColor();
        Color[] guessPegs = decodingBoard.getCBreakRowColor(row);
        int numOfPegs = codedPegs.length;

        int[] hintPegs = initializeHintPegs(numOfPegs);
        int hintPeg = NO_VALUE.getIntValue();

        for (int i = 0, j; i < codedPegs.length; i++) {
            int currentMatchingColorPosition = -1;
            j = 0;
            for (; j < guessPegs.length; j++) {
                if ((codedPegs[i] == guessPegs[j]) && (i == j)) {
                    hintPegs[j] = BLACK.getIntValue();
                    currentMatchingColorPosition = j;
                    break;
                } else if (codedPegs[i] == guessPegs[j]) {
                    hintPeg = WHITE.getIntValue();
                    if (hintPegs[j] == NO_VALUE.getIntValue()) {
                        currentMatchingColorPosition = j;
                    }
                }
            }

            if ((currentMatchingColorPosition > -1) &&
                    (hintPegs[currentMatchingColorPosition] == NO_VALUE.getIntValue())) {
                hintPegs[currentMatchingColorPosition] = hintPeg;
            }
            hintPeg = NO_VALUE.getIntValue();

        }

        decodingBoard.setHintColor(changeIntToColor(hintPegs), row);
    }

    private int[] initializeHintPegs(int size) {
        int[] hintPegs = new int[size];
        for (int i = 0; i < size; i++) {
            hintPegs[i] = NO_VALUE.getIntValue();
        }

       return hintPegs;
    }
}
