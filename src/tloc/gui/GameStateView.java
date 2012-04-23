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
	private final int HEIGHT = 600;
	private static List<Character> entities;
	private static Map<Character, Image> spriteMap = new HashMap<>();
	private Image area;
	private float scale = 1.0f;
	
	//initialize game Objects
	@Override
	public void init(GameContainer gc) throws SlickException {
		game = new GameState();
		Controls.newControls();
		entities = game.getEntityList();
		
		//get image for area
		area = SpriteFactory.getSprite(game.getCurrentArea());
		
		//get images for each entity
		Iterator<Character> entityIter = entities.iterator();
		while (entityIter.hasNext()) {
			Character currentChar = entityIter.next();
			spriteMap.put(currentChar, SpriteFactory.getSprite(currentChar));
			
			//set height and width properties of character to reflect sprite size
			currentChar.getProperties().setHeight(spriteMap.get(currentChar).getHeight());
			currentChar.getProperties().setWidth(spriteMap.get(currentChar).getWidth());
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
			int charHeight = spriteMap.get(currentChar).getHeight();
			spriteMap.get(currentChar).draw(currentChar.getCharacterLocation().getxLocation(),
					HEIGHT - charHeight - currentChar.getCharacterLocation().getyLocation(), scale);
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
