package moves;



import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;


public class Facade extends Move {

	public Facade(String name, int basePower, int accuracy, MoveCategory moveCategory, Types moveType, int priority,
			int pp, String description, boolean affectedByProtect, boolean makesContact) {
		super("Facade", 70, 100, MoveCategory.PHYSICAL, Types.NORMAL, 0, 32, "Power doubles if user is burn/poison/paralyzed.", true,
				true);
		
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		//do nothing

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		Status curStatus = user.getCurStatus();
		if ((curStatus == Status.BURN) || (curStatus == Status.POISON) || (curStatus == Status.PARALYSIS)) {
			setPower(140);
		} else {
			setPower(70);
		}
		
	}

}
