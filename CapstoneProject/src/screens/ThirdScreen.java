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
import java.util.Iterator;

import javax.swing.SwingUtilities;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import Robot.Ability;
import Robot.Armor;
import Robot.Hammer;
import Robot.HeavyArmor;
import Robot.Kamehameha;
import Robot.LightArmor;
import Robot.MediumArmor;
import Robot.Meteor;
import Robot.Robot;
import Robot.Spear;
import Robot.Sword;
import Robot.Weapon;
import core.DrawingSurface;
import processing.core.*;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.*;
import com.google.firebase.database.*;
import com.google.firebase.database.DatabaseReference.CompletionListener;

/** 
 * Subclass representing a the during battle screen
 * @author tylertamura
 * @author deepteshday
 */
public class ThirdScreen extends Screen {
	
	private DrawingSurface surface;
	
	private Rectangle ground;
	
	private Robot me;

	
	private double meX = 0;
	private double meY = 0;
	private double meH = 0;
	private String sHP = "";
	
	private Rectangle healthpart;
	
	private ArrayList<Robot> robots;

	private Robot enemyRobot;

	private PImage image;

	private DatabaseReference postsRef;
	private DatabaseReference myUserRef;

	public ThirdScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;
		ground = new Rectangle(-1000,500,DRAWING_WIDTH + 2000,1000);

