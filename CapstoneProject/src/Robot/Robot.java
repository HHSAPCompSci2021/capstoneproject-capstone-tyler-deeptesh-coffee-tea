package Robot;

import processing.core.PImage;
/**
 * Class representing a robot
 * @author tylertamura
 * @author deepteshday
 *
 */
public class Robot {
private int Health;
private double speed;
private int Damage;
private double reload;
private Weapon weapon;
private Armor armor;
private Ability ability;
private PImage image;
/**
 * Constructor that initializes all the fields
 * @param weapon
 * @param armor
 * @param ability
 */
public Robot(Weapon weapon , Armor armor , Ability ability) {
 this.weapon= weapon;
 this.armor = armor;
 this.ability = ability;
 Health = 150;
 speed = 10;
 Damage = 20;
 reload = 5;
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

}
