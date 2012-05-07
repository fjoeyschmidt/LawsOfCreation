package tloc.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuView extends BasicGameState {
	private Image background = null;
	private Image startGameOption = null;
	private Image exitOption = null;
	private int stateID = 0;
	private static int menuX = 560;
	private static int menuY = 400;

	public MainMenuView(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		background = new Image("tloc/gui/res/background.png");

		Image menuOptions = new Image("tloc/gui/res/menuoptions.png");
		startGameOption = menuOptions.getSubImage(0, 0, 240, 70);
		exitOption = menuOptions.getSubImage(135, 127, 240, 60);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		background.draw(0, 0);
		startGameOption.draw(menuX, menuY);
		exitOption.draw(menuX, menuY + 100);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		boolean startGame = false;
		boolean exitGame = false;

		if( ( mouseX >= menuX && mouseX <= menuX + startGameOption.getWidth()) &&
				( mouseY >= menuY && mouseY <= menuY + startGameOption.getHeight()) ) {
			startGame = true;
		} else if( ( mouseX >= menuX && mouseX <= menuX+ exitOption.getWidth()) &&
				( mouseY >= menuY + 100 && mouseY <= menuY + 100 + exitOption.getHeight()) ) {
			exitGame = true;
		}
		
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			if (startGame) {
				game.enterState(RunGame.GAMEPLAYSTATE);
			} else if (exitGame) {
				container.exit();
			}
		}

	}

	@Override
	public int getID() {
		return stateID;
	}

}
