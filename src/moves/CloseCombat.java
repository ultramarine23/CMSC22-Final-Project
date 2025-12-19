package moves;


import java.util.EnumSet;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Stats;
import main.Globals.Types;
import pokemon.Pokemon;
import pokemon.StatsContainer;

public class CloseCombat extends Move {

	public CloseCombat() {
		super(
				"Close Combat", 
				120, 
				100, 
				MoveCategory.PHYSICAL, 
				Types.FIGHTING, 
				0, 
				8, 
				"Lowers the user's Defense and Sp. Def by 1.", 
				EnumSet.noneOf(MoveFlags.class)
			);
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		//lower the def and sdef by 1
		user.incrementStatStage(Stats.DEF, -1);
		int defMod = user.getStatMod(Stats.DEF);
		user.applyModStat(Stats.DEF, defMod);
		
		user.incrementStatStage(Stats.SPD, 1);
		int spdMod = user.getStatMod(Stats.SPD);
		user.applyModStat(Stats.SPD, spdMod);
		

	}

}
