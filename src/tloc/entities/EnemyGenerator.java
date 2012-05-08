package tloc.entities;

import java.util.Random;

public abstract class EnemyGenerator {
	private static Random random;
	
	static {
		random = new Random();
	}
	
	public static void generateEnemies(OurGameState game) {
		int enemyCount = 0;
		for (Character c : game.getEntityList()) {
			if (c instanceof Enemy) {
				enemyCount++;
			}
		}
		
		while (enemyCount < game.getMaxEnemies()) {
			createScaledEnemy(game);
			enemyCount++;
		}
	}
	
	//creates new enemy scaled off of player level
	private static void createScaledEnemy(OurGameState game) {
		int maxHealth, damage, defense, speed, level;
		level = game.getPlayer().getLevel();
		maxHealth = 10 * level;
		damage = level;
		defense = level;
		speed = game.getPlayer().getSpeed();
		Enemy enemy = new Enemy("Karn", maxHealth, damage, defense, speed, 20, "60x90", level);
		
		
		//generate a weapon for the enemy
		Weapon enemyWeapon = createScaledWeapon(game);
		enemy.setWeapon(enemyWeapon);
		
		//generate a location for the enemy
		Location enemyLoc = generateStartingLocation(game);
		enemy.setCharacterLocation(enemyLoc);
		
		game.addCharacter(enemy);
	}
	
	//create a scaled weapon based of player's weapon stats
	private static Weapon createScaledWeapon(OurGameState game) {
		int damage = random.nextInt( game.getPlayer().getLevel() );
		damage++;
		damage = damage * 2;
		return new Weapon("enemyWeapon", damage, 10);
	}
	
	private static Location generateStartingLocation(OurGameState game) {
		int yLocation = random.nextInt( OurGameState.getHeight() );
		int xLocation;
		if (game.getPlayer().getCharacterLocation().getxLocation() > OurGameState.getWidth() / 2) {
			xLocation = 10;
		} else {
			xLocation = OurGameState.getWidth() - 40;
		}
		
		Location startLoc = new Location(xLocation, yLocation);
		
		startLoc = Area.outOfBounds(game.getCurrentArea(), startLoc);
		
		return startLoc;
	}
	
	
}
