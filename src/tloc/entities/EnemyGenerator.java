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
		int maxHealth, damage, defense, speed, level;
		level = game.getPlayer().getLevel();
		maxHealth = 10 * level;
		damage = level;
		defense = level;
		speed = game.getPlayer().getSpeed();
		Enemy enemy = new Enemy("Karn", maxHealth, damage, defense, speed, 20, "60x90");
		
		//generate a weapon for the enemy
		Weapon enemyWeapon = createScaledWeapon();
		enemy.setWeapon(enemyWeapon);
		
		//generate a location for the enemy
		Location enemyLoc = generateStartingLocation();
		enemy.setCharacterLocation(enemyLoc);
		
		game.addCharacter(enemy);
	}
	
	//create a scaled weapon based of player's weapon stats
	private static Weapon createScaledWeapon() {
		int damage = random.nextInt( game.getPlayer().getLevel() );
		damage++;
		damage = damage * 5;
		return new Weapon("enemyWeapon", damage, 15);
	}
	
	private static Location generateStartingLocation() {
		int yLocation = random.nextInt( GameState.getHeight() );
		int xLocation;
		if (game.getPlayer().getCharacterLocation().getxLocation() > GameState.getWidth() / 2) {
			xLocation = 0;
		} else {
			xLocation = GameState.getWidth() - 30;
		}
		return new Location(xLocation, yLocation);
	}
	
	private static void setGame(GameState g) {
		game = g;
	}
}
