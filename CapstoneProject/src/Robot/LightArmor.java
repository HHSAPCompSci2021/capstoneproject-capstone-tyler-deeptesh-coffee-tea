package Robot;

/** 
 * Subclass representing armor that is light
 * @author tylertamura
 * @author deepteshday
 */
public class LightArmor extends Armor {
	private Armor arm;
	
public LightArmor() {
	arm = new LightArmor();
}
public LightArmor(Armor armor) {
	super(armor);
}
/**
 * Sets the armor to light armor and sets this to true
 */
public void setArmor() {
	super.armor = arm;
}
}
