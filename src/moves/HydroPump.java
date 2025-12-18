package moves;

import java.util.EnumSet;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Types;
import pokemon.Pokemon;

public class HydroPump extends Move {

	public HydroPump() {
		super(
				"Hydro Pump", 
				110, 
				80, 
				MoveCategory.SPECIAL,
				Types.WATER, 
				0, 
				8, 
				"No additional effect.", 
				EnumSet.noneOf(MoveFlags.class));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// do nothing

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// do nothing

	}

}
