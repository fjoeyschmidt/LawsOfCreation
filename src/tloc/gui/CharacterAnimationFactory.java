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
	private static Map<String, Animation> jumpingUpLeftAnimationMap = new HashMap<String, Animation>();
	private static Map<String, Animation> jumpingUpRightAnimationMap = new HashMap<String, Animation>();
	private static Map<String, Animation> jumpingDownLeftAnimationMap = new HashMap<String, Animation>();
	private static Map<String, Animation> jumpingDownRightAnimationMap = new HashMap<String, Animation>();
	private static Map<String, Animation> attackingRightAnimationMap = new HashMap<String, Animation>();
	private static Map<String, Animation> attackingLeftAnimationMap = new HashMap<String, Animation>();
	private static Map<String, Animation> blockingRightAnimationMap = new HashMap<String, Animation>();
	private static Map<String, Animation> blockingLeftAnimationMap = new HashMap<String, Animation>();
	private static Map<String, Animation> flinchingRightAnimationMap = new HashMap<String, Animation>();
	private static Map<String, Animation> flinchingLeftAnimationMap = new HashMap<String, Animation>();
	private static Integer spriteWidth;
	private static Integer spriteHeight;
	private static String[] spriteDimensions = new String[2]; 
	
	//loads and returns the sprite

	public static Animation loadAnimation(Character c) throws SlickException {
		Animation a = null;
		if (c.isFlinching()) {
			if (c.getFacingDirection() < 0) {
				a = getAnimation(c, flinchingLeftAnimationMap);
			}
			if (c.getFacingDirection() > 0) {
				a = getAnimation(c, flinchingRightAnimationMap);
			}
		} else if (c.isAttacking()) {
			if (c.getFacingDirection() < 0) {
				a = getAnimation(c, attackingLeftAnimationMap);
			}
			if (c.getFacingDirection() > 0) {
				a = getAnimation(c, attackingRightAnimationMap);
			}
			
		} else if (c.isBlocking()) {
			if (c.getFacingDirection() < 0) {
				a = getAnimation(c, blockingLeftAnimationMap);
			}
			if (c.getFacingDirection() > 0) {
				a = getAnimation(c, blockingRightAnimationMap);
			}
		} else if (c.isJumping()) {
			if (c.getFacingDirection() < 0 && c.getJumpDirection() > 0) {
				a = getAnimation(c, jumpingUpLeftAnimationMap);
			}
			if (c.getFacingDirection() > 0 && c.getJumpDirection() > 0) {
				a = getAnimation(c, jumpingUpRightAnimationMap);
			}
			if (c.getFacingDirection() < 0 && c.getJumpDirection() < 0) {
				a = getAnimation(c, jumpingDownLeftAnimationMap);
			}
			if (c.getFacingDirection() > 0 && c.getJumpDirection() < 0) {
				a = getAnimation(c, jumpingDownRightAnimationMap);
			}
		} else if (c.isMoving()) {
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
				+ c.getCharacterName() + "Sheet" + c.getSpriteSize() + ".png";
		spriteDimensions = c.getSpriteSize().split("x");
		spriteWidth = new Integer(spriteDimensions[0]);
		spriteHeight = new Integer(spriteDimensions[1]);
		
		if ( c.isMoving() ) {
			
			SpriteSheet charSheet = new SpriteSheet(new Image(resName), spriteWidth, spriteHeight);
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
		if ( !c.isMoving() ){
			SpriteSheet charSheet = new SpriteSheet(new Image(resName), spriteWidth, spriteHeight);
			Image[] charFrames = new Image[1];
			charFrames[0] = charSheet.getSubImage(0, 0);
			if (c.getFacingDirection() <= 0) {
				charFrames = getFlipped(c, charFrames);
			}
			charAnim = new Animation(charFrames, 10);
		}
		if ( c.getJumpDirection() > 0 ) {
			SpriteSheet charSheet = new SpriteSheet(new Image(resName), spriteWidth, spriteHeight);
			Image[] charFrames = new Image[1];
			charFrames[0] = charSheet.getSubImage(0, 5);
			if (c.getFacingDirection() <= 0 ) {
				charFrames = getFlipped(c, charFrames);
			}
			charAnim = new Animation(charFrames, 10 * c.getProperties().getJumpHeight());
		}
		if ( c.getJumpDirection() < 0 ) {
			SpriteSheet charSheet = new SpriteSheet(new Image(resName), spriteWidth, spriteHeight);
			Image[] charFrames = new Image[1];
			charFrames[0] = charSheet.getSubImage(0, 6);
			if (c.getFacingDirection() <= 0 ) {
				charFrames = getFlipped(c, charFrames);
			}
			charAnim = new Animation(charFrames, 10 * c.getProperties().getJumpHeight());
		}
		if( c.isAttacking() ) {
			SpriteSheet charSheet = new SpriteSheet(new Image(resName), spriteWidth, spriteHeight);
			Image[] charFrames = new Image[2];
			charFrames[0] = charSheet.getSubImage(0, 7);
			charFrames[1] = charSheet.getSubImage(0, 8);
			
			if (c.getFacingDirection() <= 0) {
				charFrames = getFlipped(c, charFrames);
			}
			charAnim = new Animation(charFrames, 160);
			
		}
		if ( c.isBlocking() ) {
			SpriteSheet charSheet = new SpriteSheet(new Image(resName), spriteWidth, spriteHeight);
			Image[] charFrames = new Image[1];
			charFrames[0] = charSheet.getSubImage(0, 8);
			
			if (c.getFacingDirection() <= 0) {
				charFrames = getFlipped(c, charFrames);
			}
			charAnim = new Animation(charFrames, 50);
		}
		if ( c.isFlinching() ) {
			SpriteSheet charSheet = new SpriteSheet(new Image(resName), spriteWidth, spriteHeight);
			Image[] charFrames = new Image[1];
			charFrames[0] = charSheet.getSubImage(0, 4);
			if (c.getFacingDirection() <= 0) {
				charFrames = getFlipped(c, charFrames);
			}
			charAnim = new Animation(charFrames, 160);
		}
		
		if (charAnim == null) {
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
