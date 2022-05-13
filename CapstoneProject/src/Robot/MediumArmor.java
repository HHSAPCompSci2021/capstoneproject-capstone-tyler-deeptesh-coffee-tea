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
//	arm = new MediumArmor();
	System.out.println("m");
}
public MediumArmor(Armor armor) {
	super(armor);
}
@Override
public int getSpeed() {
	// TODO Auto-generated method stub
	return 5;
}
@Override
public int getDefense() {
	// TODO Auto-generated method stub
	return 30;
}
}
