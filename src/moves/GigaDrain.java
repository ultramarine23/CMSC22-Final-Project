package moves;

import java.util.EnumSet;
import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Types;
import pokemon.Pokemon;
import main.TurnHistory;
public class GigaDrain extends Move {
    	public GigaDrain() {
		super(
				"Giga Drain", 
				75, 
				100, 
				MoveCategory.PHYSICAL, 
				Types.FIGHTING, 
				0, 
				16, 
				"User recovers 50% of the damage dealt.", 
				EnumSet.of(MoveFlags.HEALING_MOVE, MoveFlags.CONTACT_MOVE));
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// gain back health
		int damage = ctx.getHistory().damageTakenThisTurn(target);
		user.healHealth(damage/2);
	}

}
