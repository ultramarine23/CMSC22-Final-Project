package moves;



import java.util.EnumSet;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Types;
import pokemon.Pokemon;

public class Counter extends Move {

	public Counter() {
		super(
				"Counter", 
				0, 
				999, 
				MoveCategory.PHYSICAL, 
				Types.FIGHTING, 
				-6, 
				8, 
				"If hit by physical attack, returns double damage.", 
				EnumSet.noneOf(MoveFlags.class)
			);
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// TASK 03	
	}

}
