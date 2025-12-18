package moves;

import java.util.EnumSet;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;
import main.Globals;

public class IceBeam extends Move {

	public IceBeam() {
		super(
				"Ice Beam", 
				90, 
				100, 
				MoveCategory.SPECIAL,
				Types.ICE, 
				0, 
				16, 
				"10% chance to freeze the target.", 
				EnumSet.noneOf(MoveFlags.class)
			);
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		if (Globals.randomEngine.nextDouble() < 0.1) {
			target.applyStatus(Status.FREEZE);
		}
	}

}
