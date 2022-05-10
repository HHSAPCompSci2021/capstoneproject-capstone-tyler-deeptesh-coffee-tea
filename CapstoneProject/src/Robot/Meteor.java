package Robot;

/** 
 * Subclass representing meteor ability
 * @author tylertamura
 * @author deepteshday
 */
public class Meteor extends Ability {
private int damage;
	@Override
	/**
	 * Set meteor as the ability used by user
	 */
	public void setAbility() {
		// TODO Auto-generated method stub
		super.ability = new Meteor();
	}

}
