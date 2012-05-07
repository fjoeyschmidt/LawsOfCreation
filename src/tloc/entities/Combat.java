package tloc.entities;

import java.util.List;


/** Class with static methods to handle combat */
public class Combat {

	public static void attack(Character c, List<Character> entities) {
		Space attackGrid;
		
		attackGrid = getAttackSpace(c);
		
		checkHit(c, attackGrid, entities);
	}

	private static void checkHit(Character c, Space attackGrid, List<Character> entities) {
		
		//check to see if any entities in gamestate are within the attackGrid
		for (Character check : entities) {
			if ( !(check == c) ) {
				int damage = c.getWeapon().getWeaponDamage() + c.getDamage() - check.getDefense();
				//true for weapon hit damage character
				if ( Space.checkOverlap(attackGrid, Space.getCharacterSpace(check)) ) {
					if ( damage < 0 ) {
						damage = 0;
					}
					check.setCurrentHealth(check.getCurrentHealth() - damage);
					if(check.getCurrentHealth() <= 0) {
						check.setDead(true);
					}
				}
			}
		}
	}
	
	public static void block(Character c) {
		
	}

	public static Space getAttackSpace(Character c) {
		Space attackGrid;
		//if character is facing left find the Space to the left
		if(c.getFacingDirection() < 0) {
			attackGrid = new Space(new Location(c.getCharacterLocation().getxLocation() - c.getWeapon().getRange(), c.getCharacterLocation().getyLocation()),
					c.getProperties().getHeight(), c.getWeapon().getRange());
		} else {
			//space to the right
			attackGrid = new Space(new Location(c.getCharacterLocation().getxLocation() + c.getProperties().getWidth(), c.getCharacterLocation().getyLocation()), 
					c.getProperties().getHeight(), c.getWeapon().getRange());
		}
		return attackGrid;
	}

}
