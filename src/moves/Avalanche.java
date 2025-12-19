package moves;

import java.util.EnumSet;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Types;
import pokemon.Pokemon;

public class Avalanche extends Move {

	public Avalanche() {
		super(
				"Avalanche", 
				60, 
				100, 
				MoveCategory.PHYSICAL, 
				Types.ICE, 
				-4,
				16, 
				"Power doubles if user is damaged by the target.", 
				EnumSet.noneOf(MoveFlags.class)
			);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		if (ctx.getHistory().wasHitThisTurn(user)) {
			this.setPower(getBasePower() * 2);
		}
	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		
	}

}
