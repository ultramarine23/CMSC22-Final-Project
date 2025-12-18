package moves;



import java.util.EnumSet;

import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;

public class ScorchingSands extends Move {

	public ScorchingSands() {
		super(
				"Scorching Sands", 
				70, 
				100, 
				MoveCategory.SPECIAL, 
				Types.GROUND, 
				0, 
				16, 
				"30% chance to burn the target. Thaws target.", 
				EnumSet.noneOf(MoveFlags.class)
			);
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		if (target.getCurStatus() == Status.FREEZE) {
			target.applyStatus(Status.NONE);
		} else if (Globals.randomEngine.nextDouble() < 0.3) {
			target.applyStatus(Status.BURN);
		}
	}

}
