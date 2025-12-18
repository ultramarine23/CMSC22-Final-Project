package pokemon;

public class StatsContainer {
	private int hp = 0;
	private int physAtk = 0;
	private int physDef = 0;
	private int speed = 0;
	private int specAtk = 0;
	private int specDef = 0;
	
	// modifiers for non-HP stats
    private int physAtkMod = 0;
    private int physDefMod = 0;
    private int speedMod = 0;
    private int specAtkMod = 0;
    private int specDefMod = 0;
	
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
	
	
	//used for moves that increases atk for example
	public void applyModStat(String statName, int modifier) {
		switch (statName) {
		case "physAtk":
			this.adjustAtkMod(modifier); //sets the new modifier
			this.physAtk = this.applyModifier(physAtk, this.limitMod(modifier));//calculate the modified stat
		case "physDef":
			this.adjustDefMod(modifier);
			this.physDef = applyModifier(physDef, this.limitMod(modifier));
		case "speed":
			this.adjustSpeedMod(modifier);
			this.speed = applyModifier(speed, this.limitMod(modifier));
		case "specAtk":
			this.adjustSpAtkMod(modifier);
			this.specAtk = applyModifier(specAtk, this.limitMod(modifier));
		case "specDef":
			this.adjustSpDefMod(modifier);
			this.specDef = applyModifier(specDef, this.limitMod(modifier));
		
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

	// Apply modifier formula
    private int applyModifier(int stat, int mod) {
        if (mod >= 0) {
            return (int)Math.round(((2.0 + mod)/2.0) * stat);
        } else {
            return (int)Math.round((2.0 / (2.0 + mod)) * stat);
        }
    }
    
    
    //this func is to limit the modifiers to -4 to +4
    private int limitMod(int mod) {
        return Math.max(-4, Math.min(4, mod));
    }
    
    
	@Override
	public String toString() {
		return "HP = " + hp + " / ATK = " + physAtk + " / DEF = " + physDef + " / SPE = " + speed
				+ " / SPA = " + specAtk + " / SPD = " + specDef;
	}
	
	//setters for Stats Modifiers
	//this will either increment or decrement based on the sign of the parameter
    public void adjustAtkMod(int mod) { physAtkMod += this.limitMod(mod); }
    public void adjustDefMod(int mod) { physDefMod += this.limitMod(mod); }
    public void adjustSpeedMod(int mod) { speedMod += this.limitMod(mod); }
    public void adjustSpAtkMod(int mod) { specAtkMod += this.limitMod(mod); }
    public void adjustSpDefMod(int mod) { specDefMod += this.limitMod(mod); }
    
    //getters for stats modifiers
    public int getAtkMod() { return physAtkMod; }
    public int getDefMod() { return physDefMod; }
    public int getSpeedMod() { return speedMod; }
    public int getSpAtkMod() { return specAtkMod; }
    public int getSpDefMod() { return specDefMod; }

    
    
    
    
    
    
    
}
