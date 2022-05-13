package Robot;

import java.awt.geom.Rectangle2D;

import core.DrawingSurface;
import processing.core.*;
/**
 * Class representing a robot
 * @author tylertamura
 * @author deepteshday
 *
 */

public class Robot extends Sprite {
	
	private int Health;
	private double speed;
	private int Damage;
	private double reload;
	private Weapon weapon;
	private Armor armor;
	private Ability ability;
	
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
public Robot(Weapon weapon , Armor armor , Ability ability, int x, int y, PImage img) {
	super(img, x, y, ROBOT_WIDTH, ROBOT_HEIGHT);
//	super(x, y, ROBOT_WIDTH, ROBOT_HEIGHT);
	this.weapon= weapon;
	this.armor = armor;
	this.ability = ability;
	Health = 150;
	speed = 10;
	Damage = 20;
 	reload = 5;
 	System.out.println("r");
}

public void act() {
	
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
public void left() {                   // We'll have to insert timers
	super.moveByAmount(-10, 0);

}
public void right() {
	super.moveByAmount(10, 0);
}
public void jump() {
	yV = -20;
}
public void down() {
	super.moveToLocation(super.x, 239);
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
public void setHealth() {
	Health = Health + armor.getDefense();
}
public Ability getAbility() {
	return ability.ability;
}
public Armor getArmor() {
	return armor.armor;
}
public Weapon getWeapon() {
	return weapon.weapon;
}
public int getSpeed() {
	return (int) speed;
}
public int getHealth() {
	return Health;
}
public int getDamage() {
	return Damage;
}
public double getReload() {
	return reload;
}
public boolean intersect(Rectangle2D other) {
	if(this.intersect(other)) {
		return true;
	}
	else {
	return false;
	}
}
}
