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
import java.time.LocalTime;
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
	int hour,min,sec;
	int Hour,Min,Sec;
	boolean canattack;
	boolean canability;
	private Rectangle healthpart;
	private boolean hasRoom = false;
	public long activetime;
	public long activetime1;
	
	private ArrayList<Robot> robots;
	private int[] rooms;

	private Robot enemyRobot;

	private PImage image;

	private DatabaseReference postsRef;
	private DatabaseReference myUserRef;
	private DatabaseReference enemyRef;
/**
 * Constructor for third screen and makes it so that multiplayer starts in this screen
 * @param surface  lets the third screen use PApplet 
 */
	public ThirdScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;
		ground = new Rectangle(-1000,500,DRAWING_WIDTH + 2000,1000);

		
		rooms = new int[100]; // For now we'll have the number of players capped at 200
		

		canattack =true;
		canability =true;
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
	/**
	 * Performs the action and makes it current action
	 * @param e gets the past action
	 */
	public void actionPerformed(ActionEvent e) {
		
			postsRef.push().setValueAsync(e);
			//System.out.println("hi");
		
	}

/**
 * Spawns a new robot with an image
 */
	public void spawnNewRobot() {
		image = surface.loadImage("images/robot.png");
		//PImage image1 = surface.loadImage("images/robot.png");
		

		
	   me = new Robot(myUserRef.getKey(), surface.weaponSelection, surface.armorSelection, surface.abilitySelection, 600, 100, image);
		System.out.println(surface.armorSelection);
		//enemyRobot = new Robot(surface.enemyWeapon,surface.enemyArmor,surface.enemyAbility,200,100,image1);

	}

	// The statements in the setup() function 
	// execute once when the program begins
	/**
	 * Sets up  the third screen and adds in robot in the correct location in the third screens
	 */
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
			cord.put("room" , -1);	me.room = -1;
			myUserRef.setValueAsync(cord);
		
		}
	
	}
	

	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	/**
	 * Draws the third screen maps, and implements the cooldown for ability, implements the room chooser part, implements the death part and implements attack and ability part	 */
	public void draw() {
	
//		sHP = "" + me.getHealth() + "/"+me.TotalHealth();
	
		

		
		surface.background(0,0,0);
		surface.rect(750, 50, -320 * me.Health / 150, 10);
		surface.fill(0, 255, 0);
		surface.rect(700, 100, 50, 200 * me.energyAmount() / 100);
		surface.fill(255, 0, 0, 0);
		surface.stroke(255, 0, 0);
		surface.rect(700, 100, 50, 200);
		surface.stroke(0, 0, 0);
		surface.fill(255, 255, 255);
		
//		surface.rect(200,50,20,20);
//        surface.text("Health", 200, 30);
//        surface.text(sHP, 200, 40);
        
		for (int i = 0; i < robots.size(); i++) {
			robots.get(i).draw(surface);
			surface.rect(50, 50, 320 * robots.get(i).Health / 150, 10);
			if (robots.get(i).isDead() && me.room != -1) {
				surface.textSize(70);
				surface.text("YOU WIN", 200, 200);
			}
		}
        
		me.draw(surface);
        // healthpart.
		if(me.isDead()==true) {
			surface.textSize(70);
			surface.text("GAME OVER", 200, 200);
		}
//		if (surface.isPressed(KeyEvent.VK_ESCAPE)) {
//			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
////			return;
//		}
		if (ground.intersects(me)) {
			me.onGround = true;
			me.moveToLocation(me.x, 239);
		} else { 
			me.onGround = false;
		}
		
		if (surface.isPressed(KeyEvent.VK_E)) {
			me.gatherEnergy();
			me.act();
			return;
		}
		
		if (surface.isPressed(KeyEvent.VK_W)&&me.onGround==true) {
			me.jump();
//			return;
		}
		if (surface.isPressed(KeyEvent.VK_A)) {
			if(me.x<10) {
				return;
			}
			else {
			me.left();
//			return;
		}
		}
		if (surface.isPressed(KeyEvent.VK_S)) {
			me.down();
//			return;
		}
		if (surface.isPressed(KeyEvent.VK_D)) {
			if(me.x>750) {
				return;
			}
			else {
			me.right();
			}
//			return;
		}

		if (surface.isPressed(KeyEvent.VK_C)) {
			System.out.println("1");
			if (me.energyAmount() == 100 && !me.isDead()) {
				System.out.println("2");
				for (int i = 0; i < robots.size(); i++) {
					System.out.println("3");
					if (enemyRef != null) {
						System.out.println("4");
//						robots.get(i).Health -= me.ability.AbilityDamage();
						
						enemyRef.removeValueAsync();
						Map<String, Integer> cord = new HashMap<>();
						cord.put("x", (int)robots.get(i).x);
						cord.put("y", (int)robots.get(i).y);
						cord.put("Health", robots.get(i).Health - me.ability.AbilityDamage());								
						cord.put("Ability", robots.get(i).getAbNum());
						cord.put("Armor", robots.get(i).getArNum());
							cord.put("Weapon", robots.get(i).getWeNum());
						cord.put("room", robots.get(i).room);
						enemyRef.push().setValueAsync(cord);
						
						me.Health -= me.ability.selfDamage;
						me.ability.energy = 0;
					}
				}
			}
		}
		me.act();
		if(surface.isPressed(KeyEvent.VK_SPACE)) {

			if(canattack == true ) {
//				surface.text("Attack", 100, 100);
				canattack = false;
				/**
				hour = LocalTime.now().getHour();
				min = LocalTime.now().getMinute();
				sec = LocalTime.now().getSecond();
				**/
				activetime = System.currentTimeMillis();
				for (int i = 0; i < robots.size(); i++) {

					if(me != robots.get(i)&&enemyRef != null) {
						System.out.println(enemyRef);
							me.Attack(robots.get(i));

					if(me != robots.get(i)) {
						System.out.println("f");
						if (enemyRef == null) {
							System.out.println("null");
						}
						if (me.Attack(robots.get(i)) && enemyRef != null) {

							System.out.println("attacked");
							enemyRef.removeValueAsync();
							Map<String, Integer> cord = new HashMap<>();
							cord.put("x", (int)robots.get(i).x);
							cord.put("y", (int)robots.get(i).y);
							cord.put("Health", robots.get(i).Health);								
							cord.put("Ability", robots.get(i).getAbNum());
							cord.put("Armor", robots.get(i).getArNum());
 							cord.put("Weapon", robots.get(i).getWeNum());
							cord.put("room", robots.get(i).room);
							enemyRef.push().setValueAsync(cord);
						}

			}
				}
					 if(Math.abs(System.currentTimeMillis()-activetime) >=2000) {
						canattack = true;
						//System.currentTimeMillis();
						System.out.println("canattack");

					}
				}
			}
			if(Math.abs(System.currentTimeMillis()-activetime) >=2000) {
				canattack = true;
				//System.currentTimeMillis();
				System.out.println("canattack");
			}
		} 
			
			
			
		
//		if(surface.isPressed(KeyEvent.VK_C)) {
//			if(canability ) {                
//				canability = false;
//				surface.text("Ability", 100, 100);
//				Hour = LocalTime.now().getHour();
//				Min = LocalTime.now().getMinute();
//				Sec = LocalTime.now().getSecond();
//				activetime1 = System.currentTimeMillis();
//				for (int i = 0; i < robots.size(); i++) {
//					if(me != robots.get(i)) {
//						System.out.println("hola");
//						me.Ability(robots.get(i));
//					}
//				}
//				if(Math.abs(System.currentTimeMillis()-activetime1) >=8000) {
//					canability=true;
//					System.out.println("canuseability");
//				}
//			}
//			if(surface.isPressed(KeyEvent.VK_H)) {
//				String str = "" + me.getHealth();
//				surface.text(str, 500, 100);
//			}
//		}

////		me.act();
//			
//
//			}
//		}}
		
		if (me.room == -1) {
			surface.textSize(70); 
			surface.text("Joining Room...", 100, 200);
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
			cord.put("room", me.room);
			myUserRef.push().setValueAsync(cord);
			meX = me.x;
			meY = me.y;
			meH = me.Health;
		}
	}
