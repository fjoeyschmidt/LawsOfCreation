package entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tloc.entities.Area;
import tloc.entities.Character;
import tloc.entities.Enemy;
import tloc.entities.Location;
import tloc.entities.Movement;
import tloc.entities.Space;
import tloc.entities.Structure;
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
	private Space espace;
	
	private int phealth = 30;
	private int pdamage = 2;
	private int pdefense = 1;
	private int pspeed = 2;
	private int pheight = 2;
	private int pwidth = 2;
	private int pjumpHeight = 5;
	private Location playerLocation = new Location(10, 10);
	
	private Location wallLocation = new Location(20, 20);
	private int wallHeight = 20;
	private int wallWidth = 20;
	private Structure wall;
	
	Location rightOrLeft = new Location(1, 0);
	Location upOrDown = new Location(0, 1);
	Location rightLeftUpDown = new Location(1, 1);
	
	@Before
	public void setUp() {
		e = new Enemy("enemy", ehealth, edamage, edefense, espeed, eheight, ewidth, ejumpHeight);
		e.setCharacterLocation(enemyLocation);
		espace = Space.getCharacterSpace(e);
		p = new Enemy("player", phealth, pdamage, pdefense, pspeed, pheight, pwidth, pjumpHeight);
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
		for (int i = 0; i < e.getProperties().getJumpHeight() * 2; i++) {
			Movement.moveCharacter(e, null);
			assertTrue(e.isJumping());
		}
		assertTrue(Location.sameLocation(e.getCharacterLocation(), enemyLocation));
		
		e.setxDirection(1);
		e.jump();
		int i;
		for (i = 0; i < e.getProperties().getJumpHeight() * 2; i++) {
			Movement.moveCharacter(e, null);
			assertTrue(e.isJumping());
		}
		
		assertTrue(Location.sameLocation(new Location( (((e.getxDirection() * e.getSpeed())*i)+1) , 1), e.getCharacterLocation()));
	}
}
