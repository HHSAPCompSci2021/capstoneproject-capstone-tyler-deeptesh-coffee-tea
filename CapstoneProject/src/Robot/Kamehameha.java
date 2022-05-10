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

}
