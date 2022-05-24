package Robot;

/** 
 * Subclass representing a hammer weapon
 * @author tylertamura
 * @author deepteshday
 */
public class Hammer extends Weapon {
	private int damage;
	private int defense;
	private int reload;
	private int speed;
	
	public Hammer() {
		this.speedReduction = 0.6;
	}
	
	@Override
	/**
	 * This method selects Hammer as the weapon chosen by the user
	 */
	public void setWeapon() {
		// TODO Auto-generated method stub
		
	}
	@Override
	/**
	 * @return damage done by Hammer
	 */
	public int getDamage() {
		// TODO Auto-generated method stub
		return 25;
	}
	@Override
	/**
	 * @return speed changed by hammer
	 */
	public int getSpeed() {
		// TODO Auto-generated method stub
		return 1;
	}
	@Override
	/**
	 * @return reload speed of the hammer
	 */
	public double getReload() {
		// TODO Auto-generated method stub
		return 1;
	}

	
}
