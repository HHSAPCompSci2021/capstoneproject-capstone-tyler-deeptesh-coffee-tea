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
		System.out.println("hammer");
	}
	
	@Override
	/**
	 * This method selects Hammer as the weapon chosen by the user
	 */
	public void setWeapon() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getDamage() {
		// TODO Auto-generated method stub
		return 13;
	}
	@Override
	public int getSpeed() {
		// TODO Auto-generated method stub
		return 1;
	}
	@Override
	public double getReload() {
		// TODO Auto-generated method stub
		return 1;
	}

	
}
