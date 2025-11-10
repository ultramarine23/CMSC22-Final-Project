package moves;

import main.BattleContext;
import main.BattleInstance;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.Types;
import pokemon.Pokemon;

public class Flamethrower extends Move {
	// constructor variables
	private static String name = "Flamethrower";
	private static int basePower = 40;
	private static int accuracy = 100;
	private static MoveCategory moveCategory = MoveCategory.SPECIAL;
	private static Types moveType = Types.FIRE;
	private static int priority = 0;
	private static int pp = 32;
	private static String description = "No additional effect.";
	private static boolean affectedByProtect = true;
	private static boolean makesContact = false;
	
	public Flamethrower() {
		super(name, basePower, accuracy, moveCategory, moveType, priority,
			pp, description, affectedByProtect, makesContact);
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// do nothing
	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		if (Globals.randomEngine.nextDouble() < 0.1) {
			
		}
		
	}

	
}
