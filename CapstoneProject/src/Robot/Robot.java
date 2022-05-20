package Robot;

import java.awt.geom.Rectangle2D;

import core.DrawingSurface;
import processing.core.*;
import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/**
 * Class representing a robot
 * @author tylertamura
 * @author deepteshday
 *
 */

public class Robot extends Sprite {
	
	public int Health;
	private double speed;
	private int Damage;
	private double reload;
	private Weapon weapon;
	private Armor armor;
	private Ability ability;
	private boolean canAttack;
	private boolean canUseAbility;
	public boolean terminated = false;
	private String uniqueID;
	int hour,mins,sec;
	int HOURS,MINS,SECS;
	
	public double xV = 0;
	public double yV = 0;
	public boolean onGround = false;
	
	private static DrawingSurface surface;
//	private static PImage image = surface.loadImage("images/robot.png");
	

	public static final int ROBOT_WIDTH = 81;
	public static final int ROBOT_HEIGHT = 261;

/**
 * Constructor that initializes all the fields
 * @param weapon
 * @param armor
 * @param ability
 */
public Robot(String uniqueID, Weapon weapon , Armor armor , Ability ability, int x, int y, PImage img) {
	super(img, x, y, ROBOT_WIDTH, ROBOT_HEIGHT);
	this.uniqueID = uniqueID;
//	super(x, y, ROBOT_WIDTH, ROBOT_HEIGHT);
	this.weapon= weapon;
	this.armor = armor;
	this.ability = ability;
	Health = 150;
	speed = 10;
	Damage = 20;
 	reload = 5;
 	canAttack = true;
 	canUseAbility = true;
 	
}
/**
 * The act method that sets how the act runs because of its infinite calling.
 */
public void act() {
	
	if (!terminated) {
	if (xV > 3)
		xV = 3;
	if (xV < -3)
		xV = -3;
	
	super.moveByAmount(xV, 5 + yV);
	
	if (xV > 0)
		xV -= 0.3;
	if (xV < 0)
		xV += 0.3;
	if (yV < 0)
		yV += 0.8;
	}
}
/**
 * Moves the robot to the left
 */
public void left() {                   // We'll have to insert timers
	super.moveByAmount(-10, 0);

}
/**
 * Moves the robot to the right
 */
public void right() {
	super.moveByAmount(10, 0);
}
/**
 * Moves the robot upwards
 */
public void jump() {
	yV = -20;
}
/**
 * Moves the robot downward
 */
public void down() {
	super.moveToLocation(super.x, 239);
}

public boolean idMatch(String uid) {
	return this.uniqueID.equals(uid);
}

public void syncWithDataObject(int x, int y) {
	
}



/**
 * Adds up the total damage the user should have
 */
public void setDamage() {
	Damage = Damage+ weapon.getDamage();
}
/**
 * Sets the final reload the user should have
 */
public void setReload() {
	reload = reload + weapon.getReload();
}
/**
 * Sets the final speed the user should have
 */
public void setSpeed() {
	speed = speed + weapon.getSpeed()+armor.getSpeed();
}
/**
 * Sets the final health the user should have
 */
public void setHealth(int LostHp) {
	Health = Health + armor.getDefense() - LostHp;
}
/**
 * 
 * @return the ability that the robot has
 */
public Ability getAbility() {
	return ability.ability;
}
/**
 * 
 * @return the armor that the robot has
 */
public Armor getArmor() {
	return armor.armor;
}
/**
 * 
 * @return the weapon that the robot uses
 */
public Weapon getWeapon() {
	return weapon.weapon;
}
/**
 * 
 * @return speed of the robot 
 */
public int getSpeed() {
	return (int) speed;
}
/**
 * 
 * @return Health of the robot
 */
public int getHealth() {
	return Health;
}
/**
 * 
 * @return damage the robot deals
 */
public int getDamage() {
	return Damage;
}
/**
 * 
 * @return returns the reload time of weapon
 */
public double getReload() {
	return reload;
}
/**
 * Checks if the reload time matches the difference between previous attack time and current attack time and 
 * set true if it can and false otherwise.
 */
public void SetcanAttack() {
if(LocalTime.now().getHour()>=hour&& LocalTime.now().getMinute()>=mins && Math.abs(LocalTime.now().getSecond()-sec)>=2) {
	canAttack = true;
}

}
/**
 * 
 * @param other gets the other robot that is battling the user
 * @return true if the user's robot is attacking and touches the other robot returns false otherwise.
 */
public boolean intersect(Robot other) {
	if(this.intersect(other)) {
		return true;
	}
	else {
	return false;
	}
}
/**
 * Attacks if the user can attack and deal damage if yes
 * @param other the other robot that is battling the user
 */
public void Attack(Robot other) {
	if(intersects(other)==true && canAttack==true) {
		setHealth(weapon.getDamage());
		hour = LocalTime.now().getHour();
		mins = LocalTime.now().getMinute();
		sec = LocalTime.now().getSecond();
		canAttack = false;
	}
}

public void Ability() {
if(canUseAbility==true) {
	setHealth(ability.AbilityDamage());
	HOURS = LocalTime.now().getHour();
	MINS = LocalTime.now().getMinute();
	SECS = LocalTime.now().getSecond();
}

}
public void CanUseAbility() {
	if(LocalTime.now().getHour()>=HOURS&& LocalTime.now().getMinute()>=MINS && Math.abs(LocalTime.now().getSecond()-SECS)>=ability.getReload()) {
		canUseAbility = true;
	}

}
public int TotalHealth() {
	return 150 + armor.getDefense();
}
public Object getDataObject() {
	// TODO Auto-generated method stub
	return null;
}
public boolean isDead() {
	if(Health<=0) {
		return true;
	}
	else
		return false;
}



}



