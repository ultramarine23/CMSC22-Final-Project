package moves;

import java.util.EnumSet;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Types;
import pokemon.Pokemon;

public class StoneEdge extends Move {	
	public StoneEdge() {
		super(
			"Stone Edge", // name
			100, // basePower
			80, // accuracy
			MoveCategory.PHYSICAL, // moveCategory
			Types.ROCK, // moveType
			0, // priority
			8, // pp
			"High critical hit ratio.", // description
			EnumSet.of(MoveFlags.HIGH_CRIT) // flags
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
