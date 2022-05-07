package Robot;

/** 
 * Super class for all weapons
 * @author tylertamura
 * @author deepteshday
 */
public abstract class Weapon {
	private int damage;
	private double speed;
	/**
	 * Default constructor for weapon that intilizes fields
	 */
	public Weapon() {
		
	}
	public abstract void setWeapon();
}
