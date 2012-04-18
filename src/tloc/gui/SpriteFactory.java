package tloc.gui;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import tloc.entities.IDisplayable;
import tloc.entities.SpriteId;

/**
 * This class should go to gui resources and fetch sprites
 */
public class SpriteFactory {
	
	//takes an entity and gets an entity based on its name
	public static Animation getSprite(IDisplayable displayable) {
		try {
			return loadSprite(displayable.getSpriteId());
		} catch (SlickException e) {
			throw new IllegalStateException("Invalid Image: ", e);
		}
	}
	
	//loads and returns the sprite
	private static Animation loadSprite(SpriteId spriteId) throws SlickException {
		Animation ridley = null;
		String resName = null;
//		if(spriteId != SpriteId.PLAYER) {
//			resName = SpriteFactory.class.getPackage().getName().replace('.', '/') + "/res/" + spriteId.toString() + ".png";
//		}
		if(spriteId == SpriteId.PLAYER) {
			SpriteSheet ridleySheet = new SpriteSheet((SpriteFactory.class.getPackage().getName().replace('.', '/') + "/res/" + 
				"ridleySheet51by61.png"), 51, 61);
			ridley = new Animation()
		}
		try {
			if(spriteId == SpriteId.PLAYER) {
				return ridley;
			}
			else {
				return new Image(resName);
			}
			
		} catch (SlickException e) {
			throw new SlickException ("Invalid sprite resource", e);
		}
	}
}
