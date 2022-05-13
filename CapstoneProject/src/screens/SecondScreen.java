package screens;



import java.awt.Point;
import java.awt.Rectangle;

import core.DrawingSurface;
import processing.core.*;

/** 
 * Subclass representing a the pre battle screen
 * @author tylertamura
 * @author deepteshday
 */
public class SecondScreen extends Screen {

	private DrawingSurface surface;
	
	private Rectangle button;
	private Rectangle lightArmor;
	private Rectangle mediumArmor;
	private Rectangle heavyArmor;
	private Rectangle sword;
	private Rectangle spear;
	private Rectangle hammer;
	private Rectangle meteor;
	private Rectangle kamehameha;
	
	private PImage photo;
	public String armor;
	
	public int selectedWeapon = -1;
	public int selectedArmor = -1;
	public int selectedAbility = -1;
	public boolean canFight = false;

	public SecondScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;


//		button = new Rectangle(800/2-100,600/2-50,200,100);
		button = new Rectangle(800/2-100,450,200,100);
		lightArmor = new Rectangle(300,100,150,75);
		mediumArmor = new Rectangle(455,100,150,75);
		heavyArmor = new Rectangle(610,100,150,75);
		sword = new Rectangle(300,200,150,75);
		spear = new Rectangle(455,200,150,75);
		hammer = new Rectangle(610,200,150,75);
		meteor = new Rectangle(378,300,150,75);
		kamehameha = new Rectangle(533,300,150,75);
		
	}
	
	public void setup() {
		photo = surface.loadImage("images/SecondLoadingScreen.jpg");
	}


	public void draw() {

		surface.image(photo, 0, 0, 900, 600);
		
		if (canFight) {
			surface.rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
			surface.fill(0);
			String str = "FIGHT!";
			float w = surface.textWidth(str);
			surface.text(str, button.x+button.width/2-w/2, button.y+button.height/2);
		}
		
		// Armor
		
		surface.fill(255);
		if (selectedArmor == 0)
			surface.fill(255, 255, 0);
		surface.rect(lightArmor.x, lightArmor.y, lightArmor.width, lightArmor.height, 10, 10, 10, 10);
		surface.fill(0);
		String str1 = "LIGHT ARMOR";
		float w1 = surface.textWidth(str1);
		surface.text(str1, lightArmor.x+lightArmor.width/2-w1/2, lightArmor.y+lightArmor.height/2);
		
		surface.fill(255);
		if (selectedArmor == 1)
			surface.fill(255, 255, 0);
		surface.rect(mediumArmor.x, mediumArmor.y, mediumArmor.width, mediumArmor.height, 10, 10, 10, 10);
		surface.fill(0);
		String str2 = "MEDIUM ARMOR";
		float w2 = surface.textWidth(str2);
		surface.text(str2, mediumArmor.x+mediumArmor.width/2-w2/2, mediumArmor.y+mediumArmor.height/2);
		
		surface.fill(255);
		if (selectedArmor == 2)
			surface.fill(255, 255, 0);
		surface.rect(heavyArmor.x, heavyArmor.y, heavyArmor.width, heavyArmor.height, 10, 10, 10, 10);
		surface.fill(0);
		String str3 = "HEAVY ARMOR";
		float w3 = surface.textWidth(str3);
		surface.text(str3, heavyArmor.x+heavyArmor.width/2-w3/2, heavyArmor.y+heavyArmor.height/2);
		
		// Weapons
		
		surface.fill(255);
		if (selectedWeapon == 0)
			surface.fill(0, 255, 0);
		surface.rect(sword.x, sword.y, sword.width, sword.height, 10, 10, 10, 10);
		surface.fill(0);
		String str4 = "SWORD";
		float w4 = surface.textWidth(str4);
		surface.text(str4, sword.x+sword.width/2-w4/2, sword.y+sword.height/2);
		
		surface.fill(255);
		if (selectedWeapon == 1)
			surface.fill(0, 255, 0);
		surface.rect(spear.x, spear.y, spear.width, spear.height, 10, 10, 10, 10);
		surface.fill(0);
		String str5 = "SPEAR";
		float w5 = surface.textWidth(str5);
		surface.text(str5, spear.x+spear.width/2-w5/2, spear.y+spear.height/2);
		
		surface.fill(255);
		if (selectedWeapon == 2)
			surface.fill(0, 255, 0);
		surface.rect(hammer.x, hammer.y, hammer.width, hammer.height, 10, 10, 10, 10);
		surface.fill(0);
		String str6 = "HAMMER";
		float w6 = surface.textWidth(str6);
		surface.text(str6, hammer.x+hammer.width/2-w6/2, hammer.y+hammer.height/2);
		
		// Abilities
		
		surface.fill(255);
		if (selectedAbility == 0)
			surface.fill(255, 0, 0);
		surface.rect(meteor.x, meteor.y, meteor.width, meteor.height, 10, 10, 10, 10);
		surface.fill(0);
		String str7 = "METEOR";
		float w7 = surface.textWidth(str7);
		surface.text(str7, meteor.x+meteor.width/2-w7/2, meteor.y+meteor.height/2);
		
		surface.fill(255);
		if (selectedAbility == 1)
			surface.fill(255, 0, 0);
		surface.rect(kamehameha.x, kamehameha.y, kamehameha.width, kamehameha.height, 10, 10, 10, 10);
		surface.fill(0);
		String str8 = "KAMEHAMEHA";
		float w8 = surface.textWidth(str8);
		surface.text(str8, kamehameha.x+kamehameha.width/2-w8/2, kamehameha.y+kamehameha.height/2);
		
//		surface.rect(button.x, button.y, button.width, button.height, 30, 30, 10, 10);
//		surface.fill(255);
//		String str1 = "Weapon";
//		float w1 = surface.textWidth(str1);
//		surface.text(str1, button.x+button.width/2-w1/2, button.y+button.height/2);
//		
//		surface.rect(button.x, button.y, button.width, button.height, 50, 50, 10, 10);
//		surface.fill(255);
//		String str2 = "Ability";
//		float w2 = surface.textWidth(str2);
//		surface.text(str2, button.x+button.width/2-w2/2, button.y+button.height/2);
		
	}



	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (button.contains(p))
			surface.switchScreen(ScreenSwitcher.GAME_SCREEN);
		
		if (lightArmor.contains(p)) {
			surface.getArmor(0);
			selectedArmor = 0;
		}
		if (mediumArmor.contains(p)) {
			surface.getArmor(1);
			selectedArmor = 1;
		}
		if (heavyArmor.contains(p)) {
			surface.getArmor(2);
			selectedArmor = 2;
		}
		
		if (sword.contains(p)) {
			surface.getWeapons(0);
			selectedWeapon = 0;
		}
		if (spear.contains(p)) {
			surface.getWeapons(1);
			selectedWeapon = 1;
		}
		if (hammer.contains(p)) {
			surface.getWeapons(2);
			selectedWeapon = 2;
		}
		
		if (meteor.contains(p)) {
			surface.getWeapons(0);
			selectedAbility = 0;
		}
		if (kamehameha.contains(p)) {
			surface.getWeapons(1);
			selectedAbility = 1;
		}
		if (!canFight && selectedWeapon >= 0 && selectedAbility >= 0 && selectedArmor >= 0 ) {
			canFight = true;
		}
	}
	

}
