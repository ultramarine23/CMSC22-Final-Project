package moves;



import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.Types;
import pokemon.Pokemon;

public class Acrobatics extends Move {

	public Acrobatics() {
		super("Acrobatics", 
				55, 
				100, 
				MoveCategory.PHYSICAL, 
				Types.NORMAL, 
				0, 
				24, 
				"Power doubles if the user has no held item", 
				true,
				true);
		
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		//do nothing

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		//items not implemented yet

	}

}
