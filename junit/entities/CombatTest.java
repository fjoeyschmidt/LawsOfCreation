package entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tloc.entities.Enemy;
import tloc.entities.GameState;
import tloc.entities.Location;
import tloc.entities.Player;
import tloc.entities.Weapon;

public class CombatTest {
	private GameState game;
	private Player player;
	private int playerHeight = 10, playerWidth = 10;
	private Enemy enemy1;
	private Weapon playerWeapon;
	private int startingHealth = 10, startingDefense = 1, startingDamage = 1;

	@Before
	public void setUp() {
		game = new GameState();
		playerWeapon = new Weapon("Starter", 5, 5);
		player = new Player();
		game.setPlayer(player);
		game.getPlayer().setWeapon(playerWeapon);
		game.getPlayer().getProperties().setHeight(playerHeight);
		game.getPlayer().getProperties().setWidth(playerWidth);
		enemy1 = new Enemy("enemy1", startingHealth, startingDamage, startingDefense, 1, 20, "60x90");
		enemy1.getProperties().setHeight(game.getPlayer().getProperties().getHeight());
		enemy1.getProperties().setWidth(game.getPlayer().getProperties().getWidth());
		game.addCharacter(enemy1);
	}

	@Test
	public void testHitRight() {
		//facing right with enemy on right
		player.setCharacterLocation(new Location(0, 0));
		player.getProperties().setHeight(10);
		player.getProperties().setWidth(10);
		player.setFacingDirection(1);

		enemy1.setCharacterLocation(new Location(10, 10));
		enemy1.getProperties().setHeight(10);
		enemy1.getProperties().setWidth(10);
		enemy1.setFacingDirection(1);
		
		int damage = player.getWeapon().getWeaponDamage() + player.getDamage() - enemy1.getDefense();
		for (int i = 1; enemy1.getCurrentHealth() > 0; i++) {
			player.attack();
			game.update();
			player.setIsAttacking(false);
			assertEquals(enemy1.getProperties().getMaxHealth() - (damage * i), enemy1.getCurrentHealth());
		}
		assertFalse(game.getEntityList().contains(enemy1));
	}

	@Test
	public void testHitLeft() {
		
		
		player.setCharacterLocation(new Location(5,0));
		player.getProperties().setHeight(10);
		player.getProperties().setWidth(10);
		player.setFacingDirection(-1);
		
		enemy1.setCharacterLocation(new Location(0,0));
		enemy1.getProperties().setHeight(10);
		enemy1.getProperties().setWidth(10);
		enemy1.setCurrentHealth(10);
		enemy1.getProperties().setMaxHealth(10);
		enemy1.setFacingDirection(1);
		
		int damage = player.getWeapon().getWeaponDamage() + player.getDamage() - enemy1.getDefense();
		
		for (int i = 1; enemy1.getCurrentHealth() > 0; i++) {
			player.attack();
			game.update();
			player.setIsAttacking(false);
			assertEquals(enemy1.getProperties().getMaxHealth() - (damage * i), enemy1.getCurrentHealth());
		}
		assertFalse(game.getEntityList().contains(enemy1));
		
	}

}
