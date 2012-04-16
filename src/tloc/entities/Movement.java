package tloc.entities;

/** A class containing static methods that handle
 * the movement of a character. Includes jumping.
 */

public class Movement {
	//move
	public static void moveCharacter(Character c, Area area) {
		if ( c.getCharacterLocation() != null ) {
			int x = c.getCharacterLocation().getxLocation() + (c.getxDirection() * c.getSpeed());
			int y = c.getCharacterLocation().getyLocation() + (c.getyDirection() * c.getSpeed());
			Location z = new Location(x, y);
			z = Area.outOfBounds(area, z);
			c.setCharacterLocation(z);
		}
	}

	//jump
	public static void jumpCharacter(Character c) {
		//jump up
		for (int i = 0; i < c.getProperties().getJumpHeight(); i++) {
			int x = c.getCharacterLocation().getxLocation() + (c.getxDirection() * c.getSpeed());
			int y = c.getCharacterLocation().getyLocation() + 1;
			c.setCharacterLocation(new Location(x, y));
			c.setIsJumping(true);
		}
		//fall down
		for (int i = c.getProperties().getJumpHeight(); i > 0; i--) {
			int x = c.getCharacterLocation().getxLocation() + (c.getxDirection() * c.getSpeed());
			int y = c.getCharacterLocation().getyLocation() - 1;
			c.setCharacterLocation(new Location(x, y));
		}
		c.setIsJumping(false);
	}
}
