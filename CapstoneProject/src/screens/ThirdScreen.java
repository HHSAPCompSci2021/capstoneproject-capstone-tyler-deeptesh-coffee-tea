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
import processing.core.PImage;
//import sprites.Mario;
//import sprites.Sprite;

/** 
 * Subclass representing a the during battle screen
 * @author tylertamura
 * @author deepteshday
 */
public class ThirdScreen extends Screen {
	
	private DrawingSurface surface;
	
	private Rectangle ground;
	
	private Robot me;


	public ThirdScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;
		ground = new Rectangle(-1000,500,DRAWING_WIDTH + 2000,1000);
	}


	public void spawnNewRobot() {
		PImage image = surface.loadImage("images/robot.png");
		me = new Robot(surface.weaponSelection, surface.armorSelection, surface.abilitySelection, 600, 100, image);
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
		

		surface.background(0,0,0);   

//		for (Sprite s : obstacles) {
//			s.draw(surface);
//		}
		me.draw(surface);

		if (surface.isPressed(KeyEvent.VK_ESCAPE)) {
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
//			return;
		}
		if (surface.isPressed(KeyEvent.VK_W) && me.onGround) {
			me.jump();
//			return;
		}
		if (surface.isPressed(KeyEvent.VK_A)) {
			me.left();
//			return;
		}
		if (surface.isPressed(KeyEvent.VK_S)) {
			me.down();
//			return;
		}
		if (surface.isPressed(KeyEvent.VK_D)) {
			me.right();
//			return;
		}
		me.act();
		if (ground.intersects(me)) {
			me.onGround = true;
			me.moveToLocation(me.x, 239);
		} else { 
			me.onGround = false;
		}

	}

	
}