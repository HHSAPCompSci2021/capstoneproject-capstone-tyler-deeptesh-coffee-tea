package Robot;

/** 
 * Super class for all weapons
 * @author tylertamura
 * @author deepteshday
 */
public abstract class Weapon {
	private int damage;
	private double speed;
	protected Weapon weapon;
	/**
	 * Default constructor for weapon that intilizes fields
	 */
	public Weapon(Weapon weapon) {
		this.weapon= weapon;
	}
	public Weapon() {
		
	}
	/**
	 * sets the weapon to the correct subclass
	 */
	public abstract void setWeapon();
	/**
	 * Returns the damage of the weapon chosen by the user
	 * @return damage of weapon 
	 */
	public abstract int getDamage();
	/**
	 * Returns the speed of the weapon chosen by the user
	 * @return the speed of the weapon
	 */
	public abstract int getSpeed();
	/**
	 * Returns the reload speed of the weapon chosen by the user
	 * @return the reload speed of the weapon
	 */
	public abstract double getReload();
}
