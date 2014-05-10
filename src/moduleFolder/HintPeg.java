//package moduleFolder;

/*
 * Description: This sets/gets the key peg color
 * 05/05: first edited -- AC
 * 
 */

import java.awt.Color;

// inherits from Peg class for getPegColor
// and setPegColor
public class HintPeg extends Peg {
    private Color pegColor;
    
    public HintPeg() {};
    
    public HintPeg(Color c) {
    	super(c);
    }
    
    // hint peg: only black and white
    // black: correct in both color and position
    // white: correct in color but wrong position
    public Color decodePegColor(int i) {
        switch (i) {
            case (1): pegColor = Color.BLACK; break;
            case (2): pegColor = Color.WHITE; break;
        } 
        
        return pegColor;
    }
    
}

