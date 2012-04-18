package tloc.entities;

/** Abstract superclass for areas that
 * the player will visit.
 */

public abstract class Area implements IDisplayable {
	private Location grid[][];
	private static int height, width;
	
	public Area(int height, int width) {
		grid = new Location[width][height];
		Area.height = height;
		Area.width = width;
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				grid[i][j] = new Location(i,j);
			}
		}
	}
	
	//make sure character does not move out of bounds
	public static Location outOfBounds(Area area, Location loc) {
		if (loc.getxLocation() > width) {
			loc.setxLocation(width);
		}
		if (loc.getxLocation() < 0) {
			loc.setxLocation(0);
		}
		if (loc.getyLocation() > height) {
			loc.setyLocation(height);
		}
		if (loc.getyLocation() < 0) {
			loc.setyLocation(0);
		}
		return loc;
	}
	
	//Getters and Setters
	public Location[][] getGrid() {
		return grid;
	}

	public void setGrid(Location grid[][]) {
		this.grid = grid;
	}
}