package moves;

import main.BattleInstance;
import main.Globals.MoveCategory;
import main.Globals.Types;
import pokemon.Pokemon;

public class Ember extends Move {
	// constructor variables
	private static String name = "Ember";
	private static int basePower = 40;
	private static int accuracy = 100;
	private static MoveCategory moveCategory = MoveCategory.SPECIAL;
	private static Types moveType = Types.FIRE;
	private static int priority = 0;
	private static int pp = 32;
	private static String description = "No additional effect.";
	private static boolean affectedByProtect = true;
	private static boolean makesContact = false;
	
	public Ember() {
		super(name, basePower, accuracy, moveCategory, moveType, priority,
			pp, description, affectedByProtect, makesContact);
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleInstance battleInst) {}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleInstance battleInst) {}

	
}
