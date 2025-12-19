package moves;



import java.util.EnumSet;

import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Types;
import pokemon.Pokemon;

public class Self_Destruct extends Move {

	public Self_Destruct() {
		super(
				"Self-Destruct", 
				200, 
				100, 
				MoveCategory.PHYSICAL, 
				Types.NORMAL, 
				0, 
				5, 
				"Self-destruct inflicts damage and causes the user to faint.", 
				EnumSet.noneOf(MoveFlags.class)
			);
		// TODO Auto-generated constructor stub
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
