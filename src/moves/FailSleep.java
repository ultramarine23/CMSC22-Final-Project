package moves;

import java.util.EnumSet;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Types;
import pokemon.Pokemon;

public class FailSleep extends Move {

	public FailSleep() {
		super("[Sleeps]", 0, 100, MoveCategory.STATUS, Types.NONE, 0, 100, "", EnumSet.noneOf(MoveFlags.class));
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		System.out.println(user.getPokemonSpecies().getName() + "is fast asleep");

	}

}
