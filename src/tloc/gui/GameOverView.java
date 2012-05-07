package tloc.gui;

import java.awt.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOverView extends BasicGameState {
	Image background = null;
	TrueTypeFont trueTypeFont = null;
	
	private int stateID = 0;

	public GameOverView(int stateID) {
		this.stateID  = stateID;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		background = new Image("tloc/gui/res/background.png");
		Font font = new Font("Verdana", Font.BOLD, 20);
		trueTypeFont = new TrueTypeFont(font, true);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		background.draw(0, 0);
		trueTypeFont.drawString(400, 500, "GAME OVER!");
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			game.enterState(RunGame.MAINMENUSTATE);
		}
	}

	@Override
	public int getID() {
		return stateID;
	}

}
