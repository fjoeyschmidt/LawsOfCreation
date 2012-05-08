package tloc.entities;

/** Structure class.
 * For permanent structures that take
 * up grid space.
 */

public class Structure {
	private Location structureLocation; //top left corner
	private int height, width;
	private Space structureSpace;
	private String SpriteSize, SpriteName;
	
	public Structure(Location loc, int h, int w, String sS, String sN) {
		structureLocation = loc;
		height = h;
		width = w;
		structureSpace = new Space(structureLocation, height, width);
		SpriteSize = sS;
	}
	
	//Getters and Setters
	public Location getStructureLocation() {
		return structureLocation;
	}

	public void setStructureLocation(Location structureLocation) {
		this.structureLocation = structureLocation;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Space getStructureSpace() {
		return structureSpace;
	}

	public void setStructureSpace(Space structureSpace) {
		this.structureSpace = structureSpace;
	}
	
	public String getSpriteSize() {
		return this.SpriteSize;
	}
	
	public String getSpriteName() {
		return this.SpriteName;
	}
}
