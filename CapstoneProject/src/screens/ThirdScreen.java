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

import Robot.LightArmor;
import Robot.Meteor;
import Robot.Robot;
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
	}
	
	public void actionPerformed(ActionEvent e) {
		
			postsRef.push().setValueAsync(e);
			//System.out.println("hi");
		
	}


	public void spawnNewRobot() {
		image = surface.loadImage("images/robot.png");
		//PImage image1 = surface.loadImage("images/robot.png");
		

		
		me = new Robot(myUserRef.getKey(), surface.weaponSelection, surface.armorSelection, surface.abilitySelection, 600, 100, image);
		
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
			myUserRef.push().setValueAsync(cord);
			meX = me.x;
			meY = me.y;
			meH = me.Health;
		}
		
		

	}
	
	public void terminate() {
		me.terminated =  true;
		myUserRef.removeValueAsync();
		Map<String, Integer> cord = new HashMap<>();
		cord.put("x", -1000);
		cord.put("y", -1000);	
		cord.put("Health", me.Health);
		myUserRef.push().setValueAsync(cord);
	}
	
	
	
	
	public class UserChangeListener implements ChildEventListener {

		private ConcurrentLinkedQueue<Runnable> tasks;
		
		
		public UserChangeListener() {  // This threading strategy will work with Processing programs. Just use this code inside your PApplet.
			tasks = new ConcurrentLinkedQueue<Runnable>();
			
			
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
//						System.out.println("d");
//						System.out.println(a.getKey());
//						if (me.idMatch(arg0.getKey())) {  // Don't react to our own data
//							System.out.print(true);
//							System.out.println("run");
						for (Robot rob : robots) {
							if (rob.idMatch(a.getKey())) {
//								System.out.println("other");
								return;
							 }
						}
//						System.out.println("check2");
						if  (me.idMatch(a.getKey())) { 
//							System.out.println("me2");
						} else {
//							System.out.println("new");
							HashMap<String, Object> cord = (HashMap<String, Object>) a.getValue();
							int x = 0,y = 0;
//							System.out.println(cord.toString());
							for (String key: cord.keySet()) {
//								System.out.println("key=" + key + " value=" + cord.get(key));
								if (cord.size() ==1 ) {
									HashMap<String, Long> cord2 = (HashMap<String,Long>) cord.get(key);
									x = cord2.get("x").intValue();
									y = cord2.get("y").intValue();

								}
							}
							
							// the  weapons/armor/abitlies are not right, im just testing
							
							Robot r = new Robot(a.getKey(), surface.weaponSelection, surface.armorSelection, surface.abilitySelection, x, y, image);
							robots.add(r);
						}
						}
						
							
						
					}
					
					
//					@SuppressWarnings("unchecked")
//					Map<String, Double> cord = (Map<String, Double>) arg0.getValue();
//					
//					int x = (int)Math.round(cord.get("x"));
//					int y = (int)Math.round(cord.get("y"));
//					
					// the  weapons/armor/abitlies are not right, im just testing
//					Robot r = new Robot(arg0.getKey(), surface.weaponSelection, surface.armorSelection, surface.abilitySelection, x, y, image);
//					robots.add(r);
					

				
				
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
							HashMap<String, Object> cord = (HashMap<String, Object>) a.getValue();
							int x = 0,y = 0,hp = 0;
							for (String key: cord.keySet()) {

								if (cord.size() ==1 ) {
									HashMap<String, Long> cord2 = (HashMap<String,Long>) cord.get(key);
									x = cord2.get("x").intValue();
									y = cord2.get("y").intValue();
									hp = cord2.get("Health").intValue();

								}
							}
							
							Robot r = new Robot(a.getKey(), surface.weaponSelection, surface.armorSelection, surface.abilitySelection, x, y, image);
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
					System.out.println("testing");
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
									System.out.print("-1000");
									a.getRef().removeValueAsync();
//									a.getRef().removeValue(null);
//									postsRef.removeValue((CompletionListener) a);
								}

							}
						}
							
							
						
						
					}
				}
				
			});
			
		}

	
}
}