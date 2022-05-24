package Robot;

/** 
 * Subclass representing a sword weapon
 * @author tylertamura
 * @author deepteshday
 */
public class Sword extends Weapon{
	private int damage;
	private int defense;
	private int reload;
	private int speed;
	
	public Sword() {
		 
	}
	
	/**
	 * Sets the weapon used by user to be sword
	 */
	public void setWeapon() {
		
	}

	@Override
	/**
	 * @return the damage dealt by the sword
	 */
	public int getDamage() {
		// TODO Auto-generated method stub
		return 15;
	}

	@Override
	/**
	 * @return the speed of the sword
	 */
	public int getSpeed() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	/**
	 * @return the reload speed of sword
	 */
	public double getReload() {
		// TODO Auto-generated method stub
		return 0.75;
	}
}
