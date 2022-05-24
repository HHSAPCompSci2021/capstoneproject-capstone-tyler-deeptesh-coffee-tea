package Robot;

/** 
 * Subclass representing Kamehameha(AKA Fireball) ability
 * @author tylertamura
 * @author deepteshday
 */
public class Kamehameha extends Ability{
	private int damage;
public Kamehameha() {

}
/**
 * This method sets kamehameha as the ability used by the user during battle 
 */
public void setAbility() {
	super.ability = new Kamehameha();
}
/**
 * @return the damage dealt by kamehameha
 */
public int AbilityDamage() {
	return 30;
}
@Override
/**
 * @return the reload speed of the ability
 */
public int getReload() {
	// TODO Auto-generated method stub
	return 10;
}
}
