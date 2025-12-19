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



public class EnergyBall extends Move {
    
	public EnergyBall() {
		super("Energy Ball", 
				90, 
				100, 
				MoveCategory.SPECIAL, 
				Types.GRASS, 
				0, 
				10, 
				"Energy Ball deals damage and has a 10% chance of lowering the target's Special Defense by one stage.", 
				EnumSet.noneOf(MoveFlags.class));
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		//do nothing

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		if (Globals.randomEngine.nextDouble() < 0.1) {
			target.incrementStatStage(Stats.SPD, -6);
		}
	}
}
