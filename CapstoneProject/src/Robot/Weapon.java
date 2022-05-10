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
	public abstract void setWeapon();
	public abstract double getDamage();
	public abstract int getSpeed();
	public abstract double getReload();
}
