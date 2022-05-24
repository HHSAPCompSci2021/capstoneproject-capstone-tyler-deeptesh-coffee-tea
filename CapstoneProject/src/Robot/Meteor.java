package Robot;

/** 
 * Subclass representing meteor ability
 * @author tylertamura
 * @author deepteshday
 */
public class Meteor extends Ability {
private int damage;
/**
 * Constructor for a meteor class
 */
	public Meteor() {
		this.energyCap = 100;
	}
	public void e() {
		this.energy += 1;
	}
	@Override
	/**
	 * Set meteor as the ability used by user
	 */
	public void setAbility() {
		// TODO Auto-generated method stub
		super.ability = new Meteor();
	}
	@Override
	/**
	 * @return the damage dealt by meteor
	 */
	public int AbilityDamage() {
		// TODO Auto-generated method stub
		return 25;
	}
	@Override
	/**
	 * @return the reload speed of the meteor ability
	 */
	public int getReload() {
		// TODO Auto-generated method stub
		return 8;
	}

}
