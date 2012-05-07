package tloc.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

/** GameState class.
 * Representation of current state of game.
 * Static update method 
 */

public class GameState extends Observable {
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private int maxEnemies;
	private boolean gameOver;
	private Area currentArea;
	private Player player;
	private List<Character> entities = new ArrayList<Character>(); //a list of all characters currently in play

	//constructor
	public GameState() {
		currentArea = new SubArea(330, 760);
		player = new Player();
		player.setCharacterLocation(new Location(100, 300));
		setPlayer(player);
		//EnemyGenerator.generateEnemies(this);
		setGameOver(false);
		setMaxEnemies(1);
	}

	//updates game state
	public void update() {
		
		//add enemies to game state
		//EnemyGenerator.generateEnemies(this);
		
		//for each Character in play (get AI actions) perform action
		for (Character c : entities) {
			if (c instanceof AI) {
				if( c instanceof EnemyAI) {
					((Enemy) c).action(player);
				}
			}
			if (c.isAttacking()) {
				if (c.getAttackCounter() == 0) {
					Combat.attack(c, this.getEntityList());
				}
			} else {
				c.move(currentArea);
			}
		}
		
		//check for dead characters in play and remove them
		Iterator<Character> iter = entities.iterator();
		for (Character c = iter.next(); iter.hasNext(); ) {
			if (c.isDead()) {
				entities.remove(c);
			}
		}
	}

	//add a new character to game state
	public void addCharacter(Character c) {
		entities.add(c);
	}

	//getters and setters	
	public static int getWidth() {
		return WIDTH;
	}
	public static int getHeight() {
		return HEIGHT;
	}

	public Area getCurrentArea() {
		return currentArea;
	}

	public void setCurrentArea(Area currentArea) {
		this.currentArea = currentArea;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
		addCharacter(this.player);
	}

	public boolean isGameOver() {
		return this.gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public List<Character> getEntityList() {
		return entities;
	}

	public int getMaxEnemies() {
		return maxEnemies;
	}

	public void setMaxEnemies(int maxEnemies) {
		this.maxEnemies = maxEnemies;
	}
}
