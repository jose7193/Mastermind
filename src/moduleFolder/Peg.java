package moduleFolder;

/*
 * Description: This sets/gets the peg color
 * 05/05: first edited -- AC
 * 
 */

import java.awt.Color;

public class Peg {
    private Color pegColor;
    
    public Peg() {};
    
    public Peg(Color c) {
        pegColor = c;
    }
     
    public Color decodePegColor(int i) {
        switch (i) {
            case (1): pegColor = Color.CYAN; break;
            case (2): pegColor = Color.GREEN; break;
            case (3): pegColor = Color.RED; break;
            case (4): pegColor = Color.BLUE; break;
            case (5): pegColor = Color.YELLOW; break;
            case (6): pegColor = Color.MAGENTA; break;
        } 
        return pegColor;
    }
    
    public Color getPegColor() {
        return pegColor;
    }
     
    public void setPegColor(Color c) {
         pegColor = c;
    }
}
