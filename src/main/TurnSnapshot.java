package main;

import java.util.HashMap;
import java.util.Map;

import pokemon.Pokemon;
import pokemon.TurnIntent;

public class TurnSnapshot {
	// turn snapshot is set by the BattleInstance every start of turn, and is immutable
	// it keeps track of static data that describes the turn such as chosen moves and
	// effective speed per pokemon
	
	private final TurnIntent[] turnOrder;
	private final Map<Pokemon, Integer> effectiveSpeeds;
	private final Map<Pokemon, TurnIntent> intentsMap;
	
	public TurnSnapshot(TurnIntent[] turnOrder) {
		this.turnOrder = turnOrder;
		this.effectiveSpeeds = new HashMap<Pokemon, Integer>();
		this.intentsMap = new HashMap<Pokemon, TurnIntent>();
		
		for (TurnIntent intent : turnOrder) {
			Pokemon user = intent.getUser();
			
			this.effectiveSpeeds.put(user, Integer.valueOf(intent.getEffectiveSpeed()));
			this.intentsMap.put(user, intent);
		}
	}
	
	public Map<Pokemon, Integer> getEffectiveSpeeds() { return effectiveSpeeds; }
	public Map<Pokemon, TurnIntent> getIntentsMap() { return intentsMap; }
	public TurnIntent[] getTurnOrder() { return turnOrder; }
	
}
