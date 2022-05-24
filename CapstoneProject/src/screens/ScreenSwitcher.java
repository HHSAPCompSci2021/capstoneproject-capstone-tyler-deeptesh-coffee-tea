package screens;

import Robot.*;
/**
 * Class that lets the screem switch
 * @author Deeptesh Dey , Tyler Tamura
 *
 */
public interface ScreenSwitcher {
	public static final int MENU_SCREEN = 0;
	public static final int SELECT_SCREEN = 1;
	public static final int GAME_SCREEN = 2;
	
	
	/**
	 * Method that switches the screen
	 * @param i the integer value of the screen , the user wants to switch
	 */
	public void switchScreen(int i);
	/**
	 *  Chooses the Armor selected by the user and sets it
	 * @param i the integer value for the armor that is chosen
	 */
	public void getArmor(int i);
	/**
	 * Chooses the weapon selected by the user and sets it
	 * @param i the integer value for the weapons that is chosen by the user
	 */
	public void getWeapons(int i);
	/**
	 * Chooses the Ability selected by the user sets it
	 * @param i the integer value for the ability that is chosen by the user
	 */
	public void getAbility(int i);
}
