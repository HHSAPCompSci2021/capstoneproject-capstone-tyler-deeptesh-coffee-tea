package core;


import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import com.google.firebase.database.DatabaseReference;

import Robot.*;
//import demo3.Player;
import processing.core.PApplet;
import screens.FirstScreen;
import screens.Screen;
import screens.ScreenSwitcher;
import screens.SecondScreen;
import screens.ThirdScreen;

/** 
 * Class that recieves input, switches screens, and does fire-base
 * @author tylertamura
 * @author deepteshday
 */
public class DrawingSurface extends PApplet implements ScreenSwitcher {

	public float ratioX, ratioY;
	
	private ArrayList<Integer> keys;
	
	private Screen activeScreen;
	private ArrayList<Screen> screens;
	public Armor armorSelection;
	public Weapon weaponSelection;
	public Ability abilitySelection;
	public Robot robot;
     public Robot enemyRobot;
      public Armor enemyArmor;
      public Ability enemyAbility;
      public Weapon enemyWeapon;
    private ArrayList<Integer> keysDown;
    private ArrayList<Robot> robots;
    
	private DatabaseReference roomRef;  // This is the database entry for the whole room
	private DatabaseReference myUserRef;
	
	private boolean currentlySending;  // These field allows us to limit database writes by only sending data once we've received confirmation the previous data went through.
	
	public DrawingSurface() {
		
//		keysDown = new ArrayList<Integer>();
//		robots = new ArrayList<Robot>();
//		
//		this.roomRef = roomRef;
//		currentlySending = false;
//		
		screens = new ArrayList<Screen>();
		
		keys = new ArrayList<Integer>();
		
		
		FirstScreen screen1 = new FirstScreen(this);
		screens.add(screen1);
		
		SecondScreen screen2 = new SecondScreen(this);
		screens.add(screen2);
		
		ThirdScreen screen3 = new ThirdScreen(this);
		screens.add(screen3);
		
		activeScreen = screens.get(0);
	}
	public DrawingSurface(DatabaseReference roomRef) {
		
	}
	 public void run()
     {
   	  if (robots.size() == 0)
				roomRef.removeValueAsync();
			else
				myUserRef.removeValueAsync();
     }
	public void setup() {
		for (Screen s : screens)
			s.setup();
	}
	
	public void draw() {
		ratioX = (float)width/activeScreen.DRAWING_WIDTH;
		ratioY = (float)height/activeScreen.DRAWING_HEIGHT;

		push();
		
		scale(ratioX, ratioY);
		
		activeScreen.draw();
		
		pop();
	}
	
	public void keyPressed() {
		keys.add(keyCode);
		if (key == ESC) { // This prevents a processing program from closing on escape key
			key = 0;}
	}

	public void keyReleased() {
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}
	
	public void mousePressed() {
		activeScreen.mousePressed();
	}
	
	
	public void mouseMoved() {
		activeScreen.mouseMoved();
	}
	
	public void mouseDragged() {
		activeScreen.mouseDragged();
	}
	
	public void mouseReleased() {
		activeScreen.mouseReleased();
	}
	
	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}

	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}
	
	public void getArmor(int i) {
		if (i == 0)
			armorSelection = new LightArmor();
		if (i == 1)
			armorSelection = new MediumArmor();
		if (i == 2)
			armorSelection = new HeavyArmor();
	}
	

	@Override
	public void switchScreen(int i) {
		activeScreen = screens.get(i);
	}

	@Override 
	public void getWeapons(int i) {
		// TODO Auto-generated method stub
		if (i == 0)
			weaponSelection = new Sword();
		if (i == 1)
			weaponSelection = new Spear();
		if (i == 2) 
			weaponSelection = new Hammer();
	}

	@Override
	public void getAbility(int i) {
		if (i == 0)
			abilitySelection = new Meteor();
		if (i == 1)
			abilitySelection = new Kamehameha();
	}
	
	public void terminate() {
		activeScreen.terminate();
	}

}

