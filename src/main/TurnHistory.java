package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import pokemon.Pokemon;

public class TurnHistory {
	/* turn history is a mutable data container set by the facade BattleContext throughout
	 * the turn, keeping track of essential details that have happened this turn like  
	 */
	
	private final Set<Pokemon> wasHit = new HashSet<Pokemon>();
	private final Map<Pokemon, Integer> damagesTaken = new HashMap<Pokemon, Integer>();
	
	public boolean wasHitThisTurn(Pokemon poke) {
		return wasHit.contains(poke);
	}
	
	public int damageTakenThisTurn(Pokemon poke) {
		return damagesTaken.get(poke).intValue();
	}
	
	// package-private mutating functions to prevent pokemon from being able to modify
	void _registerHit(Pokemon poke) {
		wasHit.add(poke);
	}
	
	void _addDamage(Pokemon poke, int amount) {
		if (damagesTaken.containsKey(poke)) {
			damagesTaken.put(poke, damagesTaken.get(poke).intValue() + amount);
		} else {
			damagesTaken.put(poke, amount);
		}
	}
}
