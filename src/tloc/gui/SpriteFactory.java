package tloc.gui;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import tloc.entities.IDisplayable;
import tloc.entities.SpriteId;

/**
 * This class should go to gui resources and fetch sprites
 */
public class SpriteFactory {
	
	//takes an entity and gets an entity based on its name
	public static Image getSprite(IDisplayable displayable) {
		try {
			return loadSprite(displayable.getSpriteId());
		} catch (SlickException e) {
			throw new IllegalStateException("Invalid Image: ", e);
		}
	}
	
	//loads and returns the sprite
	private static Image loadSprite(SpriteId spriteId) throws SlickException {
		String resName = SpriteFactory.class.getPackage().getName().replace('.', '/') + "/res/" + spriteId.toString() + ".png";
		try {
			return new Image(resName);
		} catch (SlickException e) {
			throw new SlickException ("Invalid sprite resource", e);
		}
	}
}
