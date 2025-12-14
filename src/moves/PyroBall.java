package moves;

import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;

public class PyroBall extends Move {

	public PyroBall(String name, int basePower, int accuracy, MoveCategory moveCategory, Types moveType, int priority,
			int pp, String description, boolean affectedByProtect, boolean makesContact) {
		super("Pyro Ball", 0, 85, MoveCategory.STATUS, Types.FIRE, 0, 24, "10% chance to burn the target. Thaws user.", true,
				false);
		// TODO Auto-generated constructor stub
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