		healthpart = new Rectangle(200,200,200,200);
		//image = surface.loadImage("images/robot.png");
		//me = new Robot(myUserRef.getKey(), surface.weaponSelection, surface.armorSelection, surface.abilitySelection, 600, 100, image);
		robots = new ArrayList<Robot>();

		
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
		System.out.println(surface.weaponSelection);
	}
	
	public void actionPerformed(ActionEvent e) {
		
			postsRef.push().setValueAsync(e);
			//System.out.println("hi");
		
	}


	public void spawnNewRobot() {
		image = surface.loadImage("images/robot.png");
		//PImage image1 = surface.loadImage("images/robot.png");
		

		
	   me = new Robot(myUserRef.getKey(), surface.weaponSelection, surface.armorSelection, surface.abilitySelection, 600, 100, image);
		System.out.println(surface.armorSelection);
		//enemyRobot = new Robot(surface.enemyWeapon,surface.enemyArmor,surface.enemyAbility,200,100,image1);

	}

	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {
		
		myUserRef = postsRef.child("users").push();
		if(robots.size()<=1) {
		spawnNewRobot();
		Map<String, Integer> cord = new HashMap<>();
		cord.put("x", (int)me.x);
		cord.put("y", (int)me.y);
		cord.put("Health", me.Health);
		cord.put("Ability", me.getAbNum());
		cord.put("Armor", me.getArNum());
		cord.put("Weapon", me.getWeNum());
	
		myUserRef.setValueAsync(cord);
		
		}
	
	}
	

	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() {
	
//		sHP = "" + me.getHealth() + "/"+me.TotalHealth();
	
		


		surface.background(0,0,0);   
		surface.rect(200,50,20,20);
        surface.text("Health", 200, 30);
        surface.text(sHP, 200, 40);
        
		for (int i = 0; i < robots.size(); i++) {
			robots.get(i).draw(surface);
		}
        
		me.draw(surface);
        // healthpart.
		if(me.isDead()==true) {
			System.out.println("Game Over");
			System.exit(0);
		}
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
			try {
				for (int i = 0; i < robots.size(); i++) {
					if(me != robots.get(i)) {
						me.Attack(robots.get(i));
			}
					else
						continue;
			}
			}
			catch(NullPointerException e) {
				System.out.print("null");
			}
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
		if (me.x != meX || me.y != meY || me.Health != meH) {
			myUserRef.removeValueAsync();
			Map<String, Integer> cord = new HashMap<>();
			cord.put("x", (int)me.x);
			cord.put("y", (int)me.y);
			cord.put("Health", me.Health);	
			cord.put("Ability", me.getAbNum());
			cord.put("Armor", me.getArNum());
			cord.put("Weapon", me.getWeNum());
			myUserRef.push().setValueAsync(cord);
			meX = me.x;
			meY = me.y;
			meH = me.Health;
		}
		
		

	}
	
	public void terminate() {
		me.terminated =  true;
		myUserRef.removeValue(null);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public class UserChangeListener implements ChildEventListener {

		private ConcurrentLinkedQueue<Runnable> tasks;
		private Weapon weapon;
		private Armor armor;
		private Ability ability;
		
		
		public UserChangeListener() {  // This threading strategy will work with Processing programs. Just use this code inside your PApplet.
			tasks = new ConcurrentLinkedQueue<Runnable>();
			weapon = ThirdScreen.this.surface.weaponSelection;
			armor = ThirdScreen.this.surface.armorSelection;
			ability = ThirdScreen.this.surface.abilitySelection;
			ThirdScreen.this.surface.registerMethod("post", this);
			
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
					
					Iterator<DataSnapshot> it = arg0.getChildren().iterator();
					
					DataSnapshot a = null;
					while (it.hasNext()) {
						a = it.next();
						for (Robot rob : robots) {
							if (rob.idMatch(a.getKey())) {
								return;
							 }
						}
						if  (me.idMatch(a.getKey())) { 
						} else {
							Weapon weapon = null; Armor armor = null; Ability ability = null;
							HashMap<String, Object> cord = (HashMap<String, Object>) a.getValue();
							int x = 0,y = 0;
							for (String key: cord.keySet()) {
								if (cord.size() ==1 ) {
									HashMap<String, Long> cord2 = (HashMap<String,Long>) cord.get(key);
									x = cord2.get("x").intValue();
									y = cord2.get("y").intValue();
									
									int i = cord2.get("Weapon").intValue();
									if (i == 0)
										weapon = new Sword();
									if (i == 1)
										weapon = new Spear();
									if (i == 2) 
										weapon = new Hammer();
									i = cord2.get("Armor").intValue();
									if (i == 0)
										armor = new LightArmor();
									if (i == 1)
										armor = new MediumArmor();
									if (i == 2)
										armor = new HeavyArmor();
									i = cord2.get("Ability").intValue();
									if (i == 0)
										ability = new Meteor();
									if (i == 1)
										ability = new Kamehameha();	
									
							}
							
							
							Robot r = new Robot(a.getKey(), weapon, armor, ability, x, y, image);
							r.setHealth(0);
							robots.add(r);
						}
						}
						
							
						
					}
					

				
				
				}
			});
		}

		@Override
		public void onChildChanged(DataSnapshot arg0, String arg1) {
			tasks.add(new Runnable() {

				@Override
				public void run() {
					
					robots.clear();
					
					Iterator<DataSnapshot> it = arg0.getChildren().iterator();
										
					DataSnapshot a = null;
					while (it.hasNext()) {
						a = it.next();
						if  (me.idMatch(a.getKey())) { 
							
						} else {
							Weapon weapon = null; Armor armor = null; Ability ability = null;
							HashMap<String, Object> cord = (HashMap<String, Object>) a.getValue();
							int x = 0,y = 0,hp = 0;
							for (String key: cord.keySet()) {

								if (cord.size() ==1 ) {
									HashMap<String, Long> cord2 = (HashMap<String,Long>) cord.get(key);
									x = cord2.get("x").intValue();
									y = cord2.get("y").intValue();
									hp = cord2.get("Health").intValue();
									
									int i = cord2.get("Weapon").intValue();
									if (i == 0)
										weapon = new Sword();
									if (i == 1)
										weapon = new Spear();
									if (i == 2) 
										weapon = new Hammer();
									i = cord2.get("Armor").intValue();
									if (i == 0)
										armor = new LightArmor();
									if (i == 1)
										armor = new MediumArmor();
									if (i == 2)
										armor = new HeavyArmor();
									i = cord2.get("Ability").intValue();
									if (i == 0)
										ability = new Meteor();
									if (i == 1)
										ability = new Kamehameha();
							}
							
							Robot r = new Robot(a.getKey(), weapon, armor, ability, x, y, image);
							r.Health = hp;
							robots.add(r);
							
							
						}
						
					}
					
					
//					for (int i = 0; i < players.size(); i++) {
//						Player p = players.get(i);
//						if (p.idMatch(arg0.getKey())) {
//							PlayerData data = arg0.getValue(PlayerData.class);
//							p.syncWithDataObject(data);
//						}
//					}
				}
				}});
				
			
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
					
					for (int i = 0; i < robots.size(); i++) {
						if (robots.get(i).terminated) {
							robots.remove(i);
						}
					}
					
					
					Iterator<DataSnapshot> it = arg0.getChildren().iterator();
					
					DataSnapshot a = null;
					while (it.hasNext()) {
						a = it.next();
						
						HashMap<String, Object> cord = (HashMap<String, Object>) a.getValue();
						int x = 0,y = 0,hp = 0;
						for (String key: cord.keySet()) {

							if (cord.size() ==1 ) {
								HashMap<String, Long> cord2 = (HashMap<String,Long>) cord.get(key);
								x = cord2.get("x").intValue();
								y = cord2.get("y").intValue();
								hp = cord2.get("Health").intValue();
								
								
								if (x == -1000 || y == -1000) {
									a.getRef().removeValue((CompletionListener) a);
									postsRef.removeValue((CompletionListener) a);
								}

							}
						}
							
							
						
						
					}
				}
				
			});
			
		}

	
}
}