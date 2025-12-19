package moves;

import java.util.EnumSet;

import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;

public class Overheat extends Move {	
	public Overheat() {
		super(
			"Overheat", // name
			130, // basePower
			90, // accuracy
			MoveCategory.SPECIAL, // moveCategory
			Types.FIRE, // moveType
			0, // priority
			5, // pp
			"Overheat deals damage but lowers the user's Special Attack by two stages after attacking", // description
			EnumSet.noneOf(MoveFlags.class) // flags
		);
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// do nothing
	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
			target.applyStatus(Status.BURN);
            
		
	}
}
