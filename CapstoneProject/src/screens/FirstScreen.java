package screens;



import java.awt.Point;
import java.awt.Rectangle;
import screens.SecondScreen;
import core.DrawingSurface;
import processing.core.*;

/** 
 * Subclass representing a the pre battle screen
 * @author tylertamura
 * @author deepteshday
 */
public class FirstScreen extends Screen {

	private DrawingSurface surface;
	
	private Rectangle button;
	
	private PImage photo;
	
/**
 * The constructor for the first screen
 * @param surface allows the first screen to use drawing surface  and modify drawing surface classs
 */
	public FirstScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;
 

//		button = new Rectangle(800/2-100,600/2-50,200,100);
		button = new Rectangle(800/2-100,450,200,100);
	}
	/**
	 * Loads up the background image for first screen
	 */
	public void setup() {
		photo = surface.loadImage("images/transformer1.png");
	}

/**
 * Draws the first screen buttons 
 */
	public void draw() {

		surface.image(photo, 0, 0, 800, 600);

		
		surface.rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
		surface.fill(0);
		String str = "BATTLE!";
		float w = surface.textWidth(str);
		surface.text(str, button.x+button.width/2-w/2, button.y+button.height/2);
		
	}



	/**
	 * Checks if the Fight button is pressed if yes creates second screen else continues
	 */
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (button.contains(p))
		{
			surface.check = true;
		}
		if(surface.check == true) {
			SecondScreen screen2 = new SecondScreen(surface);
			screen2.setup();
			surface.getScreen().add(screen2);
			surface.switchScreen(ScreenSwitcher.SELECT_SCREEN);
			//switchScreen(ScreenSwitcher.GAME_SCREEN);
		}
//		surface.switchScreen(ScreenSwitcher.SELECT_SCREEN); // I changed the position of this line because if 
															// you click out side the button, it will cause an error
	}
	

}

