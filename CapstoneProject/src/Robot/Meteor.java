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
		System.out.println("M");
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
	public int AbilityDamage() {
		// TODO Auto-generated method stub
		return 25;
	}
	@Override
	public int getReload() {
		// TODO Auto-generated method stub
		return 8;
	}

}
