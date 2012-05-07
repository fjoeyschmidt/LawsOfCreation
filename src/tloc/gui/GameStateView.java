package tloc.gui;

import java.awt.Font;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import tloc.entities.Controls;
import tloc.entities.GameState;
import tloc.entities.Character;

/**
 * GameStateView draws the GUI.
 *
 */
public class GameStateView extends BasicGameState {
	private int stateID = 0;
	
	public GameStateView(int stateID) {
		this.stateID = stateID;
	}

	private static GameState game;
	private final int HEIGHT = 600;
	private static List<Character> entities;
	private Map<Character, Animation> sprites = new HashMap<Character, Animation>();
	private Image area;
	private Animation sprite;
	private Font font = new Font("Verdana", Font.BOLD, 40);
	private TrueTypeFont trueTypeFont;

	//initialize game Objects
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		trueTypeFont = new TrueTypeFont(font, true);
		game = new GameState();
		Controls.newControls();

		//get image for area
		area = SpriteFactory.getSprite(game.getCurrentArea());

		//add new characters
		addNewCharacters();
	}
	
	//render gui objects method
	@Override
	public void render(GameContainer container, StateBasedGame state, Graphics arg2)
			throws SlickException {
		String playerHealth = "Health: " + game.getPlayer().getCurrentHealth() + " / " + game.getPlayer().getProperties().getMaxHealth();
		String playerXP = "Exp: " + game.getPlayer().getExp() + " / " + game.getPlayer().getExpToLvl();
				
		//draw area
		area.draw(0, 0);
		trueTypeFont.drawString(200, 20, playerHealth);
		trueTypeFont.drawString(250, 70, playerXP);
		
		
		//add new characters
		addNewCharacters();
		
		//for each character draw their sprite
		entities = game.getEntityList();
		for (Character currentChar : entities) {
			if (currentChar.isJumping()) {
				String resName = GameStateView.class.getPackage().getName().replace('.', '/') + "/res/" +
						"ShadowSheet35x35.png";
				Image shadow = new Image(resName);
				shadow.draw(currentChar.getCharacterLocation().getxLocation() + 5, 
						HEIGHT - currentChar.getProperties().getHeight() + 30 - currentChar.getCharacterLocation().getyLocation());
			}
			sprite = CharacterAnimationFactory.loadAnimation(currentChar);
			int charHeight = sprites.get(currentChar).getHeight();
			charHeight = charHeight - currentChar.getProperties().getHeight();
			if (currentChar.isJumping()) {
				sprite.draw(currentChar.getJumpingLocation().getxLocation(),
						HEIGHT - currentChar.getProperties().getHeight() - currentChar.getJumpingLocation().getyLocation());
				
			} else {
				sprite.draw(currentChar.getCharacterLocation().getxLocation(),
						HEIGHT - currentChar.getProperties().getHeight() - currentChar.getCharacterLocation().getyLocation());
			}
		}
		
		//if Player dies open Game Over screen
		if( game.isGameOver() ) {
			state.enterState(RunGame.GAMEOVERSTATE);
		}
		if ( game.getPlayer().checkLvlUp() ) {
			state.enterState(RunGame.LEVELUPSTATE);
		}
	}

	//update gamestate method
	@Override
	public void update(GameContainer gc, StateBasedGame arg1, int arg2)
			throws SlickException {
		if (GameController.lvlUp) {
			GameController.levelUp(game.getPlayer());
		}
		gc.getInput();
		//call method handler for player input
		GameController.handleInput(gc.getInput(), game);

		//updates game state
		game.update();
	}

	@Override
	public int getID() {
		return stateID;
	}

	public void addNewCharacters() throws SlickException {
		//get all characters
		entities = game.getEntityList();
		//map character animations with characters
		for (Character currentChar : entities) {
			if ( currentChar.isNewCharacter() ) {
				sprites.put(currentChar, CharacterAnimationFactory.loadAnimation(currentChar));

				//set height and width properties of character to reflect sprite size
				currentChar.getProperties().setHeight(sprites.get(currentChar).getHeight());
				currentChar.getProperties().setWidth(sprites.get(currentChar).getWidth());
				
				currentChar.setNewCharacter(false);
			}
		}
	}

}
