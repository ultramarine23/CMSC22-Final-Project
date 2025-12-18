package moves;

import main.Globals.MoveFlags;
import java.util.EnumSet;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.Types;
import pokemon.Pokemon;

public class AquaTail extends Move {

	public AquaTail() {
		super(
				"Aqua Tail",
				90, 
				90, 
				MoveCategory.PHYSICAL, 
				Types.WATER, 
				0, 
				16,
				"No additional effect.", 
				EnumSet.of(MoveFlags.CONTACT_MOVE)
				);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		//do nothing
	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		//do nothing

	}

}
