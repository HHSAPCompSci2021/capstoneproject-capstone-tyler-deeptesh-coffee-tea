package Robot;

/** 
 * Super class for all armors
 * @author tylertamura
 * @author deepteshday
 */
public abstract class Armor {
	private int hpIncrease;
	private double speedReduction;
	/**
	 * Default Constructor for armor that initilalizes fields
	 */
public Armor() {
	
}
/**
 * Allows the user to select a specific type of armor
 */
public abstract void setArmor();

}

