package moves;



import java.util.EnumSet;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Types;
import pokemon.Pokemon;

public class Explosion extends Move {

	public Explosion() {
		super(
				"Explosiom", 
				250, 
				100, 
				MoveCategory.PHYSICAL, 
				Types.NORMAL, 
				0, 
				5, 
				"Self-destruct inflicts damage and causes the user to faint.", 
				EnumSet.noneOf(MoveFlags.class)
			);
		//Does not make contact
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// do nothing

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		user.takeDamage(user.getCurrentStats().getHp());
	}

}
