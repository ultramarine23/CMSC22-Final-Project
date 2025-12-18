package moves;

import java.util.EnumSet;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;

public class WillOWisp extends Move {

	public WillOWisp() {
		super(
				"Will-O-Wisp",
				0, 
				85, 
				MoveCategory.STATUS, 
				Types.FIRE,
				0,
				24,
				"Burns the target", 
				EnumSet.noneOf(MoveFlags.class)
			);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		target.applyStatus(Status.BURN);
	}

}
