package moves;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;

public class ThunderWave extends Move {

	public ThunderWave(String name, int basePower, int accuracy, MoveCategory moveCategory, Types moveType,
			int priority, int pp, String description, boolean affectedByProtect, boolean makesContact) {
		super("Thunder Wave", 0, 90, MoveCategory.STATUS, Types.ELECTRIC, 0, 32, "Paralyzes the target.", true,
				false);
		// TODO Auto-generated constructor stub
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
