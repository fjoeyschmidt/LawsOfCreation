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
	
	//maps a spriteid to an image
	private static final Map<SpriteId, Image> spriteMap = new HashMap<>();
	static {
		try {
			for (SpriteId spriteId : SpriteId.values()) {
				spriteMap.put(spriteId, loadSprite(spriteId));
			}
		} catch (SlickException e) {
			throw new IllegalStateException("Error loading sprite resources", e);
		}
	}
	
	//takes an entity and gets an entity from the sprite map
	public static Image getSprite(IDisplayable displayable) {
		return spriteMap.get(displayable);
	}
	
	//loads and returns the sprite
	private static Image loadSprite(SpriteId spriteId) throws SlickException {
		String resName = SpriteFactory.class.getPackage().getName().replace('.', '/') + "/res/" + spriteId.toString() + ".png";
		System.out.println(resName);
		try {
			return new Image(resName);
		} catch (SlickException e) {
			throw new IllegalStateException("Invalid sprite resource", e);
		}
	}
}
