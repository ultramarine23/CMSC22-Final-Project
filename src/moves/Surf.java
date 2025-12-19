package moves;

import java.util.EnumSet;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Types;
import pokemon.Pokemon;

public class Surf extends Move {	
	public Surf() {
		super(
			"Surf", // name
			90, // basePower
			100, // accuracy
			MoveCategory.SPECIAL, // moveCategory
			Types.WATER, // moveType
			0, // priority
			11, // pp
			"A flush of water hits the enemy", // description
			EnumSet.noneOf(MoveFlags.class) // flags
		);
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// do nothing
	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// do nothing
	}
}
