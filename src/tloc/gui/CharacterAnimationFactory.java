package tloc.gui;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import tloc.entities.Character;

public class CharacterAnimationFactory {
	private static Map<String, Animation> stillAnimationMap = new HashMap<String, Animation>();
	private static Map<String, Animation> movingAnimationMap = new HashMap<String, Animation>();
	
	//loads and returns the sprite

	public static Animation loadAnimation(Character c) throws SlickException {
		Animation a;
		if (c.isMoving()) {
			a = getAnimation(c, movingAnimationMap);
		} else {
			a = getAnimation(c, stillAnimationMap);
		}
		return a;
	}

	private static Animation getAnimation(Character c, Map<String, Animation> map)
			throws SlickException {
		Animation a;
		a = map.get(c.getCharacterName());
		if (a == null) {
			a = _loadAnimation(c);
			map.put(c.getCharacterName(), a);
		}
		return a;
	}
	
	private static Animation _loadAnimation(Character c) throws SlickException {
		Animation charAnim = null;
		String resName;
		if( c.isMoving() ) {
			resName = CharacterAnimationFactory.class.getPackage().getName().replace('.', '/') + "/res/" 
					+ c.getCharacterName() + "MoveSheet.png";
			SpriteSheet charSheet = new SpriteSheet(new Image(resName), 51, 61);
			Image[] charFrames = new Image[4];
			charFrames[0] = charSheet.getSubImage(1, 0);
			charFrames[1] = charSheet.getSubImage(0, 1);
			charFrames[2] = charSheet.getSubImage(1, 0);
			charFrames[3] = charSheet.getSubImage(1, 1);
			charAnim = new Animation(charFrames, 1);
		}
		if( !c.isMoving() ){
			resName = CharacterAnimationFactory.class.getPackage().getName().replace('.', '/') + "/res/" 
					+ c.getCharacterName() + "MoveSheet.png";
			SpriteSheet charSheet = new SpriteSheet(new Image(resName), 51, 61);
			Image[] charFrames = new Image[1];
			charFrames[0] = charSheet.getSubImage(0, 0);
			charAnim = new Animation(charFrames, 10);
		}
		if(charAnim == null) {
			throw new SlickException("Animation is null");
		}
		return charAnim;
	}	
}
