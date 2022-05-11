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
private int speed;
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
	return speed;
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
}
