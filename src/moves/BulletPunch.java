package moves;

import java.util.EnumSet;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Types;
import pokemon.Pokemon;

public class BulletPunch extends Move {

	public BulletPunch() {
		super(
				"Bullet Punch", 
				40, 
				100, 
				MoveCategory.PHYSICAL, 
				Types.STEEL, 
				1,
				16, 
				"The user strikes the target with tough punches as fast as bullets. This move always goes first.", 
				EnumSet.of(MoveFlags.CONTACT_MOVE)
			);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
	
	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		
	}

}
