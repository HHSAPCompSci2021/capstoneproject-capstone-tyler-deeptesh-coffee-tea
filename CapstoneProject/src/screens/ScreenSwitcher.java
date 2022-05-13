package screens;

import Robot.*;

public interface ScreenSwitcher {
	public static final int MENU_SCREEN = 0;
	public static final int SELECT_SCREEN = 1;
	public static final int GAME_SCREEN = 2;
	
	
	
	public void switchScreen(int i);
	
	public void getArmor(int i);
	public void getWeapons(int i);
	public void getAbility(int i);
}
