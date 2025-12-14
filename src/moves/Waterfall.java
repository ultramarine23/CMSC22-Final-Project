package moves;



import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.Types;
import pokemon.Pokemon;

public class Waterfall extends Move {

	public Waterfall(String name, int basePower, int accuracy, MoveCategory moveCategory, Types moveType, int priority,
			int pp, String description, boolean affectedByProtect, boolean makesContact) {
		super("Waterfall", 80, 100, MoveCategory.PHYSICAL, Types.WATER, 80, 24, "20% chance to make the target flinch.", true,
				true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// do nothing

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		//set flinch to true
		if (Globals.randomEngine.nextDouble() < 0.2) {
			target.setFlinched(true);
		}

	}

}
