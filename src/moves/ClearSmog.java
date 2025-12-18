package moves;

import java.util.EnumSet;

import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;

public class ClearSmog extends Move {	
	public ClearSmog() {
		super(
			"Clear Smog", // name
			50, // basePower
			999, // accuracy
			MoveCategory.SPECIAL, // moveCategory
			Types.POISON, // moveType
			0, // priority
			24, // pp
			"Resets all stat changes for the target.", // description
			EnumSet.noneOf(MoveFlags.class) // flags
		);
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// do nothing
	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		target.setCurrentStats(target.getBaseStats());
	}
}
