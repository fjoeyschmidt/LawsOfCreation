package tloc.entities;

/** Enemy class.
 * Methods for combat.
 */

public class Enemy extends Character {
	
	public Enemy(String name, int health, int dam, int def, int spd, int h, int w, int jH) {
		super(name, health, dam, def, spd, h, w, jH);
		setWeapon(new Weapon("Standard", 5, 5));
	}
	
	//attack player
	public void attackPlayer() {
		
	}
	
	//avoid player
	public void avoidPlayer() {
		
	}
}
