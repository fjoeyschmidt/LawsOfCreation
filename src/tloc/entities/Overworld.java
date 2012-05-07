package tloc.entities;

/** Class for top-down Overworld Areas.
 */
public class Overworld extends Area implements IDisplayable {
	
	public Overworld(int height, int width) {
		super(height, width);
	}
	
	public SpriteId getSpriteId() {
		return SpriteId.OVERWORLD;
	}
}
