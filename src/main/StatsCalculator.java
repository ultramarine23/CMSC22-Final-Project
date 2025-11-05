package main;

import main.Globals.Stats;
import pokemon.StatsContainer;

// this class offers static methods that can be used to get the real values
// of stats, in contrast to their base stat form

public class StatsCalculator {
	public static StatsContainer realizeAllStats(StatsContainer origStats) {
		StatsContainer realizedStats = new StatsContainer(new int[] {0, 0, 0, 0, 0, 0});
		
		realizedStats.setHp(realizeStat(Stats.HP, origStats.getHp()));
		realizedStats.setAtk(realizeStat(Stats.ATK, origStats.getAtk()));
		realizedStats.setDef(realizeStat(Stats.DEF, origStats.getDef()));
		realizedStats.setSpAtk(realizeStat(Stats.SPA, origStats.getSpAtk()));
		realizedStats.setSpDef(realizeStat(Stats.SPD, origStats.getSpDef()));
		realizedStats.setSpeed(realizeStat(Stats.SPE, origStats.getSpeed()));
		
		return realizedStats;
	}
	
	public static int realizeStat(Stats stat, int value) {
		float realizedValue = value;
		
		if (stat == Stats.HP) {
			realizedValue = ((2 * realizedValue) + 31) + 100 + 10;
		} else {
			realizedValue = (((2 * realizedValue) + 31) + 5);
		}
		
		return (int) realizedValue;
	}
}
