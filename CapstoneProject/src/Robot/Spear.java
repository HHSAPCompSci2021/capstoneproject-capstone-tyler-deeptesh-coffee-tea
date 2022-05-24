package Robot;

/** 
 * Subclass representing a spear weapon
 * @author tylertamura
 * @author deepteshday
 */
public class Spear extends Weapon {
	private int damage;
	private int defense;
	private int reload;
	private int speed;
	private Weapon weapon;
	public Spear(Weapon weapon) {
		this.weapon=weapon;
	}
	public Spear() {
//		weapon = new Spear();

	}
	/**
	 * Sets the weapon used by user as weapon
	 */
	public void setWeapon() {
		super.weapon= weapon;
	}

	@Override
	/**
	 * @return the damage of the spear
	 */
	public int getDamage() {
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	/**
	 * @return the speed of the user affected by the spear
	 */
	public int getSpeed() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	/**
	 * @return the reload speed of the spear
	 */
	public double getReload() {
		// TODO Auto-generated method stub
		return 0.5;
	}
}