//			System.out.println(myUserRef);
		
		
		



	/**
	 * Removes the robot from the third screen and terminates it
	 */
	public void terminate() {
		me.terminated =  true;
		myUserRef.removeValue(null);
		try {
			Thread.sleep(3000);
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
		
		/**
		 * Constructor for user Change Listeners
		 */
		public UserChangeListener() {  // This threading strategy will work with Processing programs. Just use this code inside your PApplet.
			tasks = new ConcurrentLinkedQueue<Runnable>();
			weapon = ThirdScreen.this.surface.weaponSelection;
			armor = ThirdScreen.this.surface.armorSelection;
			ability = ThirdScreen.this.surface.abilitySelection;
			ThirdScreen.this.surface.registerMethod("post", this);
			
		}
		
		/**
		 * runs all the task 
		 */
		public void post() {
		
			
			while (!tasks.isEmpty()) {
				
				Runnable rT = tasks.remove();
				rT.run();
			}
		}
		
		@Override
		/**
		 * Cancels the database
		 */
		public void onCancelled(DatabaseError arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		/**
		 * Gets all the stuff from the data, assigns room numbers and makes new robots 
		 */
		public void onChildAdded(DataSnapshot arg0, String arg1) {
			tasks.add(new Runnable() {

				@Override
				public void run() {
					
					for (int i = 0; i < rooms.length; i ++) {
						rooms[i] = 0;
					}
					
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
							HashMap<String, Object> cord = (HashMap<String, Object>) a.getValue();
							for (String key: cord.keySet()) {
								if (cord.size() ==1 ) {
									HashMap<String, Long> cord2 = (HashMap<String,Long>) cord.get(key);
									int roomNum = cord2.get("room").intValue();
									if (roomNum != -1)
										rooms[roomNum]++;
								}
							}
							
						} else {
							Weapon weapon = ThirdScreen.this.surface.weaponSelection; Armor armor = ThirdScreen.this.surface.armorSelection; Ability ability = ThirdScreen.this.surface.abilitySelection;
							HashMap<String, Object> cord = (HashMap<String, Object>) a.getValue();
							int x = 0,y = 0,hp = 0;
							for (String key: cord.keySet()) {
								if (cord.size() ==1 ) {
									HashMap<String, Long> cord2 = (HashMap<String,Long>) cord.get(key);
									
									int roomNum = cord2.get("room").intValue();
									
									
									if (roomNum != -1)
										rooms[roomNum]++;
									
									
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
							
							
							enemyRef = a.getRef();
							Robot r = new Robot(a.getKey(), weapon, armor, ability, x, y, image);
							r.setHealth(0);
							r.Health = hp;
							robots.add(r);
						}
					}

						
							
						
					}
					

					if  (me.room == -1) { 
						for(int i = 0; i < rooms.length; i++) {
							if (rooms[i] < 2) {
								me.room = i;
//								// hasRoom = true;
								break;
							}
						}
						
					}
				
				}
			});
		}

		@Override
		/**
		 * Gets the previous robot data and copies it to a new robot in a location pressed by the user and removes the previous robot, it implements the changes
		 */
		public void onChildChanged(DataSnapshot arg0, String arg1) {
			tasks.add(new Runnable() {

				@Override
				public void run() {
					
					robots.clear();
					
					// Create arraylist
					// then update
					

					

					//rooms.clear();

					Iterator<DataSnapshot> it = arg0.getChildren().iterator();
										
					DataSnapshot a = null;
					while (it.hasNext()) {
						a = it.next();
						if  (me.idMatch(a.getKey())) { 
							HashMap<String, Object> cord = (HashMap<String, Object>) a.getValue();
							for (String key: cord.keySet()) {
								if (cord.size() ==1 ) {
									HashMap<String, Long> cord2 = (HashMap<String,Long>) cord.get(key);
									int h = cord2.get("Health").intValue();
									me.Health = h;
								}
							}
						} else {
							//Weapon weapon = null; Armor armor = null; Ability ability = null;
							HashMap<String, Object> cord = (HashMap<String, Object>) a.getValue();
							int x = 0,y = 0,hp = 0,roomNum = 0;
							boolean same = true;
							for (String key: cord.keySet()) {

								if (cord.size() ==1 ) {
									HashMap<String, Long> cord2 = (HashMap<String,Long>) cord.get(key);																		
										
									roomNum = cord2.get("room").intValue();
									if (roomNum != me.room) {
										same = false;
										break;
									}
									
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
								
							if (same) {
								enemyRef = a.getRef();
								Robot r = new Robot(a.getKey(), weapon, armor, ability, x, y, image);
								r.Health = hp;
								r.room = roomNum;
								robots.add(r);
							}
							
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
		/**
		 * Moves the robot
		 */
		public void onChildMoved(DataSnapshot arg0, String arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		/**
		 * Removes the robot from the database
		 */
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