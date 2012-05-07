package tloc.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import tloc.entities.LevelUpEnum;

public class LevelUpView extends BasicGameState {
	Image background = null;
	Image addButton = null;
	
	private int stateID = 0;
	
	private static int addX = 650;
	private static int addY = 110;
	private static int lineSpace = 72;
	
	public LevelUpView(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		background = new Image("tloc/gui/res/levelupscreen.png");
		addButton = new Image("tloc/gui/res/addbutton.png");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		background.draw(0, 0);
		for (int i = 0; i < 4; i++) {
			addButton.draw(addX, addY + (lineSpace * i));
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		boolean health = false, damage = false, defense = false, speed = false;
		LevelUpEnum up = null;
		
		if ( (mouseX >= addX && mouseX <= addX + addButton.getWidth()) && (mouseY >= addY && mouseY <= addY + addButton.getHeight()) ) {
			health = true;
		} else if ( (mouseX >= addX && mouseX <= addX + addButton.getWidth()) && (mouseY >= addY + lineSpace && mouseY <= addY + lineSpace +addButton.getHeight()) ) {
			damage = true;
		} else if ( (mouseX >= addX && mouseX <= addX + addButton.getWidth()) && (mouseY >= addY + (lineSpace * 2) && mouseY <= addY + (lineSpace * 2) + addButton.getHeight()) ) {
			defense = true;
		} else if ( (mouseX >= addX && mouseX <= addX + addButton.getWidth()) && (mouseY >= addY + (lineSpace * 3) && mouseY <= addY + (lineSpace * 3) + addButton.getHeight()) ) {
			speed = true;
		}
		
		if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ) {
			if (health) {
				up = LevelUpEnum.HEALTH;
			} else if (damage) {
				up = LevelUpEnum.DAMAGE;
			} else if (defense) {
				up = LevelUpEnum.DEFENSE;
			} else if (speed) {
				up = LevelUpEnum.SPEED;
			}
			if (up != null) {
				GameController.handleLevelUp(up);
				game.enterState(RunGame.GAMEPLAYSTATE);
			}
		}
	}

	@Override
	public int getID() {
		return stateID;
	}

}
