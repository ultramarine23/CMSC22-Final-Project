package moves;


import java.util.EnumSet;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;
import main.Globals;

public class IcePunch extends Move {

	public IcePunch() {
		super(
				"Ice Punch", 
				75, 
				100, 
				MoveCategory.PHYSICAL, 
				Types.ICE, 
				0, 
				24, 
				"Has a 10% chance of freezing the target.", 
				EnumSet.of(MoveFlags.CONTACT_MOVE)
			);
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
