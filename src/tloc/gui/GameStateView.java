package tloc.gui;

import java.util.Iterator;
import java.util.List;

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
	private Image area;
	private Image playerSprite;
	private float scale = 1.0f;
	
	//initialize game Objects
	@Override
	public void init(GameContainer gc) throws SlickException {
		game = new GameState();
		Controls.newControls();
		area = new Image("tloc/gui/res/LAND.png");
		playerSprite = new Image("tloc/gui/res/PLAYER.png");
	}
	
	//render gui objects method
	@Override
	public void render(GameContainer gc,  Graphics g)
			throws SlickException {
		//draw area
		area.draw(0, 0);
		
		//get all characters
		entities = game.getEntityList();
		Iterator<Character> charIter = entities.iterator();
		
		//get sprite for each character and then draw each character
		while (charIter.hasNext()) {
			Character tempChar = charIter.next();
			
		}
		
		
		int playerHeight = playerSprite.getHeight();
		playerHeight = playerHeight - game.getPlayer().getProperties().getHeight();
		int playerWidth = game.getPlayer().getProperties().getWidth();
		playerSprite.draw(playerWidth + game.getPlayer().getCharacterLocation().getxLocation(), 
				HEIGHT - playerHeight - game.getPlayer().getCharacterLocation().getyLocation(), scale);
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
