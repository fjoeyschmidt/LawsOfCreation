package tloc.entities;

import java.util.Queue;
import java.util.LinkedList;


/** A class containing static methods that handle
 * the movement of a character. Includes jumping.
 */

public class Movement {
	
	//move
	public static void moveCharacter(Character c, Area area) {
		if ( c.getCharacterLocation() != null ) {
			if ( c.getMoveQueue().isEmpty() ) {
				int x = c.getCharacterLocation().getxLocation() + (c.getxDirection() * c.getSpeed());
				int y = c.getCharacterLocation().getyLocation() + (c.getyDirection() * c.getSpeed());
				Location z = new Location(x, y);
				z = Area.outOfBounds(area, z);
				c.setCharacterLocation(z);
			} else {
				c.setCharacterLocation(c.getMoveQueue().remove());
			}
		}
	}

	//jump
	public static void jumpCharacter(Character c) {
		int x = c.getCharacterLocation().getxLocation();
		int y = c.getCharacterLocation().getxLocation();
		Queue<Location> moveQ = new LinkedList<Location>();
		//jump up
		for (int i = 0; i < c.getProperties().getJumpHeight(); i++) {
			x = x + (c.getxDirection() * c.getSpeed());
			y = y - i;
			moveQ = c.getMoveQueue();
			moveQ.add(new Location(x, y));
			c.setMoveQueue(moveQ);
			c.setIsJumping(true);
		}
		//fall down
		for (int i = 0; i < c.getProperties().getJumpHeight(); i++) {
			x = x + (c.getxDirection() * c.getSpeed());
			y = y + i;
			moveQ = c.getMoveQueue();
			moveQ.add(new Location(x, y));
			c.setMoveQueue(moveQ);
			
		}
		c.setIsJumping(false);
	}
}
