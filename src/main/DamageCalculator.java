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
		typeEffectivenessMap.put("FIRE_FIRE", 0.5);
		typeEffectivenessMap.put("FIRE_WATER", 0.5);
		typeEffectivenessMap.put("FIRE_GRASS", 2.0);
		typeEffectivenessMap.put("FIRE_ICE", 2.0);
		typeEffectivenessMap.put("FIRE_BUG", 2.0);
		typeEffectivenessMap.put("FIRE_ROCK", 0.5);
		typeEffectivenessMap.put("FIRE_DRAGON", 0.5);
		typeEffectivenessMap.put("FIRE_STEEL", 2.0);
		typeEffectivenessMap.put("NORMAL_ROCK", 0.5);
		typeEffectivenessMap.put("NORMAL_GHOST", 0.0);
		typeEffectivenessMap.put("NORMAL_STEEL", 0.5);
		typeEffectivenessMap.put("WATER_FIRE", 2.0);
		typeEffectivenessMap.put("WATER_WATER", 0.5);
		typeEffectivenessMap.put("WATER_GRASS", 0.5);
		typeEffectivenessMap.put("WATER_GROUND", 2.0);
		typeEffectivenessMap.put("WATER_ROCK", 2.0);
		typeEffectivenessMap.put("WATER_DRAGON", 0.5);
		typeEffectivenessMap.put("WATER_STEEL", 0.5);
		typeEffectivenessMap.put("GRASS_FIRE", 0.5);
		typeEffectivenessMap.put("GRASS_WATER", 2.0);
		typeEffectivenessMap.put("GRASS_GRASS", 0.5);
		typeEffectivenessMap.put("GRASS_POISON", 0.5);
		typeEffectivenessMap.put("GRASS_GROUND", 2.0);
		typeEffectivenessMap.put("GRASS_FLYING", 0.5);
		typeEffectivenessMap.put("GRASS_BUG", 0.5);
		typeEffectivenessMap.put("GRASS_ROCK", 2.0);
		typeEffectivenessMap.put("GRASS_DRAGON", 0.5);
		typeEffectivenessMap.put("GRASS_STEEL", 0.5);
		typeEffectivenessMap.put("ELECTRIC_WATER", 2.0);
		typeEffectivenessMap.put("ELECTRIC_GRASS", 0.5);
		typeEffectivenessMap.put("ELECTRIC_ELECTRIC", 0.5);
		typeEffectivenessMap.put("ELECTRIC_GROUND", 0.0);
		typeEffectivenessMap.put("ELECTRIC_FLYING", 2.0);
		typeEffectivenessMap.put("ELECTRIC_GHOST", 0.5);
		typeEffectivenessMap.put("ELECTRIC_DRAGON", 0.5);
		typeEffectivenessMap.put("ICE_FIRE", 0.5);
		typeEffectivenessMap.put("ICE_WATER", 0.5);
		typeEffectivenessMap.put("ICE_GRASS", 2.0);
		typeEffectivenessMap.put("ICE_ICE", 0.5);
		typeEffectivenessMap.put("ICE_GROUND", 2.0);
		typeEffectivenessMap.put("ICE_FLYING", 2.0);
		typeEffectivenessMap.put("ICE_DRAGON", 2.0);
		typeEffectivenessMap.put("ICE_STEEL", 0.5);
		typeEffectivenessMap.put("FIGHTING_NORMAL", 2.0);
		typeEffectivenessMap.put("FIGHTING_ICE", 2.0);
		typeEffectivenessMap.put("FIGHTING_POISON", 0.5);
		typeEffectivenessMap.put("FIGHTING_FLYING", 0.5);
		typeEffectivenessMap.put("FIGHTING_PSYCHIC", 0.5);
		typeEffectivenessMap.put("FIGHTING_BUG", 0.5);
		typeEffectivenessMap.put("FIGHTING_ROCK", 2.0);
		typeEffectivenessMap.put("FIGHTING_GHOST", 0.0);
		typeEffectivenessMap.put("FIGHTING_DARK", 2.0);
		typeEffectivenessMap.put("FIGHTING_STEEL", 2.0);
		typeEffectivenessMap.put("FIGHTING_FAIRY", 0.5);
		typeEffectivenessMap.put("POISON_GRASS", 2.0);
		typeEffectivenessMap.put("POISON_POISON", 0.5);
		typeEffectivenessMap.put("POISON_GROUND", 0.5);
		typeEffectivenessMap.put("POISON_ROCK", 0.5);
		typeEffectivenessMap.put("POISON_GHOST", 0.5);
		typeEffectivenessMap.put("POISON_STEEL", 0.0);
		typeEffectivenessMap.put("POISON_FAIRY", 2.0);
		typeEffectivenessMap.put("GROUND_FIRE", 2.0);
		typeEffectivenessMap.put("GROUND_GRASS", 0.5);
		typeEffectivenessMap.put("GROUND_ELECTRIC", 2.0);
		typeEffectivenessMap.put("GROUND_FIGHTING", 2.0);
		typeEffectivenessMap.put("GROUND_POISON", 2.0);
		typeEffectivenessMap.put("GROUND_FLYING", 0.0);
		typeEffectivenessMap.put("GROUND_BUG", 0.5);
		typeEffectivenessMap.put("GROUND_ROCK", 2.0);
		typeEffectivenessMap.put("GROUND_STEEL", 2.0);
		typeEffectivenessMap.put("PSYCHIC_POISON", 2.0);
		typeEffectivenessMap.put("PSYCHIC_FIGHTING", 2.0);
		typeEffectivenessMap.put("PSYCHIC_PSYCHIC", 0.5);
		typeEffectivenessMap.put("PSYCHIC_DARK", 0.0);
		typeEffectivenessMap.put("PSYCHIC_STEEL", 0.5);
		typeEffectivenessMap.put("BUG_FIRE", 0.5);
		typeEffectivenessMap.put("BUG_GRASS", 2.0);
		typeEffectivenessMap.put("BUG_FIGHTING", 0.5);
		typeEffectivenessMap.put("BUG_FLYING", 0.5);
		typeEffectivenessMap.put("BUG_PSYCHIC", 2.0);
		typeEffectivenessMap.put("BUG_ROCK", 0.5);
		typeEffectivenessMap.put("BUG_DARK", 2.0);
		typeEffectivenessMap.put("BUG_GHOST", 0.5);
		typeEffectivenessMap.put("BUG_STEEL", 0.5);
		typeEffectivenessMap.put("BUG_FAIRY", 0.5);
		typeEffectivenessMap.put("ROCK_FIRE", 2.0);
		typeEffectivenessMap.put("ROCK_ICE", 2.0);
		typeEffectivenessMap.put("ROCK_FIGHTING", 0.5);
		typeEffectivenessMap.put("ROCK_GROUND", 0.5);
		typeEffectivenessMap.put("ROCK_FLYING", 2.0);
		typeEffectivenessMap.put("ROCK_BUG", 2.0);
		typeEffectivenessMap.put("ROCK_STEEL", 0.5);
		typeEffectivenessMap.put("GHOST_NORMAL", 0.0);
		typeEffectivenessMap.put("GHOST_PSYCHIC", 2.0);
		typeEffectivenessMap.put("GHOST_GHOST", 2.0);
		typeEffectivenessMap.put("GHOST_DARK", 0.5);
		typeEffectivenessMap.put("DARK_FIGHTING", 0.5);
		typeEffectivenessMap.put("DARK_PSYCHIC", 2.0);
		typeEffectivenessMap.put("DARK_GHOST", 2.0);
		typeEffectivenessMap.put("DARK_DARK", 0.5);
		typeEffectivenessMap.put("DARK_FAIRY", 0.5);
		typeEffectivenessMap.put("DRAGON_DRAGON", 2.0);
		typeEffectivenessMap.put("DRAGON_STEEL", 0.5);
		typeEffectivenessMap.put("DRAGON_FAIRY", 0.0);
		typeEffectivenessMap.put("STEEL_FIRE", 0.5);
		typeEffectivenessMap.put("STEEL_WATER", 0.5);
		typeEffectivenessMap.put("STEEL_ELECTRIC", 0.5);
		typeEffectivenessMap.put("STEEL_ICE", 2.0);
		typeEffectivenessMap.put("STEEL_ROCK", 2.0);
		typeEffectivenessMap.put("STEEL_STEEL", 0.5);
		typeEffectivenessMap.put("STEEL_FAIRY", 2.0);
		typeEffectivenessMap.put("FAIRY_FIRE", 0.5);
		typeEffectivenessMap.put("FAIRY_FIGHTING", 2.0);
		typeEffectivenessMap.put("FAIRY_POISON", 0.5);
		typeEffectivenessMap.put("FAIRY_DRAGON", 2.0);
		typeEffectivenessMap.put("FAIRY_DARK", 2.0);
		typeEffectivenessMap.put("FAIRY_STEEL", 0.5);
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
