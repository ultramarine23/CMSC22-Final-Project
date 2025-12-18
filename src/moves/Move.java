package moves;

import pokemon.Pokemon;

import java.util.EnumSet;

import main.BattleContext;
import main.Globals.Types;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;

public abstract class Move {
	private final String name;
	private int basePower; //can be modified
	private int accuracy; //can be modified
	private final MoveCategory moveCategory;
	private final Types moveType;
	private int priority; //can be modified
	private int pp; //can be modified
	private final String description;
	private final EnumSet<MoveFlags> flags;
	
	
	public Move(String name, int basePower, int accuracy, MoveCategory moveCategory, Types moveType, int priority,
			int pp, String description, EnumSet<MoveFlags> flags) {
		this.name = name;
		this.basePower = basePower; 
		this.accuracy = accuracy;
		this.moveCategory = moveCategory;
		this.moveType = moveType;
		this.priority = priority;
		this.pp = pp;
		this.description = description;
		this.flags = flags;
	}

	public abstract void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx);
	public abstract void afterExecution(Pokemon user, Pokemon target, BattleContext ctx);

	
	// set-get methods
	public String getName() { return name; }
	public int getBasePower() { return basePower; }
	public int getAccuracy() { return accuracy; }
	public MoveCategory getMoveCategory() { return moveCategory;}
	public Types getMoveType() { return moveType; }
	public int getPriority() { return priority; }
	public int getPp() { return pp; }
	public String getDescription() { return description; }
<<<<<<< Updated upstream
	public boolean isAffectedByProtect() { return affectedByProtect; }
	public boolean makesContact() { return makesContact; }
	
	
	public void setPower(int power) {this.basePower = power;}
	public void setAccuracy(int accuracy) {this.accuracy = accuracy;}
	public void setPriority (int prio) {this.priority = prio;}
	public void setPP(int pp) {this.pp = pp;}
	 
=======
	public EnumSet<MoveFlags> getFlags() { return flags; }
>>>>>>> Stashed changes
}
