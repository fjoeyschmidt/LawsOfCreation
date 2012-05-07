package tloc.gui;

import org.newdawn.slick.Input;
import tloc.entities.Command;
import tloc.entities.CommandHandler;
import tloc.entities.Controls;
import tloc.entities.GameState;
import tloc.entities.LevelUpEnum;

public class GameController {
	public static boolean lvlUp = false;
	private static LevelUpEnum statUp;
	
	public static void handleInput(Input input, GameState game) {
		//check if input is to move up or down
		if (input.isKeyDown(Input.KEY_S)) {
			handleCommand(Controls.getCommand(Input.KEY_S), game);
		} else if (input.isKeyDown(Input.KEY_W)) {
			handleCommand(Controls.getCommand(Input.KEY_W), game);
		} else {
			game.getPlayer().setyDirection(0);
		}
		
		//check if input is to move left of right
		if (input.isKeyDown(Input.KEY_D)) {
			handleCommand(Controls.getCommand(Input.KEY_D), game);
		} else if (input.isKeyDown(Input.KEY_A)) {
			handleCommand(Controls.getCommand(Input.KEY_A), game);
		} else {
			game.getPlayer().setxDirection(0);
		}
		
		//check if input is jump
		if (input.isKeyDown(Input.KEY_SPACE)) {
			handleCommand(Controls.getCommand(Input.KEY_SPACE), game);
		}
		
		//check if input is attack
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			handleCommand(Controls.getCommand(Input.MOUSE_LEFT_BUTTON), game);
		}
		
		//check if input is block
		if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
			handleCommand(Controls.getCommand(Input.MOUSE_RIGHT_BUTTON), game);
		}
	}
	
	//takes a command and calls player methods accordingly
	private static void handleCommand(Command command, GameState game) {
		CommandHandler.handleCommand(command, game.getPlayer());
	}

	public static void handleLevelUp(LevelUpEnum up) {
		lvlUp = true;
		statUp = up;
	}
	
	public static void levelUp(tloc.entities.LevelUp level) {
		level.levelUp(statUp);
	}

	
}
