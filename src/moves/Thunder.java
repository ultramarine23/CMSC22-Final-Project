package moves;

import java.util.EnumSet;

import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;

public class Thunder extends Move {

	public Thunder() {
		super(
				"Thunder", 
				110, 
				70, 
				MoveCategory.SPECIAL, 
				Types.ELECTRIC, 
				0, 
				16, 
				"30% chance to paralyze. Can't miss in rain.", 
				EnumSet.noneOf(MoveFlags.class)
			);
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		if (Globals.randomEngine.nextDouble() < 0.3) {
			target.applyStatus(Status.PARALYSIS);
		}
	}

}
