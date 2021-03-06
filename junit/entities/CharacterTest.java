package entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tloc.entities.Character;
import tloc.entities.Enemy;
import tloc.entities.Location;
import tloc.entities.Space;

public class CharacterTest {
	private Character e;
	private int health = 30;
	private int damage = 2;
	private int defense = 1;
	private int speed = 2;
	private int height = 2;
	private int width = 2;
	private int jumpHeight = 5;
	private String sS = "30x30";
	private Location enemyLocation = new Location(1, 1);
	private Space space;
	
	@Before
	public void setUp() {
		e = new Enemy("enemy", health, damage, defense, speed, jumpHeight, sS, 1);
		e.getProperties().setHeight(height);
		e.getProperties().setWidth(width);
		e.setCharacterLocation(enemyLocation);
		space = Space.getCharacterSpace(e);
	}
	
	//basic character test
	@Test
	public void testCharSetUp() throws Exception {
		//enemy
		assertEquals(height, e.getProperties().getHeight());
		assertEquals(width, e.getProperties().getWidth());
		assertEquals(health, e.getProperties().getMaxHealth());
		assertEquals(health, e.getCurrentHealth());
		assertEquals(damage, e.getDamage());
		assertEquals(defense, e.getDefense());
		assertEquals(speed, e.getSpeed());
		assertEquals(jumpHeight, e.getProperties().getJumpHeight());
		assertTrue(Location.sameLocation(e.getCharacterLocation(), enemyLocation));
		assertTrue(space.sameSpace(e.getSpaceTaken()));
	}
	
	//setter test
	@Test
	public void testCharacterSetters() throws Exception {
		//enemy
		e.getProperties().setHeight(3);
		e.getProperties().setWidth(6);
		e.getProperties().setMaxHealth(20);
		e.setCurrentHealth(10);
		e.setDamage(3);
		e.setDefense(4);
		e.setSpeed(6);
		e.setxDirection(1);
		e.setyDirection(-1);
		e.setCharacterLocation(new Location(3,4));
		
		assertEquals(3, e.getProperties().getHeight());
		assertEquals(6, e.getProperties().getWidth());
		assertEquals(20, e.getProperties().getMaxHealth());
		assertEquals(10, e.getCurrentHealth());
		assertEquals(3, e.getDamage());
		assertEquals(4, e.getDefense());
		assertEquals(6, e.getSpeed());
		assertEquals(1, e.getxDirection());
		assertEquals(-1, e.getyDirection());
		assertTrue(Location.sameLocation(new Location(3, 4), e.getCharacterLocation()));
		assertTrue(e.getSpaceTaken().sameSpace(new Space(new Location(3, 4), 3, 6)));
	}
}
