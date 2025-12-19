package moves;

import main.Globals.MoveFlags;
import main.Globals.Stats;
import main.Globals.Status;

import java.util.EnumSet;

import main.BattleContext;
import pokemon.Pokemon;
import pokemon.StatsContainer;
import main.Globals.MoveCategory;
import main.Globals.Types;


public class Hypnosis extends Move {
	public Hypnosis() {
		super(
				"Hypnosis", 
				0, 
				100, 
				MoveCategory.STATUS, 
				Types.PSYCHIC, 
				0, 
				20, 
				"Puts the opponent to sleep", 
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
		target.applyStatus(Status.SLEEP);
	}

}
