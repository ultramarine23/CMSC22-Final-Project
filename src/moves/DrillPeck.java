package moves;

import java.util.EnumSet;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Types;
import pokemon.Pokemon;

public class DrillPeck extends Move {

	public DrillPeck() {
		super(
				"Drill Peck", 
				80, 
				100, 
				MoveCategory.PHYSICAL, 
				Types.FLYING, 
				0, 
				32, 
				"No additional effect.", 
				EnumSet.of(MoveFlags.CONTACT_MOVE));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		//do nothing

	}

}
