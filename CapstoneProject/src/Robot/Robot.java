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

public Robot(Weapon weapon , Armor armor , Ability ability) {
 this.weapon= weapon;
 this.armor = armor;
 this.ability = ability;
 Health = 150;
 speed = 10;
 Damage = 20;
 reload = 5;
}
public void setDamage() {
	
}
public void setReload() {
	
}
public void setSpeed() {
	
}
public void setHealth() {
	
}

}
