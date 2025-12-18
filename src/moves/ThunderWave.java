package moves;

import java.util.EnumSet;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;

public class ThunderWave extends Move {

	public ThunderWave() {
		super(
				"Thunder Wave", 
				0, 
				90, 
				MoveCategory.STATUS, 
				Types.ELECTRIC, 
				0, 
				32, 
				"Paralyzes the target.", 
				EnumSet.noneOf(MoveFlags.class)
			);
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		target.applyStatus(Status.PARALYSIS);

	}

}
