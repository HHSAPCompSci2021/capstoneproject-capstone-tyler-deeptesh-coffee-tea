package screens;


import java.awt.Rectangle;

import java.awt.Shape;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.HashMap;

import javax.swing.SwingUtilities;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import Robot.LightArmor;
import Robot.Meteor;
import Robot.Robot;
import Robot.Sword;
import core.DrawingSurface;
import processing.core.*;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.*;
import com.google.firebase.database.*;

/** 
 * Subclass representing a the during battle screen
 * @author tylertamura
 * @author deepteshday
 */
public class ThirdScreen extends Screen {
	
	private DrawingSurface surface;
	
	private Rectangle ground;
	
	private Robot me;
<<<<<<< HEAD
	
	private double meX = 0;
	private double meY = 0;
	
	
	private Rectangle healthpart;
	
	private ArrayList<Robot> robots;
=======
	private Robot enemyRobot;
	//private Rectangle healthpart;
>>>>>>> ce8f33b6f2e66ecec4581a80e7318b28b74dc09f

	private DatabaseReference postsRef;
	private DatabaseReference myUserRef;

	public ThirdScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;
		ground = new Rectangle(-1000,500,DRAWING_WIDTH + 2000,1000);
<<<<<<< HEAD
		healthpart = new Rectangle(200,200,200,200);
				
		
		robots = new ArrayList<Robot>();
		
=======
	//	healthpart = new Rectangle(200,200,200,200);
>>>>>>> ce8f33b6f2e66ecec4581a80e7318b28b74dc09f
		
		FileInputStream refreshToken;
		try {
			
			refreshToken = new FileInputStream("RobotDeathArena.json");
			
			FirebaseOptions options = new FirebaseOptions.Builder()
				    .setCredentials(GoogleCredentials.fromStream(refreshToken))
				    .setDatabaseUrl("https://robot-death-arena-default-rtdb.firebaseio.com/")
				    .build();

				FirebaseApp.initializeApp(options);
				DatabaseReference database = FirebaseDatabase.getInstance().getReference();
				postsRef = database.child("posts");

				postsRef.addChildEventListener(new UserChangeListener());
				
				
				

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
		
<<<<<<< HEAD
		
		me = new Robot(myUserRef.getKey(), surface.weaponSelection, surface.armorSelection, surface.abilitySelection, 600, 100, image);
=======
		enemyRobot = new Robot(surface.enemyWeapon,surface.enemyArmor,surface.enemyAbility,200,100,image1);
		me = new Robot(surface.weaponSelection, surface.armorSelection, surface.abilitySelection, 600, 100, image);
>>>>>>> ce8f33b6f2e66ecec4581a80e7318b28b74dc09f
	}

	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {
		myUserRef = postsRef.child("users").push();
		spawnNewRobot();
		Map<String, Double> cord = new HashMap<>();
		cord.put("x", me.x);
		cord.put("y", me.y);
		myUserRef.setValueAsync(cord);
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
		
		// update database
		if (me.x != meX || me.y != meY) {
			myUserRef.removeValueAsync();
			Map<String, Double> cord = new HashMap<>();
			cord.put("x", me.x);
			cord.put("y", me.y);
			
			myUserRef.push().setValueAsync(cord);
			meX = me.x;
			meY = me.y;
		}

	}
	
	
	
	
	public class UserChangeListener implements ChildEventListener {

		private ConcurrentLinkedQueue<Runnable> tasks;
		
		public UserChangeListener() {  // This threading strategy will work with Processing programs. Just use this code inside your PApplet.
			tasks = new ConcurrentLinkedQueue<Runnable>();
			
//			ThirdScreen.this.registerMethod("post", this);
		}
		
		
		public void post() {
			while (!tasks.isEmpty()) {
				Runnable r = tasks.remove();
				r.run();
			}
		}
		
		@Override
		public void onCancelled(DatabaseError arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onChildAdded(DataSnapshot arg0, String arg1) {
			tasks.add(new Runnable() {

				@Override
				public void run() {
					if (me.idMatch(arg0.getKey())) {  // Don't react to our own data
						return;
					}
					
//					PlayerData data = arg0.getValue(PlayerData.class);
//					Player p = new Player(arg0.getKey(), data, DrawingSurface.this);
//					players.add(p);
				}
				
			});
		}

		@Override
		public void onChildChanged(DataSnapshot arg0, String arg1) {
			tasks.add(new Runnable() {

				@Override
				public void run() {
					if (me.idMatch(arg0.getKey()))
						return;
					
//					for (int i = 0; i < players.size(); i++) {
//						Player p = players.get(i);
//						if (p.idMatch(arg0.getKey())) {
//							PlayerData data = arg0.getValue(PlayerData.class);
//							p.syncWithDataObject(data);
//						}
//					}
				}
				
			});
			
		}

		@Override
		public void onChildMoved(DataSnapshot arg0, String arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onChildRemoved(DataSnapshot arg0) {
			tasks.add(new Runnable() {

				@Override
				public void run() {
					if (me.idMatch(arg0.getKey()))
						return;
					
//					for (int i = 0; i < players.size(); i++) {
//						if (players.get(i).idMatch(arg0.getKey())) {
//							players.remove(i);
//							break;
//						}
//					}
				}
				
			});
			
		}

	
}
}