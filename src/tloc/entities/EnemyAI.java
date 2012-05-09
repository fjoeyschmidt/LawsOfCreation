package tloc.entities;


public interface EnemyAI extends AI {
	static final float fiftyPercent = 0.5f;
	static final float fourtyPercent = 0.4f;
	static final float thirtyPercent = 0.3f;
	static final float twentyPercent = 0.2f;
	static final float tenPercent = 0.1f;

	@Override
	public void action(Character c);
	public boolean inRange(Character c);
	public boolean avoid();
	public Command[] flee(Character c);
	public boolean farAway(Character c);
	public Command[] moveTo(Character c);
	public int playerAboveOrBelow(Character c);
	public int playerLeftOrRight(Character c);
}
