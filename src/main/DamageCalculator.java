package main;

import moves.Move;
import pokemon.Pokemon;

public class DamageCalculator {
	public static int calculateDamage(Pokemon user, Pokemon target, Move move) {
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
		
		return (int) mainDamage;
	}
}
