package main;

public class StatsContainer {
	int hp = 0;
	int physAtk = 0;
	int physDef = 0;
	int speed = 0;
	int specAtk = 0;
	int specDef = 0;
	
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
}
