package tloc.gui;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Input;

import tloc.entities.Command;


public class Controls {
	private static final Map<Command, Integer> controlMap = new HashMap<>();
	
	//set default controls
	public static void newControls() {
		controlMap.put(Command.MOVEDOWN, Input.KEY_S);
		controlMap.put(Command.MOVEUP, Input.KEY_W);
		controlMap.put(Command.MOVELEFT, Input.KEY_A);
		controlMap.put(Command.MOVERIGHT, Input.KEY_D);
		controlMap.put(Command.JUMP, Input.KEY_SPACE);
		controlMap.put(Command.ATTACK, Input.MOUSE_LEFT_BUTTON);
		controlMap.put(Command.BLOCK, Input.MOUSE_RIGHT_BUTTON);
	}
	
	//returns command based off int value
	public static Command getCommand(int input) {
		if (input == controlMap.get(Command.MOVERIGHT)) {
			return Command.MOVERIGHT;
		}
		if (input == controlMap.get(Command.MOVELEFT)) {
			return Command.MOVELEFT;
		}
		if (input == controlMap.get(Command.MOVEUP)) {
			return Command.MOVEUP;
		}
		if (input == controlMap.get(Command.MOVEDOWN)) {
			return Command.MOVEDOWN;
		}
		if (input == controlMap.get(Command.JUMP)) {
			return Command.JUMP;
		}
		if (input == controlMap.get(Command.ATTACK)) {
			return Command.ATTACK;
		}
		if (input == controlMap.get(Command.BLOCK)) {
			return Command.BLOCK;
		}
		return null;
	}
	
	public int getMoveRight() {
		return controlMap.get(Command.MOVERIGHT);
	}

	public void setMoveRight(int moveRight) {
		controlMap.remove(Command.MOVERIGHT);
		controlMap.put(Command.MOVERIGHT, moveRight);
	}

	public int getMoveLeft() {
		return controlMap.get(Command.MOVELEFT);
	}

	public void setMoveLeft(int moveLeft) {
		controlMap.remove(Command.MOVELEFT);
		controlMap.put(Command.MOVELEFT, moveLeft);
	}

	public int getMoveUp() {
		return controlMap.get(Command.MOVEUP);
	}

	public void setMoveUp(int moveUp) {
		controlMap.remove(Command.MOVEUP);
		controlMap.put(Command.MOVEUP, moveUp);
	}

	public int getMoveDown() {
		return controlMap.get(Command.MOVEDOWN);
	}

	public void setMoveDown(int moveDown) {
		controlMap.remove(Command.MOVEDOWN);
		controlMap.put(Command.MOVEDOWN, moveDown);
	}

	public int getJump() {
		return controlMap.get(Command.JUMP);
	}

	public void setJump(int jump) {
		controlMap.remove(Command.JUMP);
		controlMap.put(Command.JUMP, jump);
	}
	public int getAttack() {
		return controlMap.get(Command.ATTACK);
	}

	public void setAttack(int attack) {
		controlMap.remove(Command.ATTACK);
		controlMap.put(Command.ATTACK, attack);
	}
	
	public int getBlock() {
		return controlMap.get(Command.BLOCK);
	}

	public void setBlock(int block) {
		controlMap.remove(Command.BLOCK);
		controlMap.put(Command.BLOCK, block);
	}
}
