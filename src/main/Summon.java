package main;

public class Summon {
	StatsContainer baseStats = new StatsContainer();
	StatsContainer currentStats = new StatsContainer();
	
	int curHp = 0;
	
	public Summon() {
		System.out.println("SKIB");
		System.out.println(String.valueOf(currentStats.hp));
		takeDamage(10);
		System.out.println(String.valueOf(currentStats.hp));
	}
	
	public void takeDamage(int amount) {
		int curHp = currentStats.getStat("hp");
		
		currentStats.setStat("hp", Integer.max(curHp - amount, 0));
		
	}
	
	public void healHealth(int amount) {
		int curHp = currentStats.getStat("hp");
		int maxHp = baseStats.getStat("hp");
		
		currentStats.setStat("hp", Integer.min(maxHp, curHp + amount));
	}
	
	public void die() {
		
	}
	
	public static void main(String[] args) {
		
	}
}
