package moves;



import java.util.EnumSet;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;


public class Facade extends Move {

	public Facade() {
		super(
				"Facade", 
				70, 
				100, 
				MoveCategory.PHYSICAL, 
				Types.NORMAL, 
				0, 
				32, 
				"Power doubles if user is burn/poison/paralyzed.", 
				EnumSet.of(MoveFlags.CONTACT_MOVE)
			);
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
