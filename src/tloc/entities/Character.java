package tloc.entities;

import java.util.LinkedList;
import java.util.Queue;


/** Abstract superclass for all characters
 * including Player, Enemy, Boss and NPC.
 * The fields are fields that will change frequently
 * for Characters.  The other fields are contained
 * in CharacterProperties.
 * 
 * Instances of Character subclasses must have
 * a height and width before a characterLocation
 * is assigned.
 */

public abstract class Character {
	
	//standard character fields
	private String characterName;
	private Weapon weapon;
	private CharacterProperties properties;
	private int currentHealth;
	private int damage, defense, speed;
	private int facingDirection = 1, xDirection = 0, yDirection = 0, jumpDirection = 0;
	private Space spaceTaken;
	private Location characterLocation, jumpingLocation;  //top left corner
	private boolean isJumping, isAttacking;
	private Queue<Location> moveQueue = new LinkedList<Location>();
	
	public Character(String name, int health, int dam, int def, int spd, int h, int w, int jH) {
		setCharacterName(name);
		currentHealth = health;
		damage = dam;
		defense = def;
		speed = spd;
		properties = new CharacterProperties(h, w, jH, health);
	}
	
	//move method
	public void move(Area area) {
		Movement.moveCharacter(this, area);
	}
	
	//jump method
	public void jump() {
		if ( !(this.isJumping()) ) { 
			this.setJumpingLocation(this.getCharacterLocation());
			setIsJumping(true);
		}
	}
	
	//attack method
	public void attack() {
		Combat.attack(this);
		this.setIsAttacking(true);
	}
	
	//block method
	public void block() {
		Combat.block(this);
	}
	
	//Getters and Setters
	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getxDirection() {
		return xDirection;
	}

	public void setxDirection(int xDirection) {
		this.xDirection = xDirection;
		if (xDirection != 0) {
			this.facingDirection = xDirection;
		}
	}

	public int getyDirection() {
		return yDirection;
	}

	public void setyDirection(int yDirection) {
		this.yDirection = yDirection;
	}

	public Space getSpaceTaken() {
		return spaceTaken;
	}

	public Location getCharacterLocation() {
		return characterLocation;
	}

	public void setCharacterLocation(Location characterLocation) {
		this.characterLocation = characterLocation;
		this.setCharacterSpace();
	}

	private void setCharacterSpace() {
		this.spaceTaken = Space.getCharacterSpace(this);
	}

	public void setIsJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}
	
	public boolean isJumping() {
		return this.isJumping;
	}
	
	public void setIsAttacking(boolean isAttacking) {
		this.isAttacking = isAttacking;
	}
	
	public boolean isAttacking() {
		return this.isAttacking;
	}	

	public CharacterProperties getProperties() {
		return properties;
	}

	public void setProperties(CharacterProperties properties) {
		this.properties = properties;
	}
	
	//returns true if character is moving
	public boolean isMoving() {
		if (this.xDirection == 0 && this.yDirection == 0) {
			return false;
		}
		return true;
	}

	public Queue<Location> getMoveQueue() {
		return moveQueue;
	}

	public void setMoveQueue(Queue<Location> moveQ) {
		moveQueue = moveQ;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public int getFacingDirection() {
		return facingDirection;
	}

	public void setFacingDirection(int facingDirection) {
		this.facingDirection = facingDirection;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public Location getJumpingLocation() {
		return jumpingLocation;
	}

	public void setJumpingLocation(Location jumpingLocation) {
		this.jumpingLocation = jumpingLocation;
	}

	public int getJumpDirection() {
		return jumpDirection;
	}

	public void setJumpDirection(int jumpDirection) {
		this.jumpDirection = jumpDirection;
	}
}
