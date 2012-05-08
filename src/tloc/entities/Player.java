 package tloc.entities;

import java.util.ArrayList;
import java.util.List;


/** Player class.
 * Contains methods for unique abilities
 * of the Player.
 */

public class Player extends Character implements LevelUp {
	
	private static final int startingHealth = 50;
	private static final int startingDamage = 1;
	private static final int startingDefense = 1;
	private static final int startingSpeed = 2;
	private static final int startingJumpHeight = 100;
	private static final String SpriteSize = "51x61";
	private static final int startingLevel = 1;
	private int exp, expToLvl; //total experience points and number of points needed to lvl up

	
	public Player() {
		super("Ridley", startingHealth, startingDamage, startingDefense, startingSpeed,
				startingJumpHeight, SpriteSize, startingLevel);
		setLevel(1);
		setExp(0);
		setExpToLvl(10);
		setWeapon(new Weapon("Starter", 5, 15));
	}

	@Override
	public void addExp(int exper) {
		setExp(getExp() + exper);
	}

	@Override
	public boolean checkLvlUp() {
		if (exp >= expToLvl) {
			return true;
		}
		return false;
	}

	@Override
	public void levelUp(LevelUpEnum statUp) {
		
		if (statUp == LevelUpEnum.HEALTH) {
			this.getProperties().setMaxHealth(this.getProperties().getMaxHealth() + 5);
		}
		if (statUp == LevelUpEnum.DAMAGE) {
			this.setDamage(this.getDamage() + this.getLevel());
		}
		if (statUp == LevelUpEnum.DEFENSE) {
			this.setDefense(this.getDefense() + this.getLevel());
		}
		if (statUp == LevelUpEnum.SPEED) {
			this.setSpeed(this.getSpeed() + 1);
		}
		this.setCurrentHealth(this.getProperties().getMaxHealth());
	}
	
	//Getters and Setters
	

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
