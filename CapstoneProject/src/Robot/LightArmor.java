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
	this.speedReduction = 1.0;

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
/**
 * @return the speed affected by the light armor
 */
public int getSpeed() {
	// TODO Auto-generated method stub
	return 8;
}
@Override
/**
 * @return the defense of light armor
 */
public int getDefense() {
	// TODO Auto-generated method stub
	return 20;
}
}
