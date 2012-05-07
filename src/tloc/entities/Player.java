 package tloc.entities;

import java.util.ArrayList;
import java.util.List;

/** Player class.
 * Contains methods for unique abilities
 * of the Player.
 */

public class Player extends Character {
	
	private static final int startingHealth = 20;
	private static final int startingDamage = 1;
	private static final int startingDefense = 1;
	private static final int startingSpeed = 2;
	private static final int startingJumpHeight = 100;
	private static final String SpriteSize = "51x61";
	private List<Ability> abilities = new ArrayList<Ability>();
	private int exp, expToLvl; //total experience points and number of points needed to lvl up
	
	public Player() {
		super("Ridley", startingHealth, startingDamage, startingDefense, startingSpeed, startingJumpHeight, SpriteSize);
		setLevel(1);
		setExp(0);
		setExpToLvl(10);
		setWeapon(new Weapon("Starter", 5, 15));
	}
	
	//sprint method
	public void sprint() {
		
	}
	
	//use item method
	public void useItem() {
		
	}
	
	//Getters and Setters
	public List<Ability> getAbilities() {
		return abilities;
	}

	public void setAbilities(List<Ability> abilities) {
		this.abilities = abilities;
	}

	public static String getSpritesize() {
		return SpriteSize;
	}
	
	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getExpToLvl() {
		return expToLvl;
	}

	public void setExpToLvl(int expToLvl) {
		this.expToLvl = expToLvl;
	}
	
}
