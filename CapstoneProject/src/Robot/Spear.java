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
		System.out.println("spear");
	}
	/**
	 * Sets the weapon used by user as weapon
	 */
	public void setWeapon() {
		super.weapon= weapon;
	}

	@Override
	public int getDamage() {
		// TODO Auto-generated method stub
		return 8;
	}

	@Override
	public int getSpeed() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public double getReload() {
		// TODO Auto-generated method stub
		return 0.5;
	}
}
