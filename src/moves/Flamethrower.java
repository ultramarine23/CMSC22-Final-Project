package moves;

import java.util.EnumSet;

import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;

public class Flamethrower extends Move {	
	public Flamethrower() {
		super(
			"Flamethrower", // name
			90, // basePower
			100, // accuracy
			MoveCategory.SPECIAL, // moveCategory
			Types.FIRE, // moveType
			0, // priority
			32, // pp
			"10% chance to burn the opponent", // description
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
			target.applyStatus(Status.BURN);
		}
	}
}
