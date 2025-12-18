package moves;

import java.util.EnumSet;

import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;

public class SludgeWave extends Move {	
	public SludgeWave() {
		super(
			"Sludge Wave", // name
			95, // basePower
			100, // accuracy
			MoveCategory.SPECIAL, // moveCategory
			Types.POISON, // moveType
			0, // priority
			16, // pp
			"10% chance to poison the opponent", // description
			EnumSet.noneOf(MoveFlags.class) // flags
		);
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// do nothing
	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		if (Globals.randomEngine.nextDouble() < 0.1) {
			target.applyStatus(Status.POISON);
		}
	}
}
