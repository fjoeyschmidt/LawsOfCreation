package tloc.entities;

import java.util.Random;


/** Enemy class.
 * Methods for combat.
 */

public class Enemy extends Character implements EnemyAI{
	private Random random = new Random();
	private boolean fleeing, moveToPlayer;
	private Command[] commandments;

	public Enemy(String name, int health, int dam, int def, int spd, int jH, String sS, int lvl) {
		super(name, health, dam, def, spd, jH, sS, lvl);
		setWeapon(new Weapon("Standard", 5, 5));
	}

	//look at AI and determine action
	@Override
	public void action(Character c) {
		if ( avoid() ) {
			setFleeing(true);
		} else {
			setFleeing(false);
			this.setFacingDirection(playerLeftOrRight(c));
			if ( inRange(c) ) {
				this.attack();
			} else {
				commandments = this.moveTo(c);
				for (int i = 0; i < commandments.length; i++) {
					CommandHandler.handleCommand(commandments[i], this);
				}
			}
		}
	}

	//if player is inRange of enemy attack return true
	@Override
	public boolean inRange(Character c) {
		Space attackGrid = Combat.getAttackSpace(this);
		if (Space.checkOverlap(attackGrid, c.getSpaceTaken())) {
			return true;
		}
		return false;
	}

	//depending on currentHealth chance to flee and then block
	@Override
	public boolean avoid() {
		int health = this.getCurrentHealth();
		int maxHealth = this.getProperties().getMaxHealth();
		if (health <= (maxHealth * fiftyPercent) ) {
			int roll = random.nextInt(10);
			if (health <= (maxHealth * tenPercent)) {
				return true;
			} else if (health <= (maxHealth * twentyPercent) && roll <= 3) {
				return true;
			}  else if (health <= (maxHealth * thirtyPercent) && roll <= 2) {
				return true;
			}  else if (health <= (maxHealth * fourtyPercent) && roll <= 1) {
				return true;
			} else if (roll == 0) {
				return true;
			}
		}
		return false;
	}

	//call methods to determine where enemy should move to
	@Override
	public Command[] moveTo(Character c) {
		Command[] commands;
		if ( this.isFleeing() ) {
			commands = this.flee(c);
		} else if (this.playerAboveOrBelow(c) == 0) {
			commands = new Command[1];
			if (this.playerLeftOrRight(c) == -1) {
				commands[0]	= Command.MOVELEFT;
			} else {
				commands[0] = Command.MOVERIGHT;
			}
		} else {
			commands = new Command[2];
			if (this.playerLeftOrRight(c) == -1) {
				commands[0]	= Command.MOVELEFT;
			} else {
				commands[0] = Command.MOVERIGHT;
			}
			if (this.playerAboveOrBelow(c) == -1) {
				commands[1]	= Command.MOVEDOWN;
			} else {
				commands[1] = Command.MOVEUP;
			}
		}

		return commands;
	}

	//handle fleeing
	@Override
	public Command[] flee(Character c) {
		Command[] commands;
		if ( this.farAway(c) ) {
			commands = new Command[1];
			commands[0] = Command.BLOCK;
		} else {
			commands = new Command[2];
			if (this.playerAboveOrBelow(c) == 0 || this.playerAboveOrBelow(c) == 1) {
				commands[0] = Command.MOVEDOWN;
			} else {
				commands[0] = Command.MOVEUP;
			}
			if (this.playerLeftOrRight(c) == 1) {
				commands[1] = Command.MOVELEFT;
			} else {
				commands[1] = Command.MOVERIGHT;
			}
		}
		return commands;
	}

	//if enemy is out of range of player return true
	@Override
	public boolean farAway(Character c) {
		int enemyY = this.getCharacterLocation().getyLocation();
		int enemyX = this.getCharacterLocation().getxLocation();
		int playerY = c.getCharacterLocation().getxLocation();
		int playerX = c.getCharacterLocation().getyLocation();
		int xDistance = playerX - enemyX;
		int yDistance = playerY - enemyY;
		if (xDistance < 0) {
			xDistance = xDistance * -1;
		}
		if (yDistance < 0) {
			yDistance = yDistance * -1;
		}

		if (xDistance >= 50 && yDistance >= 20) {
			return true;
		}
		return false;
	}

	//return 1 if player is above enemy, return 0 on same level, return -1 if player is below
	@Override
	public int playerAboveOrBelow(Character c) {
		int enemyY = this.getCharacterLocation().getyLocation();
		int playerY = c.getCharacterLocation().getyLocation();
		if (playerY < enemyY) {
			return -1;
		}
		if (playerY > enemyY) {
			return 1;
		}
		return 0;
	}

	//return 1 if player is right of enemy, return -1 if player is left of enemy
	@Override
	public int playerLeftOrRight(Character c) {
		int enemyX = this.getCharacterLocation().getxLocation();
		int playerX = c.getCharacterLocation().getxLocation();
		if (playerX < enemyX) {
			return -1;
		}
		return 1;
	}

	//getters and setters
	public boolean isFleeing() {
		return fleeing;
	}

	public void setFleeing(boolean fleeing) {
		this.fleeing = fleeing;
	}

	public boolean isMoveToPlayer() {
		return moveToPlayer;
	}

	public void setMoveToPlayer(boolean moveToPlayer) {
		this.moveToPlayer = moveToPlayer;
	}
}
