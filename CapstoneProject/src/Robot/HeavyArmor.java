package Robot;

/** 
 * Subclass representing armor that is heavy
 * @author tylertamura
 * @author deepteshday
 */
public class HeavyArmor extends Armor {
	private Armor arm;
public HeavyArmor(Armor armor) {
		super(armor);
		arm = armor;
	}
public HeavyArmor() {
	 arm = new HeavyArmor();
}

/**
 * Set HeavyArmor as the armor used by the user
 */
public void setArmor() {
	super.armor = arm;
}
}
