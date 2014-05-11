package moduleTestbench; /**
 * Testbench for Peg and hintPeg
 * 05/05: first edited -- AC
 * 
 */

import moduleFolder.Peg;
import moduleFolder.HintPeg;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestPeg extends JFrame {
	/** constructor */
    public TestPeg() {
        setTitle("Test Peg Color Decoding");
        setLayout(new GridLayout(2,4));
        
        // set Peg color by random number
        Peg p = new Peg();
        HintPeg hp = new HintPeg();
        
        // generate random # between 1 and 6 for peg
        for (int i=0; i<6; i++) {
        	int pRandom = (int)(Math.random()*(6) + 1);
        	//System.out.println("pRandom: " + pRandom);
        	add(new PegPanel(p.decodePegColor(pRandom)));
        	System.out.println(p.getPegColor());
        }
        
        // generate random # between 1 and 2 for key peg
        for (int j=0; j<2; j++) {
        	int hpRandom = (int)(Math.random()*(2) + 1);
        	//System.out.println("hpRandom: " + hpRandom);
        	add(new PegPanel(hp.decodePegColor(hpRandom)));
        }
        
        // test hintPeg inherited methods from Peg
        hp.setPegColor(Color.BLUE);
    	System.out.println(hp.getPegColor());
    }
    
    /** main */
    public static void main(String[] args) {
        TestPeg tp = new TestPeg();
                 
        tp.setSize(400, 300);
        tp.setLocationRelativeTo(null);
        tp.setDefaultCloseOperation(EXIT_ON_CLOSE);
        tp.setVisible(true);
    }
    
    /** draw peg */
    class PegPanel extends JPanel {
    	private Color c;
    	
    	public PegPanel() {}
    	
    	public PegPanel(Color c) {
    		this.c = c;
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
