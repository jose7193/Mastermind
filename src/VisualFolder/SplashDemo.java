/*
 * SplashDemo.java
 * Dependencies: splash_screen.gif
 * run from the command line: java -splash:images/splash_screen.gif SplashDemo
 * Author: Dave Buxton
 */
package VisualFolder;

import java.awt.*;
import java.awt.event.*;
port javax.swing.JFrame;
import javax.swing.*;

@SuppressWarnings("serial")
public class SplashDemo extends JFrame implements ActionListener {
	static void renderSplashFrame(Graphics2D g, int frame) {
        Font font = new Font("Verdana", Font.BOLD, 14);
        g.setFont(font);
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(7,380,400,40);
        g.setPaintMode();
        g.setColor(new Color(10, 65, 164));
    }
    public SplashDemo() {
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
		Game.main(null);
        setVisible(true);
        toFront();
    }
    public void actionPerformed(ActionEvent ae) {
        System.exit(0);
    }
    
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		SplashDemo s = new SplashDemo();
    }
}
