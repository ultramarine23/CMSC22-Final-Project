package moves;

import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;

public class BodySlam extends Move {	
	public BodySlam() {
		super(
			"Body Slam", // name
			85, // basePower
			100, // accuracy
			MoveCategory.PHYSICAL, // moveCategory
			Types.NORMAL, // moveType
			0, // priority
			24, // pp
			"30% chance to paralyze the opponent", // description
			true, // affectedByProtect
			true // makesContact
		);
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// do nothing
	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		if (Globals.randomEngine.nextDouble() < 0.3) {
			target.applyStatus(Status.PARALYSIS);
		}
	}
}
