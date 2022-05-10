package Robot;

/** 
 * Subclass representing armor that is moderate
 * @author tylertamura
 * @author deepteshday
 */
public class MediumArmor extends Armor {
	private Armor arm;
/**
 * This method sets the armor to medium armor
 */
public void setArmor() {
	super.armor= arm;
}
public MediumArmor() {
	arm = new MediumArmor();
}
public MediumArmor(Armor armor) {
	super(armor);
}
}
