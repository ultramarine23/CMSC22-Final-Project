package moves;


import java.util.EnumSet;

import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Types;
import pokemon.Pokemon;

public class Hurricane extends Move {

	public Hurricane() {
		super(
				"Hurricane", 
				110, 
				70, 
				MoveCategory.SPECIAL, 
				Types.FLYING, 
				0, 
				32, 
				"30% chance to confuse target. Can't miss in rain. ", 
				EnumSet.noneOf(MoveFlags.class)
			);
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		if (Globals.randomEngine.nextDouble() < 0.3){
			
		}

	}

}
