package moves;

import main.Globals.MoveFlags;
import java.util.EnumSet;

import main.BattleContext;
import pokemon.Pokemon;
import pokemon.StatsContainer;
import main.Globals.MoveCategory;
import main.Globals.Types;


public class BulkUp extends Move {
	public BulkUp() {
		super(
				"Bulk Up", 
				0, 
				100, 
				MoveCategory.STATUS, 
				Types.FIGHTING, 
				0, 
				32, 
				"Raises the user's Attack and Defense by 1.", 
				EnumSet.noneOf(MoveFlags.class)
			);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		//do nothing

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		StatsContainer stats = user.getCurrentStats();
		stats.setAtk(stats.getAtk() + 1);
		stats.setDef(stats.getDef() + 1);
		
	}

}
