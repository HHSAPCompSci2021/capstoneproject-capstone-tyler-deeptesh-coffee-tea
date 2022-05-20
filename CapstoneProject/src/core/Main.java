package core;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;


import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

public class Main {

	public static void main(String args[]) {
		
		ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
	    root.setLevel(ch.qos.logback.classic.Level.ERROR); 

		DrawingSurface drawing = new DrawingSurface();
		PApplet.runSketch(new String[]{""}, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame)canvas.getFrame();

		window.setSize(800, 600);
		window.setMinimumSize(new Dimension(100,100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.addWindowListener(new WindowAdapter() {
			 @Override
		        public void windowClosing(WindowEvent e) {
		            super.windowClosing(e); 
		            System.out.print("exited");
		        }
		});
		
		window.setResizable(true);

		window.setVisible(true);
		
		
		canvas.requestFocus();
	}

}
