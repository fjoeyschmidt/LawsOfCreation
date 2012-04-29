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
			if (c.isJumping()) {
				jumpCharacter(c);
				c.setyDirection(0);
			}
			int x = c.getCharacterLocation().getxLocation() + (c.getxDirection() * c.getSpeed());
			int y = c.getCharacterLocation().getyLocation() + (c.getyDirection() * c.getSpeed());
			Location z = new Location(x, y);
			z = Area.outOfBounds(area, z);
			c.setCharacterLocation(z);
		}
	}

	public static void jumpCharacter(Character c) {
		//jump up while character has not peaked jump
		if ( c.getJumpingLocation().getyLocation() < c.getCharacterLocation().getyLocation() + c.getProperties().getJumpHeight()
				&& c.getJumpDirection() != -1) {
			c.setJumpDirection(1);
		} else if (c.getJumpingLocation().getyLocation() > c.getCharacterLocation().getyLocation()) {
			//fall down
			c.setJumpDirection(-1);
		} else {
			c.setJumpDirection(0);
			c.setIsJumping(false);
		}
		if (c.getJumpDirection() == 0) {
			c.setJumpingLocation(c.getCharacterLocation());
		} else {
			int x = c.getCharacterLocation().getxLocation();
			int y = c.getJumpingLocation().getyLocation();
			y = y + (c.getJumpDirection() * 4);
			c.setJumpingLocation(new Location(x, y));
		}
	}
}
