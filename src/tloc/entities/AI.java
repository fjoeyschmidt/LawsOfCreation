package tloc.entities;

public interface AI {
	public void aiMove(Character c, Player p);
	public void aiAttack(Character c, Player p);
	public void aiAvoid(Character c, Player p);
}
