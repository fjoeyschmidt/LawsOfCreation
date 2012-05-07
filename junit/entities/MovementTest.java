package entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tloc.entities.Character;
import tloc.entities.Enemy;
import tloc.entities.Location;
import tloc.entities.Movement;
import tloc.entities.Space;
import tloc.entities.SubArea;

public class MovementTest {
	
	private Character e, p;
	private int ehealth = 30;
	private int edamage = 2;
	private int edefense = 1;
	private int espeed = 2;
	private int eheight = 2;
	private int ewidth = 2;
	private int ejumpHeight = 5;
	private Location enemyLocation = new Location(1, 1);
	private String sS = "30x30";
	private int phealth = 30;
	private int pdamage = 2;
	private int pdefense = 1;
	private int pspeed = 2;
	private int pheight = 2;
	private int pwidth = 2;
	private int pjumpHeight = 5;
	
	Location rightOrLeft = new Location(1, 0);
	Location upOrDown = new Location(0, 1);
	Location rightLeftUpDown = new Location(1, 1);
	
	@Before
	public void setUp() {
		e = new Enemy("enemy", ehealth, edamage, edefense, espeed, ejumpHeight, sS);
		e.getProperties().setHeight(eheight);
		e.getProperties().setWidth(ewidth);
		e.setCharacterLocation(enemyLocation);
		p = new Enemy("player", phealth, pdamage, pdefense, pspeed, pjumpHeight, sS);
		p.getProperties().setHeight(pheight);
		p.getProperties().setWidth(pwidth);
	}

	// movement test
	@Test
	public void testMove() throws Exception {
		e.setCharacterLocation(enemyLocation);
		e.setSpeed(3);
		e.setxDirection(1);
		e.setyDirection(1);
		Movement.moveCharacter(e, new SubArea(100, 100));
		
		assertTrue(e.isMoving());
		assertTrue(Location.sameLocation(new Location(4, 4),
				e.getCharacterLocation()));
		assertTrue(e.getSpaceTaken().sameSpace(
				new Space(new Location(4, 4), 2, 2)));
		assertEquals(1, e.getFacingDirection());
		
		e.setxDirection(-1);
		
		assertTrue(e.isMoving());
		assertEquals(-1, e.getFacingDirection());
	}
	
	// jump test
	@Test
	public void testJump() throws Exception {
		// standing still
		e.jump();
		assertTrue(e.isJumping());
		while (e.isJumping()) {
			e.move(null);
		}
		assertTrue(Location.sameLocation(enemyLocation, e.getCharacterLocation()));
		assertFalse(e.isJumping());
		
		int counter = 0;
		e.setxDirection(1);
		e.jump();
		assertTrue(e.isJumping());
		while (e.isJumping()) {
			e.move(null);
			counter++;
			e.setyDirection(1);
		}
		assertTrue(Location.sameLocation(new Location(enemyLocation.getxLocation() + (counter * e.getSpeed()), 
				enemyLocation.getyLocation()), e.getCharacterLocation()));
		assertFalse(e.isJumping());
	}
}
