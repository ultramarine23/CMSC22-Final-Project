package moves;

import java.util.EnumSet;

import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Stats;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;

public class Curse extends Move {	
	public Curse() {
		super(
			"Curse", // name
			0, // basePower
			999, // accuracy
			MoveCategory.STATUS, // moveCategory
			Types.GHOST, // moveType
			0, // priority
			16, // pp
			"-1 Speed, +1 Attack and Defense", // description
			EnumSet.noneOf(MoveFlags.class) // flags
		);
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		user.incrementStatStage(Stats.ATK, 1);
		user.incrementStatStage(Stats.DEF, 1);
		user.incrementStatStage(Stats.SPE, -1);
	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// do nothing
	}
}
