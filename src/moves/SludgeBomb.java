package moves;

import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;

public class SludgeBomb extends Move {	
	public SludgeBomb() {
		super(
			"Sludge Bomb", // name
			90, // basePower
			100, // accuracy
			MoveCategory.SPECIAL, // moveCategory
			Types.POISON, // moveType
			0, // priority
			24, // pp
			"30% chance to poison the opponent", // description
			true, // affectedByProtect
			false // makesContact
		);
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// do nothing
	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		if (Globals.randomEngine.nextDouble() < 0.3) {
			target.applyStatus(Status.POISON);
		}
	}
}
