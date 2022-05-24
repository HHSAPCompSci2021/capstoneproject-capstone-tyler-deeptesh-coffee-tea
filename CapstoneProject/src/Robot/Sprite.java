package Robot;

import java.awt.geom.Rectangle2D;
import processing.core.PApplet;
import processing.core.PImage;
import screens.ThirdScreen;


 
public class Sprite extends Rectangle2D.Double {
	
	// FIELDS
	private PImage image;
	
	// CONSTRUCTORS
	/**
	 * Constructor for Sprite
	 * @param x the x coordinate of sprite
	 * @param y the y coordinate of sprite
	 * @param w the width of the sprite
	 * @param h the height of the sprite
	 */
	public Sprite(int x, int y, int w, int h) {
		this(null, x, y, w, h);
	}
	/**
	 * Constructor for Sprite
	 * @param img the image of the sprite
	* @param x the x coordinate of sprite
	 * @param y the y coordinate of sprite
	 * @param w the width of the sprite
	 * @param h the height of the sprite
	 */
	public Sprite(PImage img, int x, int y, int w, int h) {
		super(x,y,w,h);
		image = img;
	}
	
	
	// METHODS	
	/**
	 * Set the sprite location to x , y
	 * @param x the x coordinate to move the sprite
	 * @param y the y coordinate to move the sprite
	 */
	public void moveToLocation(double x, double y) {
		super.x = x;
		super.y = y;
	}
	/**
	 * Adds the value of x , y to current sprite location
	 * @param x the amount of x movement the user wants
	 * @param y the amount of y movement the user wants
	 */
	public void moveByAmount(double x, double y) {
		super.x += x;
		super.y += y;
	}
	/**
	 * Adjusts the Sprite to fit the window change
	 * @param windowWidth gets the window width
	 * @param windowHeight gets the window height
	 */
	public void applyWindowLimits(int windowWidth, int windowHeight) {
		x = Math.min(x,windowWidth-width);
		y = Math.min(y,windowHeight-height);
		x = Math.max(0,x);
		y = Math.max(0,y);
	}
	
	/**
	 * Draws the sprite image and fills it and makes a new rectangle overlapping  the sprite image
	 * @param g Papplet that lets the user to use processing 
	 */
	public void draw(PApplet g) {
		if (image != null)
			g.image(image,(float)x,(float)y,(float)width,(float)height);
		else {
			g.fill(100);
			g.rect((float)x,(float)y,(float)width,(float)height);
		}
	}
	
	
}










