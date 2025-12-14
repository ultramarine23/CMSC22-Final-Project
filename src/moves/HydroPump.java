package moves;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.Types;
import pokemon.Pokemon;

public class HydroPump extends Move {

	public HydroPump(String name, int basePower, int accuracy, MoveCategory moveCategory, Types moveType, int priority,
			int pp, String description, boolean affectedByProtect, boolean makesContact) {
		super("Hydro Pump", 110, 80, MoveCategory.SPECIAL, Types.WATER, 0, 8, "No additional effect.", true,
				false);
		// TODO Auto-generated constructor stub
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
