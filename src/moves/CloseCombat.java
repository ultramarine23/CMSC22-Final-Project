package moves;


import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.Types;
import pokemon.Pokemon;
import pokemon.StatsContainer;

public class CloseCombat extends Move {

	public CloseCombat(String name, int basePower, int accuracy, MoveCategory moveCategory, Types moveType,
			int priority, int pp, String description, boolean affectedByProtect, boolean makesContact) {
		super("Close Combat", 120, 100, MoveCategory.PHYSICAL, Types.FIGHTING, 0, 8, "Lowers the user's Defense and Sp. Def by 1.", true,
				true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		//lower the def and sdef by 1
		StatsContainer stats = user.getCurrentStats();
		stats.setDef(stats.getDef() - 1);
		stats.setSpDef(stats.getSpDef() - 1);

	}

}
