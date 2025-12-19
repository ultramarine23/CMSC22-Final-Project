package moves;


import java.util.EnumSet;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Stats;
import main.Globals.Types;
import pokemon.Pokemon;
import main.Globals;

public class DracoMeteor extends Move {

	public DracoMeteor() {
		super(
				"Draco Meteor", 
				130, 
				90, 
				MoveCategory.SPECIAL, 
				Types.DRAGON, 
				0, 
				5, 
				"Draco Meteor deals damage but lowers the user's Special Attack by two stages after attacking.", 
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
			target.incrementStatStage(Stats.SPA, -6);
		
	}

}
