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
	public int getDamage() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public int getSpeed() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public double getReload() {
		// TODO Auto-generated method stub
		return 0.75;
	}
}
