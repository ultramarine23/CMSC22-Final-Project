package moves;



import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;

public class ScorchingSands extends Move {

	public ScorchingSands(String name, int basePower, int accuracy, MoveCategory moveCategory, Types moveType,
			int priority, int pp, String description, boolean affectedByProtect, boolean makesContact) {
		super("Scorching Sands", 70, 100, MoveCategory.SPECIAL, Types.GROUND, 0, 16, "30% chance to burn the target. Thaws target.", true,
				false);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// do nothing

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		if (user.getCurStatus() == Status.FREEZE) {
			user.applyStatus(Status.NONE);
		} else if (Globals.randomEngine.nextDouble() < 0.3) {
			target.applyStatus(Status.BURN);
		}
	}

}
