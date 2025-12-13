package moves;



import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.Types;
import pokemon.Pokemon;

public class Counter extends Move {

	public Counter(String name, int basePower, int accuracy, MoveCategory moveCategory, Types moveType, int priority,
			int pp, String description, boolean affectedByProtect, boolean makesContact) {
		super("Counter", 120, 100, MoveCategory.PHYSICAL, Types.FIGHTING, 0, 8, "If hit by physical attack, returns double damage.", true,
				true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		//inflicts counter buff
		
		
		
	}

}
