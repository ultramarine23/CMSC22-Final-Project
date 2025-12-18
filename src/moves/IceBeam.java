package moves;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;
import main.Globals;

public class IceBeam extends Move {

	public IceBeam(String name, int basePower, int accuracy, MoveCategory moveCategory, Types moveType, int priority,
			int pp, String description, boolean affectedByProtect, boolean makesContact) {
		super(name, 90, 100, MoveCategory.SPECIAL, Types.ICE, 0, 16, "10% chance to freeze the target.", affectedByProtect,
				makesContact);
		// TODO Auto-generated constructor stub
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
