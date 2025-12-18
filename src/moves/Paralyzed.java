package moves;

import java.util.EnumSet;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Types;
import pokemon.Pokemon;

public class Paralyzed extends Move {
	// constructor variables
	private static String name = "[Paralyzed]";
	private static int basePower = 0;
	private static int accuracy = 100;
	private static MoveCategory moveCategory = MoveCategory.STATUS;
	private static Types moveType = Types.NONE;
	private static int priority = 0;
	private static int pp = 32;
	private static String description = "No additional effect.";
	private static EnumSet<MoveFlags> flags = EnumSet.noneOf(MoveFlags.class);
	
	public Paralyzed() {
		super(name, basePower, accuracy, moveCategory, moveType, priority, 
				pp, description, flags);
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// TODO Auto-generated method stub
	}
}
