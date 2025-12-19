package moves;

import main.Globals.MoveFlags;
import java.util.EnumSet;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.Types;
import pokemon.Pokemon;

public class Earthquake extends Move {

	public Earthquake() {
		super("Earthquake", 
				100, 
				100, 
				MoveCategory.PHYSICAL, 
				Types.GROUND, 
				0, 
				10, 
				"Earthquake deals damage", 
				EnumSet.noneOf(MoveFlags.class));
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		//do nothing

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		//items not implemented yet
	}

}
