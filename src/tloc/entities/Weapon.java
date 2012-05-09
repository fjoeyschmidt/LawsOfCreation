package tloc.entities;

/** Weapon class.
 * Fields for weapon damage and range.
 */

public class Weapon {
	private String name;
	private int weaponDamage;
	private int range;
	
	public Weapon(String name, int damage, int range) {
		this.setName(name);
		this.setRange(range);
		this.setWeaponDamage(damage);
	}
	
	//Getters and Setters
	public int getWeaponDamage() {
		return weaponDamage;
	}

	public void setWeaponDamage(int weaponDamage) {
		this.weaponDamage = weaponDamage;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
