package tloc.gui;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * 
 */
public class RunGame extends StateBasedGame {
	public static final int MAINMENUSTATE = 0;
	public static final int GAMEPLAYSTATE = 1;
	public static final int LEVELUPSTATE = 2;
	public static final int GAMEOVERSTATE = 3;
	
	public RunGame() {
		super("TLOC");
		
		this.addState(new MainMenuView(MAINMENUSTATE));
		this.addState(new GameStateView(GAMEPLAYSTATE));
		this.addState(new LevelUpView(LEVELUPSTATE));
		this.addState(new GameOverView(GAMEOVERSTATE));
		this.enterState(MAINMENUSTATE);
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer(new RunGame());
		container.setDisplayMode(800, 600, false);
		container.setTargetFrameRate(100);
		container.start();
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.getState(MAINMENUSTATE).init(container, this);
		this.getState(GAMEPLAYSTATE).init(container, this);
		this.getState(LEVELUPSTATE).init(container, this);
		this.getState(GAMEOVERSTATE).init(container, this);
	}
}
