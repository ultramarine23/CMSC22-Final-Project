package pokemon;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import main.Globals.Stats;
import main.Globals.StatMods;

public class StatsManager {
	private final StatsContainer baseStats;
	private final StatsContainer currentStats;
	private final Map<Stats, Integer> statChanges;
	private final EnumSet<StatMods> miscellaneousMods;
	
	public StatsManager(StatsContainer baseStats) {
		this.baseStats = baseStats;
		this.currentStats = baseStats.clone();
		this.miscellaneousMods = EnumSet.noneOf(StatMods.class);
		
		this.statChanges = new HashMap<Stats, Integer>();
		this.statChanges.put(Stats.ATK, 0);
		this.statChanges.put(Stats.DEF, 0);
		this.statChanges.put(Stats.SPA, 0);
		this.statChanges.put(Stats.SPD, 0);
		this.statChanges.put(Stats.SPE, 0);
	}
	
	public void calculateStats() {
		for (Stats stat : statChanges.keySet()) {
			double modifier = (double)statChanges.get(stat);
			double multiplier;
			
			if (modifier > 0) {
				multiplier = ((2 + modifier) / 2);
			} else if (modifier < 0) {
				multiplier = (2 / (2 + modifier));
			} else {
				multiplier = 1.0;
			}
			
			currentStats.setStat(stat, (int)((double)(baseStats.getStat(stat)) * multiplier));
		}
		
		// handle miscellaneous stat modifiers
		if (miscellaneousMods.contains(StatMods.PARALYSIS)) {
			currentStats.modifyStat(Stats.SPE, 0.5);
		}
		if (miscellaneousMods.contains(StatMods.BURN)) {
			currentStats.modifyStat(Stats.ATK, 0.5);
		}
	}
	
	//used when adding status in pokemon
	public void addMiscStatMod(StatMods miscMod) {
		miscellaneousMods.add(miscMod); 
	}
	
	//used when adding status in pokemon
	public void removeMiscStatMod(StatMods miscMod) {
		miscellaneousMods.remove(miscMod);
	}
	
	//used when applying status mods to pokemon
	public void incrementStatStage(Stats stat, int amount) {
		int statStage = statChanges.get(stat).intValue() + amount; 
		statStage = this.limitMod(statStage);
		statChanges.put(stat, Integer.valueOf(statStage));
		calculateStats();
	}
	
	private int limitMod(int mod) {
        return Math.max(-6, Math.min(6, mod));
    }
	
	
	//helper method
	public void resetStatStages() {
		this.statChanges.put(Stats.ATK, 0);
		this.statChanges.put(Stats.DEF, 0);
		this.statChanges.put(Stats.SPA, 0);
		this.statChanges.put(Stats.SPD, 0);
		this.statChanges.put(Stats.SPE, 0);
	}
	
	
	//getters
	public StatsContainer getCurrentStats() {return currentStats; }
	public EnumSet<StatMods> getMiscallaneous(){return this.miscellaneousMods;}
	
	
	
	
	
}
