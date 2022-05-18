package screens;


import java.awt.Rectangle;

import java.awt.Shape;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import Robot.LightArmor;
import Robot.Meteor;
import Robot.Robot;
import Robot.Sword;
import core.DrawingSurface;
//import demo1.Post;
import processing.core.PImage;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.*;
import com.google.firebase.database.*;
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
	private Robot enemyRobot;
	//private Rectangle healthpart;

	private DatabaseReference postsRef;

	public ThirdScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;
		ground = new Rectangle(-1000,500,DRAWING_WIDTH + 2000,1000);
	//	healthpart = new Rectangle(200,200,200,200);
		
		FileInputStream refreshToken;
		try {
			
			refreshToken = new FileInputStream("RobotDeathArena.json");
			
			FirebaseOptions options = new FirebaseOptions.Builder()
				    .setCredentials(GoogleCredentials.fromStream(refreshToken))
				    .setDatabaseUrl("https://robotdeatharena-ea59b.firebaseio.com/")
				    .build();

				FirebaseApp.initializeApp(options);
				DatabaseReference database = FirebaseDatabase.getInstance().getReference();
				postsRef = database.child("posts");

				postsRef.addChildEventListener(new DatabaseChangeListener());
				
				
				

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
			postsRef.push().setValueAsync(e);
			System.out.println("hi");
		
	}


	public void spawnNewRobot() {
		PImage image = surface.loadImage("images/robot.png");
		PImage image1 = surface.loadImage("images/robot.png");
		
		enemyRobot = new Robot(surface.enemyWeapon,surface.enemyArmor,surface.enemyAbility,200,100,image1);
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
	String str= ""+ me.getHealth()+ "/"+me.TotalHealth();
	

		surface.background(0,0,0);   
		surface.rect(200,50,20,20);
        surface.text("Health", 200, 30);
        surface.text("str", 200, 40);
//		for (Sprite s : obstacles) {
//			s.draw(surface);
//		}
		me.draw(surface);
        // healthpart.
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
		if(surface.isPressed(KeyEvent.VK_SPACE)) {
			me.Attack(enemyRobot);
			surface.text("Attack", 100, 100);
		}
		if(surface.isPressed(KeyEvent.VK_C)) {
			me.Ability();
			surface.text("Ability", 100, 100);
		}
		me.act();
		if (ground.intersects(me)) {
			me.onGround = true;
			me.moveToLocation(me.x, 239);
		} else { 
			me.onGround = false;
		}
		
		// See if robot x/y are different and then update database
		

	}
	
	
	
	
	/**
	 * 
	 * Handles all changes to the database reference. Because Firebase uses a separate thread than most other processes we're using (both Swing and Processing),
	 * we need to have a strategy for ensuring that code is executed somewhere besides these methods.
	 * 
	 * @author john_shelby
	 *
	 */
	class DatabaseChangeListener implements ChildEventListener {


		@Override
		public void onCancelled(DatabaseError arg0) {
			// TODO Auto-generated method stub

		}


		@Override
		public void onChildAdded(DataSnapshot dataSnapshot, String arg1) {
			
			SwingUtilities.invokeLater(new Runnable() {  // This threading strategy will work with Swing programs. Just put whatever code you want inside of one of these "runnable" wrappers.

				@Override
				public void run() {
					
					// This is where we receive the data
					
//					Post post = dataSnapshot.getValue(Post.class);
//
//					String text = output.getText();
//					text += post + "\n";
//
//					output.setText(text);
					
				}
				
			});
			
			
		}


		@Override
		public void onChildChanged(DataSnapshot arg0, String arg1) {
			// TODO Auto-generated method stub

		}


		@Override
		public void onChildMoved(DataSnapshot arg0, String arg1) {
			// TODO Auto-generated method stub

		}


		@Override
		public void onChildRemoved(DataSnapshot arg0) {
			// TODO Auto-generated method stub

		}
		
	}

	
}