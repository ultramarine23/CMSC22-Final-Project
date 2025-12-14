package moves;


import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.Types;
import pokemon.Pokemon;

public class Hurricane extends Move {

	public Hurricane(String name, int basePower, int accuracy, MoveCategory moveCategory, Types moveType, int priority,
			int pp, String description, boolean affectedByProtect, boolean makesContact) {
		super("Hurricane", 110, 70, MoveCategory.SPECIAL, Types.FLYING, 0, 32, "30% chance to confuse target. Can't miss in rain. ", true,
				false);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		if (Globals.randomEngine.nextDouble() < 0.3){
			
		}

	}

}
