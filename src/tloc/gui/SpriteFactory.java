package tloc.gui;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import tloc.entities.IDisplayable;
import tloc.entities.SpriteId;
import tloc.entities.Structure;

/**
 * This class should go to gui resources and fetch sprites
 */
public class SpriteFactory {
	
	private static Integer spriteWidth;
	private static Integer spriteHeight;
	private static String[] spriteDimensions = new String[2]; 
	
	//takes an entity and gets an entity from the sprite map
	public static Image getSprite(IDisplayable displayable) throws SlickException {
		SpriteId spriteId = displayable.getSpriteId();
		String resName = SpriteFactory.class.getPackage().getName().replace('.', '/') + "/res/" + spriteId.toString() + ".png";
		try {
			return new Image(resName);
		} catch (SlickException e) {
			throw new SlickException ("Invalid sprite resource", e);
		}
	}
	
//	public static Image getStructureSprite(Structure s) throws SlickException {
//		spriteDimensions = s.getSpriteSize().split("x");
//		spriteWidth = new Integer(spriteDimensions[0]);
//		spriteHeight = new Integer(spriteDimensions[1]);
//		String resName = SpriteFactory.class.getPackage().getName().replace('.', '/') + "/res/" 
//				+ s.getSpriteName() + "Sheet" + s.getSpriteSize() + ".png";
//		SpriteSheet strucSheet = new SpriteSheet(new Image(resName), spriteWidth, spriteHeight);
//		Image strucImage = strucSheet.getSubImage(0, 0);
//		
//		
//		return strucImage;
//	}
}
