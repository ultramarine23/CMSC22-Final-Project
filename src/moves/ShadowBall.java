package moves;

import java.util.EnumSet;

import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Stats;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;

public class ShadowBall extends Move {	
	public ShadowBall() {
		super(
			"Shadow Ball", // name
			80, // basePower
			100, // accuracy
			MoveCategory.SPECIAL, // moveCategory
			Types.GHOST, // moveType
			0, // priority
			24, // pp
			"Shadow Ball deals damage and has a 20% chance of lowering the target's Special Defense by one stage.", // description
			EnumSet.noneOf(MoveFlags.class) // flags
		);
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// do nothing
	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		
        if (Globals.randomEngine.nextDouble() < 0.2) {
				target.incrementStatStage(Stats.SPA, -6);
		}
		
	}
}
