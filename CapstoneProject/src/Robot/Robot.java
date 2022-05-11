package Robot;

import processing.core.PImage;
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
	private PImage image;

	public static final int ROBOT_WIDTH = 200;
	public static final int ROBOT_HEIGHT = 400;
/**
 * Constructor that initializes all the fields
 * @param weapon
 * @param armor
 * @param ability
 */
public Robot(Weapon weapon , Armor armor , Ability ability, int x, int y) {
	super(img, x, y, ROBOT_WIDTH, ROBOT_HEIGHT);
	this.weapon= weapon;
	this.armor = armor;
	this.ability = ability;
	Health = 150;
	speed = 10;
	Damage = 20;
 	reload = 5;
}

public void act() {
	System.out.println("i");
}
public void left() {
	System.out.println("a");
}
public void right() {
	System.out.println("d");
}
public void jump() {
	System.out.println("w");
}
public void down() {
	System.out.println("s");
}





/**
 * Adds up the total damage the user should have
 */
public void setDamage() {
	
}
/**
 * Sets the final reload the user should have
 */
public void setReload() {
	
}
/**
 * Sets the final speed the user should have
 */
public void setSpeed() {
	
}
/**
 * Sets the final health the user should have
 */
public void setHealth() {
	
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
}
