package tloc.gui;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import tloc.entities.IDisplayable;
import tloc.entities.SpriteId;

/**
 * This class should go to gui resources and fetch sprites
 */
public class SpriteFactory {
	
	//takes an entity and gets an entity from the sprite map
	public static Image getSprite(IDisplayable displayable) throws SlickException {
		SpriteId spriteId = displayable.getSpriteId();
		String resName = SpriteFactory.class.getPackage().getName().replace('.', '/') + "/res/" + spriteId.toString() + ".png";
		try {
			return new Image(resName);
		} catch (SlickException e) {
			throw new IllegalStateException("Invalid sprite resource", e);
		}
	}
}