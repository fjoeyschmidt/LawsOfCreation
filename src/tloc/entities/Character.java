package tloc.entities;


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
	private boolean isJumping, isAttacking, isDead;
	private String SpriteSize;
	private int attackCounter;
	
	public Character(String name, int health, int dam, int def, int spd, int h, int w, int jH, String sS) {
		setCharacterName(name);
		currentHealth = health;
		damage = dam;
		defense = def;
		speed = spd;
		properties = new CharacterProperties(h, w, jH, health);
		setSpriteSize(sS);
		setDead(false);
		setAttackCounter(0);
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
		if (!isAttacking) {
			this.setAttackCounter(0);
		}
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

	public int getAttackCounter() {
		return attackCounter;
	}

	public void setAttackCounter(int attackCounter) {
		this.attackCounter = attackCounter;
	}
}
