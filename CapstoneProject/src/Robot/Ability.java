package Robot;
import java.time.LocalTime;
/** 
 * Super class for all abilities
 * @author tylertamura
 * @author deepteshdey
 */
public abstract class Ability {
	private int damage;
	private int defense;
	private int reload;
	private int speed;
	public int energy;
	public int energyCap;
	public int selfDamage;
	protected Ability ability;
	/**
	 * Default constructor for ability to initialize fields
	 */
	public Ability() {
		
	}
	/**
	 * Chooses the ability that is ultimetly selected by the user
	 */
	public abstract void setAbility();
	/**
	 * Checks if ability is available to used by the user
	 * @return true if ability is ready
	 */
	
	public abstract int AbilityDamage();
	/**
	 * 
	 * @return the reload speed of the ability
	 */
	public abstract int getReload();
	/**
	 * Accessor method
	 * @return ability that is selected by the user
	 */
	public Ability getAbility() {
		return ability;
	}
	
	public void e() {
		energy += 1;
	}
}