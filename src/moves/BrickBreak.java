package moves;

import main.Globals.MoveFlags;
import java.util.EnumSet;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.Types;
import pokemon.Pokemon;

public class BrickBreak extends Move {

	public BrickBreak() {
		super("Brick Break", 
				75, 
				100, 
				MoveCategory.PHYSICAL, 
				Types.FIGHTING, 
				0, 
				24, 
				"Destroys screens, unless the target is immune.", 
				EnumSet.of(MoveFlags.BREAKS_SCREENS)
				);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		//do nothing

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		//if the target pokemon target pokemon uses screen, allow damage to pass through

	}

}
