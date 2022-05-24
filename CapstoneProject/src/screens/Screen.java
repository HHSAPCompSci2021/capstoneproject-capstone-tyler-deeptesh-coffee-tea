package screens;

import processing.core.PApplet;

/** 
 * Superclass representing a screen
 * @author tylertamura
 * @author deepteshday
 */
public abstract class Screen {
/**
 * Constructor for Screen class
 */
	public final int DRAWING_WIDTH, DRAWING_HEIGHT;
	
	public Screen(int width, int height) {
		this.DRAWING_WIDTH = width;
		this.DRAWING_HEIGHT = height;
	}
	/**
	 * Setsup the screen
	 */
	public void setup() {
		
	}
	/**
	 * Draws the screen functions on the screen
	 */
	public void draw() {
		
	}
	/**
	 * Checks if the mouse is pressed and if yes does that so in the screen
	 */
	public void mousePressed() {
		
	}
	/**
	 * Checks if the mouse is moved and if so does that in the screen
	 */
	public void mouseMoved() {
		
	}
	/**
	 * Checks if the mouse is dragged and if so does that in the screen
	 */
	public void mouseDragged() {
		
	}
	/**
	 * Checks if the mouse is released and if so does that in the screen
	 */
	public void mouseReleased() {
		
	}
	/**
	 * Terminates the screen
	 */
	public void terminate() {
		
	}
	
	
	
}
