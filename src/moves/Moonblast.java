package moves;


import java.util.EnumSet;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Stats;
import main.Globals.Types;
import pokemon.Pokemon;
import main.Globals;

public class Moonblast extends Move {

	public Moonblast() {
		super(
				"Moonblast", 
				95, 
				100, 
				MoveCategory.SPECIAL, 
				Types.FAIRY, 
				0, 
				24, 
				"Blast from the moon that may lower the target's special attack", 
				EnumSet.noneOf(MoveFlags.class)
			);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		if (Globals.randomEngine.nextDouble() < 0.3) {
			target.incrementStatStage(Stats.SPA, -1);
		}
	}

}
