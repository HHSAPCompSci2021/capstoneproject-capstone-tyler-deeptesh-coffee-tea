package screens;



import java.awt.Point;
import java.awt.Rectangle;

import core.DrawingSurface;
import processing.core.*;

/** 
 * Subclass representing a the pre battle screen
 * @author tylertamura
 * @author deepteshday
 */
public class SecondScreen extends Screen {

	private DrawingSurface surface;
	
	private Rectangle button;
	
	private PImage photo;
	

	public SecondScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;


		button = new Rectangle(800/2-100,600/2-50,200,100);
		
	}
	
	public void setup() {
		photo = surface.loadImage("images/SecondLoadingScreen.jpg");
	}


	public void draw() {

		surface.image(photo, 0, 0, 900, 600);

		
		surface.rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
		surface.fill(255);
		String str = "Armor";
		float w = surface.textWidth(str);
		surface.text(str, button.x+button.width/2-w/2, button.y+button.height/2);
		
		surface.rect(button.x, button.y, button.width, button.height, 30, 30, 10, 10);
		surface.fill(255);
		String str1 = "Weapon";
		float w1 = surface.textWidth(str1);
		surface.text(str1, button.x+button.width/2-w1/2, button.y+button.height/2);
		
		surface.rect(button.x, button.y, button.width, button.height, 50, 50, 10, 10);
		surface.fill(255);
		String str2 = "Ability";
		float w2 = surface.textWidth(str2);
		surface.text(str2, button.x+button.width/2-w2/2, button.y+button.height/2);
		
	}



	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (button.contains(p))
			surface.switchScreen(ScreenSwitcher.GAME_SCREEN);
	}
	

}
