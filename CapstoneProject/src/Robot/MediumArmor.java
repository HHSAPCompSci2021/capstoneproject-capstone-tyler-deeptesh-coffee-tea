package Robot;

/** 
 * Subclass representing armor that is moderate
 * @author tylertamura
 * @author deepteshday
 */
public class MediumArmor extends Armor {
	private Armor arm;
	public double spdF = 0.8;
/**
 * This method sets the armor to medium armor
 */
public void setArmor() {
	super.armor= arm;
	this.speedReduction = 0.8;
}
public MediumArmor() {
//	arm = new MediumArmor();

}
public MediumArmor(Armor armor) {
	super(armor);
}
@Override
/**
 * @return the speed of medium armor
 */
public int getSpeed() {
	// TODO Auto-generated method stub
	return 5;
}
/**
 * @return the defense added by the medium armor
 */
@Override
public int getDefense() {
	// TODO Auto-generated method stub
	return 5;
}
}
