package moves;

import java.util.EnumSet;

import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;

public class Thunderbolt extends Move {

	public Thunderbolt() {
		super(
				"Thunderbolt", 
				90, 
				100, 
				MoveCategory.SPECIAL, 
				Types.ELECTRIC, 
				0, 
				24, 
				"10% chance to paralyze the target.", 
				EnumSet.noneOf(MoveFlags.class));
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		if (Globals.randomEngine.nextDouble() < 0.1) {
			target.applyStatus(Status.PARALYSIS);
		}

	}

}
