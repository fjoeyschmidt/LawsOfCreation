package entities;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tloc.entities.Character;
import tloc.entities.Location;
import tloc.entities.Movement;
import tloc.entities.Player;
import tloc.entities.Space;
import tloc.entities.SubArea;

public class SpaceTest {
	private Space space1, space2;
	private Character p, wall;
	private int phealth = 30;
	private int pdamage = 2;
	private int pdefense = 1;
	private int pspeed = 1;
	private int pheight = 1;
	private int pwidth = 1;
	private int pjumpHeight = 5;
	private Location playerLocation = new Location(0, 0);
	private List<Character> entities = new ArrayList<Character>();

	@Before
	public void setUp() {
		space1 = new Space(new Location(1, 1), 4, 4);
		space2 = new Space(new Location(3, 3), 4, 4);
		wall = new Player();
		wall.setCharacterLocation(new Location(20, 20));
		wall.getProperties().setHeight(20);
		wall.getProperties().setWidth(20);
		entities.add(wall);
		p = new Player();
		p.getProperties().setMaxHealth(phealth);
		p.setDamage(pdamage);
		p.setDefense(pdefense);
		p.setSpeed(pspeed);
		p.getProperties().setHeight(pheight);
		p.getProperties().setWidth(pwidth);
		p.getProperties().setJumpHeight(pjumpHeight);
		p.setCharacterLocation(playerLocation);
		entities.add(p);
	}

	// same space test
	@Test
	public void sameSpaceTest() throws Exception {
		assertTrue(space1.sameSpace(new Space(new Location(1, 1), 4, 4)));
		assertTrue(space2.sameSpace(new Space(new Location(3, 3), 4, 4)));
	}

	// overlap test
	@Test
	public void overlapTest() throws Exception {
		assertTrue(Space.checkOverlap(space1, space2));
		assertFalse(Space.checkOverlap(space1, wall.getSpaceTaken()));
	}

	// collision test 1 direction
	@Test
	public void testCollision1Direction() throws Exception {
		int steps = 100;

		// collision right
		p.setCharacterLocation(new Location(0, 30));
		p.setxDirection(1);
		p.setyDirection(0);
		for (int i = 0; i < steps; i++) {
			Space.checkCollision(p.getSpaceTaken(), wall.getSpaceTaken());
			Movement.moveCharacter(p, new SubArea(100, 100), entities);
		}
		assertTrue(Location.sameLocation(p.getCharacterLocation(),
				new Location(18, 30)));

		// collision left
		p.setCharacterLocation(new Location(50, 30));
		p.setxDirection(-1);
		p.setyDirection(0);
		for (int i = 0; i < steps; i++) {
			Space.checkCollision(p.getSpaceTaken(), wall.getSpaceTaken());
			Movement.moveCharacter(p, new SubArea(100,100), entities);
		}
		assertTrue(Location.sameLocation(p.getCharacterLocation(),
				new Location(41, 30)));

		// collision up
		p.setCharacterLocation(new Location(30, 0));
		p.setxDirection(0);
		p.setyDirection(1);
		for (int i = 0; i < steps; i++) {
			Space.checkCollision(p.getSpaceTaken(), wall.getSpaceTaken());
			Movement.moveCharacter(p, new SubArea(100,100), entities);
		}
		assertTrue(Location.sameLocation(p.getCharacterLocation(),
				new Location(30, 18)));

		// collision down
		p.setCharacterLocation(new Location(30, 50));
		p.setxDirection(0);
		p.setyDirection(-1);
		for (int i = 0; i < steps; i++) {
			Space.checkCollision(p.getSpaceTaken(), wall.getSpaceTaken());
			Movement.moveCharacter(p, new SubArea(100,100), entities);
		}
		assertTrue(Location.sameLocation(p.getCharacterLocation(),
				new Location(30, 41)));
	}
}
