package tloc.entities;

import java.util.Random;

public abstract class EnemyGenerator {
	private static Random random;
	private static GameState game;
	static {
		random = new Random();
	}
	
	public static void generateEnemies(GameState game) {
		int enemyCount = 0;
		setGame(game);
		for (Character c : game.getEntityList()) {
			if (c instanceof Enemy) {
				enemyCount++;
			}
		}
		
		while (enemyCount < game.getMaxEnemies()) {
			createScaledEnemy();
			enemyCount++;
		}
	}
	
	//creates new enemy scaled off of player level
	private static void createScaledEnemy() {
		int jumpHeight, maxHealth
		int playerLvl = game.getPlayer().getLevel();
		Enemy enemy;
		
		//generate a weapon for the enemy
		Weapon enemyWeapon = createScaledWeapon();
		//generate a location for the enemy
		Location enemyLoc = generateStartingLocation();
		
		
	}
	
	//create a scaled weapon based of player's weapon stats
	private static Weapon createScaledWeapon() {
		
	}
	
	private static Location generateStartingLocation() {
		
	}
	
	private static void setGame(GameState g) {
		game = g;
	}
}
