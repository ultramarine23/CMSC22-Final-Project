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
			int modifier = statChanges.get(stat);
			double multiplier;
			
			if (modifier > 0) {
				multiplier = ((2 + modifier) / 2);
			} else if (modifier < 0) {
				multiplier = (2 / (2 + modifier));
			} else {
				multiplier = 1.0;
			}
			
			currentStats.setStat(stat, (int)(baseStats.getStat(stat) * multiplier));
		}
		
		// handle miscellaneous stat modifiers
		if (miscellaneousMods.contains(StatMods.PARALYSIS)) {
			currentStats.modifyStat(Stats.SPE, 0.5);
		}
		if (miscellaneousMods.contains(StatMods.BURN)) {
			currentStats.modifyStat(Stats.ATK, 0.5);
		}
	}
	
	public void addMiscStatMod(StatMods miscMod) {
		miscellaneousMods.add(miscMod);
	}
	
	public void removeMiscStatMod(StatMods miscMod) {
		miscellaneousMods.remove(miscMod);
	}
	
	public void incrementStatStage(Stats stat, int amount) {
		Integer statStage = statChanges.get(stat);
		statStage += amount;
	}
	
	public void resetStatStages() {
		this.statChanges.put(Stats.ATK, 0);
		this.statChanges.put(Stats.DEF, 0);
		this.statChanges.put(Stats.SPA, 0);
		this.statChanges.put(Stats.SPD, 0);
		this.statChanges.put(Stats.SPE, 0);
	}
	
	public StatsContainer getCurrentStats() { return currentStats; }
	
}
