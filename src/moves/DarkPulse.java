package moves;

import java.util.EnumSet;

import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;

public class DarkPulse extends Move {	
	public DarkPulse() {
		super(
			"Dark Pulse", // name
			80, // basePower
			100, // accuracy
			MoveCategory.SPECIAL, // moveCategory
			Types.DARK, // moveType
			0, // priority
			24, // pp
			"20% chance to flinch the opponent", // description
			EnumSet.noneOf(MoveFlags.class) // flags
		);
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// do nothing
	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		if (Globals.randomEngine.nextDouble() < 0.2) {
			target.setFlinched(true);
		}
		
	}
}
