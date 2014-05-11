package moduleTestbench; /**
 * Testbench for Decoding Board
 * 05/05: first edited -- AC
 * 
 */

import moduleFolder.DecodingBoard;
import moduleFolder.Peg;
import moduleFolder.HintPeg;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestDecodingBoard extends JFrame {
    private Color[] codeMaker = new Color[4];
    private Color[] codeBreaker = new Color[4];
    private Color[] keyPeg = new Color[4];
    private static final int ROW_NUM = 0;
    private Container c;
    
	public TestDecodingBoard() {
        setTitle("Test Decoding Board");
     	c = getContentPane();
     	
     	// set up board and peg
        DecodingBoard db = new DecodingBoard();
        Peg p = new Peg();
        HintPeg hp = new HintPeg();
        
        // set key peg color
        setLayout(new GridLayout(3,4));
        for (int j=0; j<4; j++) {
        	int hpRandom = (int)(Math.random()*(2) + 1);
        	keyPeg[j] = hp.decodePegColor(hpRandom);
        }
        db.setHintColor(keyPeg, ROW_NUM);
        for (int j=0; j<4; j++) {
        	add(new PegPanel(db.getHintColor(ROW_NUM)[j]));
        }
    	    	
        
        // set code maker peg color
        for (int i=0; i<4; i++) {
     		int pRandom = (int)(Math.random()*(6) + 1);
     		codeMaker[i] = p.decodePegColor(pRandom);
        }
     	db.setCMakeRowColor(codeMaker);
     	for (int i=0; i<4; i++) {
     		add(new PegPanel(db.getCMakeRowColor()[i]));
     	}
     	
    	// set code breaker peg color
    	for (int i=0; i<4; i++) {
     		int pRandom = (int)(Math.random()*(6) + 1);
     		codeBreaker[i] = p.decodePegColor(pRandom);
        }
    	db.setCBreakRowColor(codeBreaker, ROW_NUM);
    	for (int i=0; i<4; i++) {
    		add(new PegPanel(db.getCBreakRowColor(ROW_NUM)[i]));
    	}
    	    	
    }
    
    public static void main(String[] args) {
        TestDecodingBoard tdp = new TestDecodingBoard();
                 
        tdp.setSize(200, 200);
        tdp.setLocationRelativeTo(null);
        tdp.setDefaultCloseOperation(EXIT_ON_CLOSE);
        tdp.setVisible(true);
    }
    
    class PegPanel extends JPanel {
    	private Color c;

    	public PegPanel(Color peg) {	
    		c = peg;
    	}
    	
        protected void paintComponent(Graphics g) {
        	super.paintComponent(g);
        	
        	int width = getWidth();
        	int height = getHeight();
        	
        	g.setColor(c);
        	g.fillOval((int)(0.1*width), (int)(0.1*height), 
        			(int)(0.8*width), (int)(0.8*width));
        		
        }
    }
    
}
