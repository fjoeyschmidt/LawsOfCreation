package tloc.entities;

public abstract class CommandHandler {
	
	public static void handleCommand(Command command, Character c) {
		if (command == Command.BLOCK) {
			c.block();
		} else {
			c.setIsBlocking(false);
		}
		if (command == Command.MOVERIGHT) {
			c.setxDirection(1);
		}
		if (command == Command.MOVELEFT) {
			c.setxDirection(-1);
		}
		if (command == Command.MOVEUP) {
			c.setyDirection(1);
		} 
		if (command == Command.MOVEDOWN) {
			c.setyDirection(-1);
		}
		if (command == Command.JUMP) {
			c.jump();
		}
		if (command == Command.ATTACK) {
			c.attack();
		}
	}
}
