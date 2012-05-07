package tloc.entities;


public interface LevelUp {
	public void addExp(int exp);
	public boolean checkLvlUp();
	public void levelUp(LevelUpEnum statUp);
}
