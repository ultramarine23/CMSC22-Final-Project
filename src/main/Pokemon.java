package main;

public class Pokemon {
	StatsContainer baseStats;
	StatsContainer currentStats;
	
	public Pokemon(StatsContainer newBaseStats) {
		baseStats = newBaseStats;
		currentStats = newBaseStats;
		
		System.out.println();
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
