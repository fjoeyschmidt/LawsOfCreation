package tloc.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** Space class.
 * Represents any space on the Area grid that
 * is occupied by a game entity.
 * Methods to compare spaces, check for overlap,
 * and detect collision.
 */

public class Space {
	protected Location topLeft, bottomRight;
	
	public Space(Location loc, int height, int width) {
		int topX, topY;
		topLeft = loc;
		topX = topLeft.getxLocation() + width;
		topY = topLeft.getyLocation() + height;
		setBottomRight(new Location(topX, topY));
	}
	
	public static Space getCharacterSpace(Character c) {
		Space charSpace = new Space(c.getCharacterLocation(), c.getProperties().getHeight(), 
				c.getProperties().getWidth());
		return charSpace;
	}
	
	//check two Spaces to see if they're the same
	public boolean sameSpace(Space compare) {
		if (Location.sameLocation(compare.getTopLeft(), this.topLeft) &&
				Location.sameLocation(compare.getBottomRight(), this.getBottomRight())) {
			return true;
		} else {
			return false;
		}
	}
	
	//check two Spaces to see if they overlap
	public static boolean checkOverlap(Space a, Space b) {
		List<Location> space1 = perimeter(a);
		List<Location> space2 = perimeter(b);
		Iterator<Location> iter1 = space1.iterator();
		Iterator<Location> iter2;
		Location compare;
	
		while (iter1.hasNext()) {
			iter2 = space2.iterator();
			compare = iter1.next();
			while (iter2.hasNext()) {
				if (Location.sameLocation(compare, iter2.next())) {
					return true;
				}
			}
		}
	
		return false;
	}
	
	/** Detect collision.
	 * Stops movement of Character in
	 * direction of collision;
	 */
	public static boolean checkCollision(Space character, Space check) {
		if ( checkOverlap(character, check) ) {
			return true;
		}
		return false;
	}
	
	//return a list of locations on the perimeter of space
	private static List<Location> perimeter(Space s) {
		int width = s.bottomRight.getxLocation() - s.topLeft.getxLocation();
		int height = s.bottomRight.getyLocation() - s.topLeft.getyLocation();
		List<Location> list = new ArrayList<Location>();
		Location loc;
		
		for (int i = 0; i < width; i++) {
			loc = new Location(s.topLeft.getxLocation() + i, s.topLeft.getyLocation());
			list.add(loc);
			loc = new Location(s.topLeft.getxLocation() + i, s.topLeft.getyLocation() + height);
			list.add(loc);
		}
		for (int i = 0; i < height; i++) {
			loc = new Location(s.topLeft.getxLocation(), s.topLeft.getyLocation() + i);
			list.add(loc);
			loc = new Location(s.topLeft.getxLocation() + width, s.topLeft.getyLocation() + i);
			list.add(loc);
		}
		
		return list;
	}

	//Getters and Setters
	public Location getTopLeft() {
		return topLeft;
	}

	public void setTopLeft(Location topLeft) {
		this.topLeft = topLeft;
	}

	public Location getBottomRight() {
		return bottomRight;
	}

	public void setBottomRight(Location bottomRight) {
		this.bottomRight = bottomRight;
	}
}
