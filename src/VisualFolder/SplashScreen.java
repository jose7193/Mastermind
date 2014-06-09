package VisualFolder;

/*
 * SplashScreen.java
 * Dependencies: splash_screen.gif
 * run from the command line: java -splash:images/splash_screen.gif SplashScreen
 * or run the jar file from the command line: java -jar mastermind.jar
 * Author: Dave Buxton
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class SplashScreen extends JFrame implements ActionListener {
    static void renderSplashFrame(Graphics2D g, int frame) {
        Font font = new Font("Verdana", Font.BOLD, 14);
        g.setFont(font);
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(7,380,400,40);
        g.setPaintMode();
        g.setColor(new Color(10, 65, 164));
    }
    public SplashScreen() {
        final java.awt.SplashScreen splash = java.awt.SplashScreen.getSplashScreen();
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
        setVisible(false);
        toFront();
    }
    public void actionPerformed(ActionEvent ae) {
        System.exit(0);
    }

}

