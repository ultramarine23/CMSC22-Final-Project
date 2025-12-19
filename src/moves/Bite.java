package moves;

import java.util.EnumSet;

import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Stats;
import main.Globals.Types;
import pokemon.Pokemon;


public class Bite extends Move {
    	public Bite() {
		super(
				"Bite", 
				60, 
				100, 
				MoveCategory.PHYSICAL, 
				Types.DARK, 
				0,
				20, 
				"An attack that may cause flinching", 
				EnumSet.noneOf(MoveFlags.class)
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
				target.setFlinched(true);
		}
	}

}
