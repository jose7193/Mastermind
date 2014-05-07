package moduleFolder;

/*
 * Description: This class will be the main module for the game.
 * 05/05: first edited -- AC
 * 
 */
import java.awt.*;

public class DecodingBoard {
    // board constants
    private static final int ROW = 12;
    private static final int COL = 4;
    private static final int HINT_COL = 4;
    
    // board: 1) 12x4 for 
    private Color[][] board = new Color[ROW][COL];    
    private Color[][] hint  = new Color[ROW][HINT_COL];
    private Color[] codeMakerColor = new Color[COL];
    
    // constructors
    public DecodingBoard() {
        for (int i=0; i<ROW; i++) {
            for (int j=0; j<COL; j++) {
                board[i][j] = null;
            }
        }
    }
    
    // clear the board when game starts again
    public void clearBoard() {
        for (int i=0; i<ROW; i++) {
            for (int j=0; j<COL; j++) {
                board[i][j] = null;
            }
        }
    }
    
    // set code-maker color; only one set of colors
    // (4 in a row): no row number needs to be specified
    public void setCMakeRowColor(Color[] inColor) {
        System.arraycopy(inColor, 0, codeMakerColor, 0, inColor.length);
    }
    
    // (4 in a row): get code-maker color
    public Color[] getCMakeRowColor() {
        return codeMakerColor;
    }
    
    // (4 in a row): set code-breaker color, specify row (0-11)
    public void setCBreakRowColor(Color[] inColor, int rowNum) {
        System.arraycopy(inColor, 0, board[rowNum], 0, inColor.length);
    }
    
    // (4 in a row): get code-breaker color, specify row (0-11)
    public Color[] getCBreakRowColor(int rowNum) {
        return board[rowNum];
    }
    
    // (4 in a row): set key peg/feedback color, specify row
    public void setHintColor(Color[] inColor, int rowNum) {
        System.arraycopy(inColor, 0, hint[rowNum], 0, inColor.length);
    }
    
    // (4 in a row): get key peg/feedback color
    public Color[] getHintColor(int rowNum) {
        return hint[rowNum];
    }
    
}
