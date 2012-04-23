package tloc.gui;

import javax.swing.SwingUtilities;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 * 
 */
public class RunGame {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				//create the GameState
				
				//create the view and set the model game
				GameStateView view = new GameStateView("TLOC");
				
				//create a container for game
				try {
					AppGameContainer container = new AppGameContainer(view);
					container.setDisplayMode(800, 600, false);
					container.setTargetFrameRate(100);
					container.start();
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
