package tloc.gui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
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
	private final int WIDTH = 800, HEIGHT = 600;
	private static List<Character> entities;
	private Map<Character, Image> sprites = new HashMap<Character, Image>();
	private Character character;
	private Image area;
	private Image sprite;
	private float scale = 1.0f;
	
	//initialize game Objects
	@Override
	public void init(GameContainer gc) throws SlickException {
		game = new GameState();
		Controls.newControls();
		new SpriteFactory();
		area = SpriteFactory.getSprite(game.getCurrentArea());
		
		//get all characters
		entities = game.getEntityList();
		Iterator<Character> charIter = entities.iterator();
		//map characters to sprites
		while (charIter.hasNext()) {
			character = charIter.next();
			sprite = SpriteFactory.getSprite(character);
			sprites.put(character, sprite);
		}
	}
	
	//render gui objects method
	@Override
	public void render(GameContainer gc,  Graphics g)
			throws SlickException {
		//draw area
		area.draw(0, 0);

		//get sprite for each character and then draw each character
		Iterator<Character> charIter = entities.iterator();
		while (charIter.hasNext()) {
			sprite = sprites.get(charIter.next());
			int height = sprite.getHeight();
			height = height - character.getProperties().getHeight();
			int width = character.getProperties().getWidth();
			sprite.draw(width + character.getCharacterLocation().getxLocation(),
					HEIGHT - height - character.getCharacterLocation().getyLocation(),
					scale);
		}
	}
	
	//update gamestate method
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();
		//call method handler for player input
		GameController.handleInput(input, game);
		
		//updates game state
		game.update();
	}
}
