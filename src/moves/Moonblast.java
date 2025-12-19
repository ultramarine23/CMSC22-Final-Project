package moves;


import java.util.EnumSet;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Stats;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;
import pokemon.StatsContainer;
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
				15, 
				"Blast from the moon that may lower the target's special attack", 
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
        StatsContainer stats = user.getCurrentStats();
		stats.setAtk(stats.getSpAtk() - 1);

	}

}
