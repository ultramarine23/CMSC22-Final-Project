package main;

import main.Globals.Types;
import main.Globals.Weather;
import moves.Move;
import pokemon.Pokemon;

import java.util.Map;
import java.util.HashMap;


public class DamageCalculator {
	public static final Map<String, Double> typeEffectivenessMap = new HashMap<>();
	
	static {
		// Task 0001 : Put the entire type chart into this hashmap
		typeEffectivenessMap.put("FIRE_POISON", 10.0);
	}
	
	public static int calculateDamage(Pokemon user, Pokemon target, Move move, BattleContext context) {
		// first calculate main damage using atk/def and move base power
		float targetDef = 0;
		float userAtk = 0;
		
		if (move.getMoveCategory() == Globals.MoveCategory.PHYSICAL) {
			targetDef = target.getCurrentStats().getDef();
			userAtk = user.getCurrentStats().getAtk();
		} else if (move.getMoveCategory() == Globals.MoveCategory.SPECIAL) {
			targetDef = target.getCurrentStats().getSpDef();
			userAtk = user.getCurrentStats().getSpAtk();
		} else {
			return 0; // return 0 damage if status move
		}
		
		float atkdefRatio = userAtk / targetDef;
		float mainDamage = ((42 * move.getBasePower() * atkdefRatio) / 50) + 2;
		
		
		// apply weather modifiers to the damage
		if (context.getCurBattle().getWeather() == Weather.RAIN) {
			if (move.getMoveType() == Types.FIRE) {
				mainDamage /= 2;
			} else if (move.getMoveType() == Types.WATER) {
				mainDamage *= 1.5;
			}
		}
		else if (context.getCurBattle().getWeather() == Weather.SUN) {
			if (move.getMoveType() == Types.WATER) {
				mainDamage /= 2;
			} else if (move.getMoveType() == Types.FIRE) {
				mainDamage *= 1.5;
			}
		}
		
		
		// apply STAB modifiers to the damage
		if (user.getType1() == move.getMoveType() || user.getType2() == move.getMoveType()) {
			mainDamage *= 1.5;
		}
		
		
		// apply random modifier to the damage
		mainDamage *= (double) Globals.randomEngine.nextFloat(0.85f, 1.0f);
		
		
		// apply type effectiveness modifier to the damage
		String typePairStr1 = move.getMoveType().toString() + "_" + target.getType1().toString();
		mainDamage *= typeEffectivenessMap.getOrDefault(typePairStr1, 1.0);
		
		// !@! TBA: apply burn modifier to the damage
		
		
		// !@! TBA: apply other modifiers (life orb, expert belt, etc.) to the damage
		
		
		return (int) mainDamage;
	}
}
