package screens;



import java.awt.Point;
import java.awt.Rectangle;

import core.DrawingSurface;

/** 
 * Subclass representing a the pre battle screen
 * @author tylertamura
 * @author deepteshday
 */
public class FirstScreen extends Screen {

	private DrawingSurface surface;
	
	private Rectangle button;

	public FirstScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;

		button = new Rectangle(800/2-100,600/2-50,200,100);
	}


	public void draw() {

		surface.background(255,255,255);
		
		surface.rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
		surface.fill(0);
		String str = "BATTLE!";
		float w = surface.textWidth(str);
		surface.text(str, button.x+button.width/2-w/2, button.y+button.height/2);
		
	}



	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (button.contains(p))
			surface.switchScreen(ScreenSwitcher.GAME_SCREEN);
	}
	

}

