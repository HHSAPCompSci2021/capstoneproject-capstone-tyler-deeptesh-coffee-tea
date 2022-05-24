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
 * Class that recieves user data, switches screens, and allows for the multiplayer to work
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
	public boolean check;
	public boolean check1;
	private boolean currentlySending;  // These field allows us to limit database writes by only sending data once we've received confirmation the previous data went through.
	/**
	 * Constructor for Drawing Surface. Creates the first screen.
	 */
	public DrawingSurface() {
		
//		keysDown = new ArrayList<Integer>();
//		robots = new ArrayList<Robot>();
//		
//		this.roomRef = roomRef;
//		currentlySending = false;
           check =false;
           check1 =false;
		screens = new ArrayList<Screen>();
		
		keys = new ArrayList<Integer>();
		
		
		FirstScreen screen1 = new FirstScreen(this);
		screens.add(screen1);
		if(check == true) {
			SecondScreen screen2 = new SecondScreen(this);
			screens.add(screen2);
			//switchScreen(ScreenSwitcher.GAME_SCREEN);
		}
		armorSelection = new LightArmor();
		weaponSelection = new Sword();
		abilitySelection = new Meteor();
		if(check1 == true) {
			ThirdScreen screen3 = new ThirdScreen(this);
			screens.add(screen3);
			//switchScreen(ScreenSwitcher.GAME_SCREEN);
		}
		
/**		
		SecondScreen screen2 = new SecondScreen(this);
		screens.add(screen2);
		
		ThirdScreen screen3 = new ThirdScreen(this);
		screens.add(screen3); */
		
		activeScreen = screens.get(0);
		armorSelection = new LightArmor();
		weaponSelection = new Sword();
		abilitySelection = new Meteor();
	}
	/**
	 * 
	 * @return Array list of screens that hold all the screens built.
	 */
	public ArrayList<Screen> getScreen(){
		return screens;
		
	}
	/**
	 * Checks if there is robot, if not removes the room
	 */
	 public void run()
     {
   	  if (robots.size() == 0)
				roomRef.removeValueAsync();
			else
				myUserRef.removeValueAsync();
     }
	 /**
	  * Sets up the screens to work
	  */
	public void setup() {
		for (Screen s : screens)
			s.setup();
	}
	/**
	 * 	Draws the screen and allows the user to scale it
	 */
	public void draw() {
		ratioX = (float)width/activeScreen.DRAWING_WIDTH;
		ratioY = (float)height/activeScreen.DRAWING_HEIGHT;

		push();
		
		scale(ratioX, ratioY);
		
		activeScreen.draw();
		
		pop();
	}
	/**
	 * Checks if the user pressed escape
	 */
	public void keyPressed() {
		keys.add(keyCode);
		if (key == ESC) { // This prevents a processing program from closing on escape key
			key = 0;}
	}
/**
 * Checks if the keyCode is released 
 */
	public void keyReleased() {
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}
/**
 * 
 * @param code the int value of the key pressed
 * @return true is key does contain the code, false otherwise
 */
	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}
	/**
	 * Presses the mouses in the current screen
	 */
	public void mousePressed() {
		activeScreen.mousePressed();
	}
	
	/**
	 * Moves the mouse in the current screen
	 */
	public void mouseMoved() {
		activeScreen.mouseMoved();
	}
	/**
	 * Drags the mouse in the current screen
	 */
	public void mouseDragged() {
		activeScreen.mouseDragged();
	}
	/**
	 * Releases the mouse in the current screen
	 */
	public void mouseReleased() {
		activeScreen.mouseReleased();
	}
	/**
	 * 
	 * @param assumed the assumed coordinate of a point
	 * @return a new point that hold the actual coordinates
	 */
	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}
/**
 * 
 * @param actual a point that holds the actual coordinates of the point
 * @return the assumed coordinate of the point
 */
	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}
	/**
	 * @param i gets the integer value that contains the information of which armor is selected
	 * Sets the armor to the one user selects
	 */
	public void getArmor(int i) {
		if (i == 0)
			armorSelection = new LightArmor();
		if (i == 1)
			armorSelection = new MediumArmor();
		if (i == 2)
			armorSelection = new HeavyArmor();
	}
	

	@Override
	/**
	 * @param i gets the int value of the screen to switch
	 * Switches the screen
	 */
	public void switchScreen(int i) {
		activeScreen = screens.get(i);
	}

	@Override 
	/**
	 * @param i gets the integer value that contains the information of which weapon is selected
	 * Sets the weapon to the one user selects
	 */
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
	/**
	 * @param i gets the integer value that contains the information of which ability is selected
	 * Sets the ability to the one user chooses
	 */
	public void getAbility(int i) {
		if (i == 0)
			abilitySelection = new Meteor();
		if (i == 1)
			abilitySelection = new Kamehameha();
	}
	/**
	 * Closes the current screen
	 */
	public void terminate() {
		activeScreen.terminate();
	}

}

