package screens;


import java.awt.Rectangle;

import java.awt.Shape;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import Robot.LightArmor;
import Robot.Meteor;
import Robot.Robot;
import Robot.Sword;
import core.DrawingSurface;
//import sprites.Mario;
//import sprites.Sprite;

/** 
 * Subclass representing a the during battle screen
 * @author tylertamura
 * @author deepteshday
 */
public class ThirdScreen extends Screen {
	
	private DrawingSurface surface;
	
	private Rectangle screenRect;
	
	private Robot me;

//	private Mario mario;
//	private List<Sprite> obstacles;

	public ThirdScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;
		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
//		obstacles = new ArrayList<Sprite>();
//		obstacles.add(new Sprite(0,250,100,50));
//		obstacles.add(new Sprite(700,250,100,50));
//		obstacles.add(new Sprite(200,400,400,50));
//		obstacles.add(new Sprite(375,300,50,100));
//		obstacles.add(new Sprite(300,250,200,50));
	}


	public void spawnNewRobot() {
		me = new Robot(new Sword(), new LightArmor(), new Meteor());
	}

	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {
		spawnNewRobot();
	}

	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() {
		
		// drawing stuff
		
		surface.background(128,128,128);   

//		for (Sprite s : obstacles) {
//			s.draw(surface);
//		}
//
//		mario.draw(surface);

		
		// modifying stuff

		if (surface.isPressed(KeyEvent.VK_ESCAPE)) {
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
			return;
		}
//		if (surface.isPressed(KeyEvent.VK_LEFT))
//			mario.walk(-1);
//		if (surface.isPressed(KeyEvent.VK_RIGHT))
//			mario.walk(1);
//		if (surface.isPressed(KeyEvent.VK_UP))
//			mario.jump();
//
//		mario.act(obstacles);
//
//		if (!screenRect.intersects(mario))
//			spawnNewMario();

	}

	
}