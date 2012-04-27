package tloc.entities;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;


/** A class containing static methods that handle
 * the movement of a character. Includes jumping.
 */

public class Movement {
	
	//move
	public static void moveCharacter(Character c, Area area) {
		if ( c.getCharacterLocation() != null ) {
			if ( c.getMoveQueue().isEmpty() ) {
				c.setIsJumping(false);
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
		if (!c.isJumping()){
			int x = c.getCharacterLocation().getxLocation();
			int y = c.getCharacterLocation().getyLocation();
			Queue<Location> moveQ = new LinkedList<Location>();
			Stack<Integer> yLocations = new Stack<Integer>();
			//jump up
			for (int i = 0; i < c.getProperties().getJumpHeight(); i++) {
				yLocations.add(y);
				x = x + (c.getxDirection() * c.getSpeed());
				y = y + (c.getProperties().getJumpHeight()/5);
				moveQ.add(new Location(x, y));
			}
			//fall down
			while (!yLocations.empty()) {
				x = x + (c.getxDirection() * c.getSpeed());
				moveQ.add(new Location(x, yLocations.pop()));
			}
			c.setMoveQueue(moveQ);
		}
	}
}
