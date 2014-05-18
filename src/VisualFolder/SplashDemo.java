
package VisualFolder;
/*
 * SplashDemo.java
 * Dependencies: splash_screen.gif
 * run from the command line: java -splash:images/splash_screen.gif SplashDemo
 * Author: Dave Buxton
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class SplashDemo extends JFrame implements ActionListener {
	//private static final long serialVersionUID = 1L;
	static void renderSplashFrame(Graphics2D g, int frame) {
        //final String[] comps = {"Model", "View", "Control"};
		//String text = "Mastermind Teams: Model View Control";
        Font font = new Font("Verdana", Font.BOLD, 14);
        g.setFont(font);
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(7,380,400,40);
        g.setPaintMode();
        //g.setColor(Color.RED);
        g.setColor(new Color(10, 65, 164));
        //or g.setColor(255, 255, 0);
        //g.drawString("Mastermind Teams: "+comps[(frame/20)%3], 20, 400); //[(frame/5)%3]+"..."
        //g.drawString(text, 20, 400);
        g.drawString("Mastermind Team Manager: Jose De Ita", 7, 390);
        g.drawString("Model Team: Alice Chan, Zita Geguzis", 7, 410);
        g.drawString("View Team: Dave Buxton, Anne Caballero, Saul Hamadani", 7, 430);
        //g.drawString("Saul Hamadani", 10, 450);
        g.drawString("Control Team: Alex Livenson, Felix Ruiz", 7, 450);
    }
    public SplashDemo() {
       super("Splash Screen");
        setSize(300, 200);
        setLayout(new BorderLayout());
        JMenu m1 = new JMenu("File");
        JMenuItem mi1 = new JMenuItem("Exit");
        m1.add(mi1);
        mi1.addActionListener(this);
        this.addWindowListener(closeWindow);

        JMenuBar mb = new JMenuBar();
        setJMenuBar(mb);
        mb.add(m1);
        
        final SplashScreen splash = SplashScreen.getSplashScreen();
        if (splash == null) {
            System.out.println("SplashScreen.getSplashScreen() returned null");
            return;
        }
        Graphics2D g = splash.createGraphics();
        if (g == null) {
            System.out.println("g is null");
            return;
        }
        for(int i=0; i<150; i++) {
            renderSplashFrame(g, i);
            splash.update();
            try {
                Thread.sleep(90);
            }
            catch(InterruptedException e) {
            }
        }
        splash.close();
        setVisible(true);
        toFront();
    }
    public void actionPerformed(ActionEvent ae) {
        System.exit(0);
    }
    
    private static WindowListener closeWindow = new WindowAdapter(){
        public void windowClosing(WindowEvent e){
            e.getWindow().dispose();
        }
    };
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		SplashDemo s = new SplashDemo();
    }
}
