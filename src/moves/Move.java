package moves;

import pokemon.Pokemon;
import main.BattleContext;
import main.Globals.Types;
import main.Globals.MoveCategory;

public abstract class Move {
	private final String name;
	private final int basePower;
	private final int accuracy;
	private final MoveCategory moveCategory;
	private final Types moveType;
	private final int priority;
	private final int pp;
	private final String description;
	private final boolean affectedByProtect;
	private final boolean makesContact;
	
	
	public Move(String name, int basePower, int accuracy, MoveCategory moveCategory, Types moveType, int priority,
			int pp, String description, boolean affectedByProtect, boolean makesContact) {
		this.name = name;
		this.basePower = basePower;
		this.accuracy = accuracy;
		this.moveCategory = moveCategory;
		this.moveType = moveType;
		this.priority = priority;
		this.pp = pp;
		this.description = description;
		this.affectedByProtect = affectedByProtect;
		this.makesContact = makesContact;
	}

	public abstract void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx);
	public abstract void afterExecution(Pokemon user, Pokemon target, BattleContext ctx);

	
	// set-get methods
	public String getName() { return name; }
	public int getBasePower() { return basePower; }
	public int getAccuracy() { return accuracy; }
	public MoveCategory getMoveCategory() { return moveCategory; }
	public Types getMoveType() { return moveType; }
	public int getPriority() { return priority; }
	public int getPp() { return pp; }
	public String getDescription() { return description; }
	public boolean isAffectedByProtect() { return affectedByProtect; }
	public boolean makesContact() { return makesContact; }
	
}
