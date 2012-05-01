package tloc.gui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

import tloc.entities.Controls;
import tloc.entities.GameState;
import tloc.entities.Character;

/**
 * GameStateView draws the GUI.
 *
 */
public class GameStateView extends BasicGame {

	public GameStateView(String title) {
		super(title);
	}

	private static GameState game;
	private final int HEIGHT = 600;
	private static List<Character> entities;
	private Map<Character, Animation> sprites = new HashMap<Character, Animation>();
	private Image area;
	private Animation sprite;

	//initialize game Objects
	@Override
	public void init(GameContainer gc) throws SlickException {
		game = new GameState();
		Controls.newControls();
		entities = GameState.getEntityList();

		//get image for area
		area = SpriteFactory.getSprite(game.getCurrentArea());

		//get all characters
		entities = GameState.getEntityList();
		Iterator<Character> charIter = entities.iterator();
		//map character animations with characters
		while (charIter.hasNext()) {
			Character currentChar = charIter.next();
			sprites.put(currentChar, CharacterAnimationFactory.loadAnimation(currentChar));

			//set height and width properties of character to reflect sprite size
			currentChar.getProperties().setHeight(sprites.get(currentChar).getHeight());
			currentChar.getProperties().setWidth(sprites.get(currentChar).getWidth());
		}
	}

	//render gui objects method
	@Override
	public void render(GameContainer gc,  Graphics g)
			throws SlickException {
		//draw area
		area.draw(0, 0);

		//for each character draw their sprite
		Iterator<Character> entityIter = entities.iterator();
		while (entityIter.hasNext()) {
			Character currentChar = entityIter.next();
			sprite = CharacterAnimationFactory.loadAnimation(currentChar);
			int charHeight = sprites.get(currentChar).getHeight();
			charHeight = charHeight - currentChar.getProperties().getHeight();
			if (currentChar.isJumping()) {
				sprite.draw(currentChar.getJumpingLocation().getxLocation(),
						HEIGHT - currentChar.getProperties().getHeight() - currentChar.getJumpingLocation().getyLocation());
			} else {
				sprite.draw(currentChar.getCharacterLocation().getxLocation(),
						HEIGHT - currentChar.getProperties().getHeight() - currentChar.getCharacterLocation().getyLocation());
				if(currentChar.isAttacking()) {
					currentChar.setIsAttacking(false);
				}
			}
		}
	}

	//update gamestate method
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		gc.getInput();
		//call method handler for player input
		GameController.handleInput(gc.getInput(), game);

		//updates game state
		game.update();
		
		
	}
}
