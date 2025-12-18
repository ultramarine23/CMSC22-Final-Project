package moves;

import java.util.EnumSet;

import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;

public class PyroBall extends Move {

	public PyroBall() {
		super(
				"Pyro Ball", 
				120, 
				90, 
				MoveCategory.PHYSICAL, 
				Types.FIRE, 
				0, 
				8, 
				"10% chance to burn the target. Thaws user.", 
				EnumSet.noneOf(MoveFlags.class)
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
		} else if (user.getCurStatus() == Status.FREEZE) {
			user.applyStatus(Status.NONE);
		}

	}

}
