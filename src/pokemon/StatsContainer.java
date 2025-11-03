package pokemon;

public class StatsContainer {
	private int hp = 0;
	private int physAtk = 0;
	private int physDef = 0;
	private int speed = 0;
	private int specAtk = 0;
	private int specDef = 0;
	
	public int getHp() { return hp; }
	public int getAtk() { return physAtk; }
	public int getDef() { return physDef; }
	public int getSpeed() { return speed; }
	public int getSpAtk() { return specAtk; }
	public int getSpDef() { return specDef; }
	
	public void setHp(int newVal) { hp = newVal; }
	public void setAtk(int newVal) { physAtk = newVal; }
	public void setDef(int newVal) { physDef = newVal; }
	public void setSpeed(int newVal) { speed = newVal; }
	public void setSpAtk(int newVal) { specAtk = newVal; }
	public void setSpDef(int newVal) { specDef = newVal; }
	
	public int getStat(String statName) {
		switch (statName) {
			case "hp":
				return hp;
			case "physAtk":
				return physAtk;
			case "physDef":
				return physDef;
			case "speed":
				return speed;
			case "specAtk":
				return specAtk;
			case "specDef":
				return specDef;
			
			default:
				throw new RuntimeException("Invalid stat name.");
		}
	}
	
	public void setStat(String statName, int value) {
		switch (statName) {
			case "hp":
				hp = value;
				break;
			case "physAtk":
				physAtk = value;
				break;
			case "physDef":
				physDef = value;
				break;
			case "speed":
				speed = value;
				break;
			case "specAtk":
				specAtk = value;
				break;
			case "specDef":
				specDef = value;
				break;
		
		default:
			throw new RuntimeException("Invalid stat name: " + statName);
		}
	}
	
	public StatsContainer clone() {
		StatsContainer clonedStats = new StatsContainer();
		
		clonedStats.setHp(hp);
		clonedStats.setAtk(physAtk);
		clonedStats.setDef(physDef);
		clonedStats.setSpAtk(specAtk);
		clonedStats.setSpDef(specDef);
		clonedStats.setSpeed(speed);
		
		return clonedStats;
	}
}
