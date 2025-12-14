package moves;

import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;

public class Blizzard extends Move {

	public Blizzard(String name, int basePower, int accuracy, MoveCategory moveCategory, Types moveType, int priority,
			int pp, String description, boolean affectedByProtect, boolean makesContact) {
		super(name, 110, 70, MoveCategory.SPECIAL, Types.ICE, 0, 8, "10% chance to freeze foe(s). Can't miss in Snow.", true,
				false);
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
