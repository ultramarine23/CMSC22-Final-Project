package moves;

import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;

public class ThunderPunch extends Move {

	public ThunderPunch(String name, int basePower, int accuracy, MoveCategory moveCategory, Types moveType,
			int priority, int pp, String description, boolean affectedByProtect, boolean makesContact) {
		super("Thunder Punch", 75, 100, MoveCategory.PHYSICAL, Types.ELECTRIC, 0, 24, "10% chance to paralyze the target.", true,
				true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		if (Globals.randomEngine.nextDouble() < 0.1) {
			target.applyStatus(Status.PARALYSIS);
		}

	}

}
