package tloc.entities;


/** Class with static methods to handle combat */
public class Combat {

	public static void attack(Character c) {
		Space attackGrid;
		if(c.getFacingDirection() < 0) {
			attackGrid = new Space(new Location(c.getCharacterLocation().getxLocation() - c.getProperties().getWidth(), c.getCharacterLocation().getyLocation()),
					c.getProperties().getHeight(), c.getWeapon().getRange());
		}
	}

	public static void block(Character c) {
		
	}

}
