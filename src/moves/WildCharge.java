package moves;



import java.util.EnumSet;

import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Types;
import pokemon.Pokemon;

public class WildCharge extends Move {

	public WildCharge() {
		super(
				"Wild Charge", 
				90, 
				100, 
				MoveCategory.PHYSICAL, 
				Types.ELECTRIC, 
				0, 
				15, 
				"Deals damage, but the user takes 1/4 damage in recoil.", 
				EnumSet.noneOf(MoveFlags.class)
			);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// do nothing

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		user.takeDamage(user.getCurrentStats().getHp() / 4);
	}

}
