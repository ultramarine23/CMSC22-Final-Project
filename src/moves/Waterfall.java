package moves;



import java.util.EnumSet;

import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Types;
import pokemon.Pokemon;

public class Waterfall extends Move {

	public Waterfall() {
		super(
				"Waterfall", 
				80, 
				100, 
				MoveCategory.PHYSICAL, 
				Types.WATER, 
				0, 
				24, 
				"20% chance to make the target flinch.", 
				EnumSet.noneOf(MoveFlags.class)
			);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// do nothing

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		//set flinch to true

	}

}
