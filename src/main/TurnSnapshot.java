package main;

import java.util.Map;

import pokemon.Pokemon;
import pokemon.TurnIntent;

public class TurnSnapshot {
	// turn snapshot is set by the BattleInstance every start of turn, and is immutable
	// it keeps track of static data that describes the turn such as chosen moves and
	// effective speed per pokemon
	private final Map<Pokemon, Integer> effectiveSpeeds;
	private final Map<Pokemon, TurnIntent> intents;
	
	public TurnSnapshot(Map<Pokemon, Integer> effectiveSpeeds, Map<Pokemon, TurnIntent> intents) {
		this.effectiveSpeeds = effectiveSpeeds;
		this.intents = intents;
	}
	
	
	public static TurnSnapshot fromBattle(BattleInstance battle) {
		// factory method that creates a turn snapshot object from current battle state
		TurnSnapshot newSnapshot = new TurnSnapshot();
		
		return newSnapshot;
	}
}
