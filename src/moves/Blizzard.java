package moves;

import main.Globals.MoveFlags;
import java.util.EnumSet;

import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;

public class Blizzard extends Move {

	public Blizzard() {
		super(
				"Blizzard", 
				110, 
				70, 
				MoveCategory.SPECIAL, 
				Types.ICE, 
				0, 
				8, 
				"10% chance to freeze foe(s). Can't miss in Snow.", 
				EnumSet.noneOf(MoveFlags.class));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		if (Globals.randomEngine.nextDouble() < 0.1) {
			target.applyStatus(Status.FREEZE);
		}

	}

}
