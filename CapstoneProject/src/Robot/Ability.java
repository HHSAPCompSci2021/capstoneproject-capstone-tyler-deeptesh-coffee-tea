package Robot;

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
	private int energy;
	private int energyCap;
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
	public boolean AbilityReady() {
		return false;
	}
	/**
	 * Adds energy to finish the ability usage cooldown
	 */
	public void addEnergy() {
		
	}
}