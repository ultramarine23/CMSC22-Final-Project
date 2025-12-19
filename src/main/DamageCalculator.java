package main;

import main.Globals.MoveCategory;
import main.Globals.Status;
import main.Globals.Types;
import main.Globals.Weather;
import moves.Move;
import pokemon.Pokemon;
import abilities.Ability;
import abilities.*;

import java.util.Map;
import java.util.HashMap;



public class DamageCalculator {
	public static final Map<String, Double> typeEffectivenessMap = new HashMap<>();
	
	static {
		// Task 0001 : Put the entire type chart into this hashmap
		// First type is the attacker, second type is the defender
		typeEffectivenessMap.put("FIRE_POISON", 1.0);
		typeEffectivenessMap.put("NORMAL_POISON", 2.0);
	}
	
	//user will deal damage to target
	public static int calculateDamage(Pokemon user, Pokemon target, Move move, BattleContext context) {
  		// first calculate main damage using atk/def and move base power
		float targetDef = 0;
		float userAtk = 0;
		float basePwr = move.getBasePower();
		
		// handle burned case
		if (user.getCurStatus() == Status.BURN && move.getMoveCategory() == MoveCategory.PHYSICAL) {
			basePwr /= 2;
		}
		
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
		float mainDamage = ((42 * basePwr * atkdefRatio) / 50) + 2;
		
		
		// apply weather modifiers to the damage
		if (context.getCurBattle().getWeather() == Weather.RAIN) {
			if (move.getMoveType() == Types.FIRE) {
				mainDamage /= 2;
			} else if (move.getMoveType() == Types.WATER || move.getName() == "Hydro Steam") {
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
		
		String typePairStr2 = move.getMoveType().toString() + "_" + target.getType2().toString();
		mainDamage *= typeEffectivenessMap.getOrDefault(typePairStr2, 1.0);
		
		// !@! TBA: apply other modifiers (life orb, expert belt, etc.) to the damage
		
		
		return (int) mainDamage;
	}
}
