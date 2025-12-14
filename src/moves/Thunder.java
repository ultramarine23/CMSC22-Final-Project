package moves;

import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;

public class Thunder extends Move {

	public Thunder(String name, int basePower, int accuracy, MoveCategory moveCategory, Types moveType, int priority,
			int pp, String description, boolean affectedByProtect, boolean makesContact) {
		super("Thunder", 110, 70, MoveCategory.SPECIAL, Types.ELECTRIC, 0, 16, "30% chance to paralyze. Can't miss in rain.", true,
				true);
		// TODO Auto-generated constructor stub
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
