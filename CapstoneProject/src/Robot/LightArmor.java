package Robot;

/** 
 * Subclass representing armor that is light
 * @author tylertamura
 * @author deepteshday
 */
public class LightArmor extends Armor {
	private Armor arm;
	
public LightArmor() {
//	arm = new LightArmor();
	System.out.println("l");
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
@Override
public int getSpeed() {
	// TODO Auto-generated method stub
	return 8;
}
@Override
public int getDefense() {
	// TODO Auto-generated method stub
	return 20;
}
}
