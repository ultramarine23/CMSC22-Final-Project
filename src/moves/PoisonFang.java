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


public class PoisonFang extends Move {
    	public PoisonFang() {
		super(
				"Poison Fang", 
			50, 
				100, 
				MoveCategory.PHYSICAL, 
				Types.POISON, 
				0,
				15, 
				"Poison Fang inflicts damage and has a 30% chance of badly poisoning the target.", 
				EnumSet.of(MoveFlags.CONTACT_MOVE)
			);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		//check the health of pokemon
		

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
        		if (Globals.randomEngine.nextDouble() < 0.3) {
				target.applyStatus(Status.POISON);
		}
	}

}
