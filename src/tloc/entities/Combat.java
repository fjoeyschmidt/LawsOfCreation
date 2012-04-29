package tloc.entities;

import java.util.Iterator;
import java.util.List;


/** Class with static methods to handle combat */
public class Combat {
	
	private static GameState game;
	public Combat(GameState g) {
		game = g;
	}

	public static void attack(Character c) {
		Space attackGrid;
		List<Character> entities = game.getEntityList();
		//if character is facing left find the Space to the left
		if(c.getFacingDirection() < 0) {
			attackGrid = new Space(new Location(c.getCharacterLocation().getxLocation() - c.getWeapon().getRange(), c.getCharacterLocation().getyLocation()),
					c.getProperties().getHeight(), c.getWeapon().getRange());
		} else {
			//space to the right
			attackGrid = new Space(new Location(c.getCharacterLocation().getxLocation() + c.getProperties().getWidth(), c.getCharacterLocation().getyLocation()), 
					c.getProperties().getHeight(), c.getWeapon().getRange());
		}
		
		//check to see if any entities in gamestate are within the attackGrid
		Iterator<Character> charIter = entities.iterator();
		while (charIter.hasNext()) {
			Character check = charIter.next();
			if ( !(check == c) ) {
				//true for weapon hit damage character
				if ( Space.checkOverlap(attackGrid, Space.getCharacterSpace(check)) ) {
					check.setCurrentHealth(check.getCurrentHealth() - c.getWeapon().getWeaponDamage());
				}
			}
		}
	}

	public static void block(Character c) {
		
	}

}
