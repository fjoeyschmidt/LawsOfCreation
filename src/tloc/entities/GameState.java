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
	private boolean gameOver;
	private Area currentArea;
	private Player player;
	//private Enemy enemy1;
	private static List<Character> entities = new ArrayList<Character>(); //a list of all characters currently in play

	//constructor
	public GameState() {
		currentArea = new SubArea(330, 760);
		player = new Player();
		player.setCharacterLocation(new Location(100, 300));
		setPlayer(player);
		//enemy1 = new Enemy("Karn", 500, 2, 2, 2, 10, 10, 0);
		gameOver = false;
	}

	//updates game state
	public void update() {
		
		//call functions to move Characters
		Iterator<Character> iter = entities.iterator();
		while (iter.hasNext()) {
			Character c = iter.next();
			if (c.isAttacking()) {
				Combat.attack(c, GameState.getEntityList());
			} else {
				c.move(currentArea);
			}
		}
	}

	

	//add a new character to game state
	public static void addCharacter(Character c) {
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

	public static List<Character> getEntityList() {
		return entities;
	}
}
