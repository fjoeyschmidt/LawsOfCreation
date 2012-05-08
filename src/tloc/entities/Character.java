package tloc.entities;

import java.util.List;


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
	private boolean newCharacter, isJumping, isAttacking, isBlocking, isDead, isFlinching;
	private String SpriteSize;
	private int attackCounter;
	private int flinchCounter;
	private int level;
	
	public Character(String name, int health, int dam, int def, int spd, int jH, String sS, int lvl) {
		setCharacterName(name);
		currentHealth = health;
		damage = dam;
		defense = def;
		speed = spd;
		properties = new CharacterProperties(1, 1, jH, health);
		setSpriteSize(sS);
		setDead(false);
		setAttackCounter(0);
		setFlinchCounter(0);
		setNewCharacter(true);
		setLevel(lvl);
	}
	
	//move method
	public void move(Area area, List<Character> entities) {
		Movement.moveCharacter(this, area, entities);
	}
	
	//jump method
	public void jump() {
		if ( !(this.isJumping()) && !this.isAttacking() ) { 
			this.setJumpingLocation(this.getCharacterLocation());
			setIsJumping(true);
		}
	}
	
	//attack method
	public void attack() {
		this.setIsAttacking(true);
	}
	
	//block method
	public void block() {
		if (!this.isJumping && !this.isAttacking()) {
			this.setIsBlocking(true);
		}
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
		return Space.getCharacterSpace(this);
	}

	public Location getCharacterLocation() {
		return characterLocation;
	}

	public void setCharacterLocation(Location characterLocation) {
		this.characterLocation = characterLocation;
		this.setCharacterSpace();
	}

	private void setCharacterSpace() {
		this.setSpaceTaken(Space.getCharacterSpace(this));
	}

	public void setIsJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}
	
	public boolean isJumping() {
		return this.isJumping;
	}
	
	public void setIsAttacking(boolean isAttacking) {
		this.isAttacking = isAttacking;
		if (!isAttacking) {
			this.setAttackCounter(0);
		}
	}
	
	public boolean isAttacking() {
		return this.isAttacking;
	}
	
	public boolean isFlinching() {
		return this.isFlinching;
	}
	
	public void setIsFlinching(boolean isFlinching) {
		this.isFlinching = isFlinching;
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

	public String getSpriteSize() {
		return SpriteSize;
	}

	public void setSpriteSize(String spriteSize) {
		SpriteSize = spriteSize;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public int getFlinchCounter() {
		return flinchCounter;
	}
	
	public void setFlinchCounter(int flinchCounter) {
		this.flinchCounter = flinchCounter;
	}
	
	public int getAttackCounter() {
		return attackCounter;
	}

	public void setAttackCounter(int attackCounter) {
		this.attackCounter = attackCounter;
	}

	public boolean isBlocking() {
		return isBlocking;
	}

	public void setIsBlocking(boolean isBlocking) {
		this.isBlocking = isBlocking;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}

	public boolean isNewCharacter() {
		return newCharacter;
	}

	public void setNewCharacter(boolean newCharacter) {
		this.newCharacter = newCharacter;
	}

	public void setSpaceTaken(Space spaceTaken) {
		this.spaceTaken = spaceTaken;
	}
}
