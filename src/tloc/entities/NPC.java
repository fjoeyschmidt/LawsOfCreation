package tloc.entities;

/** A subclass of Character for NPCs.
 * Contains methods to start conversation
 * and to move NPC.
 */

public class NPC extends Character {
	
	public NPC(String name, int health, int dam, int def, int spd, int h, int w, int jH, String sS) {
		super(name, health, dam, def, spd, h, w, jH, sS);
	}

	//start conversation with player
	public void convoStart() {
		
	}
	
	//Artificial movement
	public void aiMovement() {
		
	}
}
