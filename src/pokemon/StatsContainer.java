package pokemon;

import main.Globals.Stats;

public class StatsContainer {
	private int hp = 0;
	private int physAtk = 0;
	private int physDef = 0;
	private int speed = 0;
	private int specAtk = 0;
	private int specDef = 0;
	
	public StatsContainer(int[] statsArray) {
		hp = statsArray[0];
		physAtk = statsArray[1];
		physDef = statsArray[2];
		specAtk = statsArray[3];
		specDef = statsArray[4];
		speed = statsArray[5];
	}
	
	
	// set-get methods
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
			return this.hp;
		case "physAtk":
			return this.physAtk;
		case "physDef":
			return this.physDef;
		case "speed":
			return this.speed;
		case "specAtk":
			return this.specAtk;
		case "specDef":
			return this.specDef;
		
		default:
			throw new RuntimeException("Invalid stat name.");
		}
	}
	
	public int getStat(Stats statName) {
		switch (statName) {
		case Stats.HP:
			return this.hp;
		case Stats.ATK:
			return this.physAtk;
		case Stats.DEF:
			return this.physDef;
		case Stats.SPE:
			return this.speed;
		case Stats.SPA:
			return this.specAtk;
		case Stats.SPD:
			return this.specDef;
		
		default:
			throw new RuntimeException("Invalid stat name.");
		}
	}
	
	
	//for stats modifiers
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
	
	// overloaded method
	public void setStat(Stats statName, int value) {
		switch (statName) {
			case Stats.HP:
				hp = value;
				break;
			case Stats.ATK:
				physAtk = value;
				break;
			case Stats.DEF:
				physDef = value;
				break;
			case Stats.SPE:
				speed = value;
				break;
			case Stats.SPA:
				specAtk = value;
				break;
			case Stats.SPD:
				specDef = value;
				break;
		
		default:
			throw new RuntimeException("Invalid stat name: " + statName);
		}
	}
	
	
	public void modifyStat(Stats statName, double multiplier) {
		switch (statName) {
		case Stats.HP:
			hp *= multiplier;
			break;
		case Stats.ATK:
			physAtk *= multiplier;
			break;
		case Stats.DEF:
			physDef *= multiplier;
			break;
		case Stats.SPE:
			speed *= multiplier;
			break;
		case Stats.SPA:
			specAtk *= multiplier;
			break;
		case Stats.SPD:
			specDef *= multiplier;
			break;
		default:
			throw new RuntimeException("Invalid stat name: " + statName);
		}
	}
	
	
	//this is the Pokemon's mutable stats, the original is at PokemonSpecies
	public StatsContainer clone() {
		StatsContainer clonedStats = new StatsContainer(new int[6]);
		
		//set it to normal values first, we can always use the setter for the modifiers
		clonedStats.setHp(hp);
		clonedStats.setAtk(physAtk);
		clonedStats.setDef(physDef);
		clonedStats.setSpAtk(specAtk);
		clonedStats.setSpDef(specDef);
		clonedStats.setSpeed(speed);
		
		return clonedStats;
	}
    
    
	@Override
	public String toString() {
		return "HP = " + hp + " / ATK = " + physAtk + " / DEF = " + physDef + " / SPE = " + speed
				+ " / SPA = " + specAtk + " / SPD = " + specDef;
	}
	

    
    
    
    
    
    
    
}
