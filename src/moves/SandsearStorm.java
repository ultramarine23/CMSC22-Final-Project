package moves;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.Types;
import pokemon.Pokemon;

public class SandsearStorm extends Move {

	public SandsearStorm(String name, int basePower, int accuracy, MoveCategory moveCategory, Types moveType,
			int priority, int pp, String description, boolean affectedByProtect, boolean makesContact) {
		super("Sandsear Storm", 100, 80, MoveCategory.SPECIAL, Types.GROUND, 0, 16, "20% chance to burn foe(s). Can't miss in rain.", true,
				false);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// TODO Auto-generated method stub

	}

}
