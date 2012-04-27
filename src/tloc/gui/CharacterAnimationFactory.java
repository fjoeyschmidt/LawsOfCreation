package tloc.gui;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import tloc.entities.Character;

public class CharacterAnimationFactory {
	private static Map<String, Animation> stillLeftAnimationMap = new HashMap<String, Animation>();
	private static Map<String, Animation> stillRightAnimationMap = new HashMap<String, Animation>();
	private static Map<String, Animation> movingLeftAnimationMap = new HashMap<String, Animation>();
	private static Map<String, Animation> movingRightAnimationMap = new HashMap<String, Animation>();
	private static Map<String, Animation> jumpingLeftAnimationMap = new HashMap<String, Animation>();
	private static Map<String, Animation> jumpingRightAnimationMap = new HashMap<String, Animation>();
	
	//loads and returns the sprite

	public static Animation loadAnimation(Character c) throws SlickException {
		Animation a = null;
		if (c.isJumping()) {
			if (c.getFacingDirection() < 0) {
				a = getAnimation(c, jumpingLeftAnimationMap);
			}
			if (c.getFacingDirection() > 0) {
				a = getAnimation(c, jumpingRightAnimationMap);
			}
		}
		if (c.isMoving()) {
			if (c.getFacingDirection() < 0) {
				a = getAnimation(c, movingLeftAnimationMap);
			}
			if (c.getFacingDirection() > 0) {
				a = getAnimation(c, movingRightAnimationMap);
			}
		} else {
			if (c.getFacingDirection() < 0) {
				a = getAnimation(c, stillLeftAnimationMap);
			}
			if (c.getFacingDirection() > 0) {
				a = getAnimation(c, stillRightAnimationMap);
			}
		}
		if (a == null) {
			throw new SlickException("a is null");
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
		String resName = CharacterAnimationFactory.class.getPackage().getName().replace('.', '/') + "/res/" 
				+ c.getCharacterName() + "Sheet.png";;
		if ( c.isJumping() ) {
			SpriteSheet charSheet = new SpriteSheet(new Image(resName), 51, 61);
			Image[] charFrames = new Image[2];
			charFrames[0] = charSheet.getSubImage(0, 5);
			charFrames[1] = charSheet.getSubImage(0, 6);
			if (c.getFacingDirection() <= 0 ) {
				charFrames = getFlipped(c, charFrames);
			}
			charAnim = new Animation(charFrames, 10 * c.getProperties().getJumpHeight());
		}
		if( c.isMoving() ) {
			SpriteSheet charSheet = new SpriteSheet(new Image(resName), 51, 61);
			Image[] charFrames = new Image[4];
			charFrames[0] = charSheet.getSubImage(0, 2);
			charFrames[1] = charSheet.getSubImage(0, 1);
			charFrames[2] = charSheet.getSubImage(0, 3);
			charFrames[3] = charSheet.getSubImage(0, 1);
			if (c.getFacingDirection() <= 0) {
				charFrames = getFlipped(c, charFrames);
			}
			charAnim = new Animation(charFrames, 120);
		}
		if( !c.isMoving() ){
			SpriteSheet charSheet = new SpriteSheet(new Image(resName), 51, 61);
			Image[] charFrames = new Image[1];
			charFrames[0] = charSheet.getSubImage(0, 0);
			if (c.getFacingDirection() <= 0) {
				charFrames = getFlipped(c, charFrames);
			}
			charAnim = new Animation(charFrames, 10);
		}
		if(charAnim == null) {
			throw new SlickException("Animation is null");
		}
		return charAnim;
	}

	private static Image[] getFlipped(Character c, Image[] charFrames) {
		for (int i = 0; i < charFrames.length; i++) {
			charFrames[i] = charFrames[i].getFlippedCopy(true, false);
		}
		return charFrames;
	}	
}
