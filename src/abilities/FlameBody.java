package abilities;



import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;
import pokemon.TurnIntent;
import main.Globals;

public class FlameBody extends Ability {

	public FlameBody() {
		super("Flame Body", Event.AFTER_GET_HIT);
		// TODO Auto-generated constructor stub
	}
	
	//Contact with the Pokémon may burn the attacker.  
	@Override
	public void trigger(BattleContext btx, Pokemon user, Pokemon target) {
		Types type = btx.getSnapshot().getIntentsMap().get(target).getMove().getMoveType(); 
		TurnIntent userIntent = btx.getSnapshot().getIntentsMap().get(user); //get the intent of attacker
		
		//get attacker moveType
		if (userIntent.getMove().getMoveCategory() == MoveCategory.PHYSICAL) { //if the attacker move category is physical
			if (Globals.randomEngine.nextDouble() < 1) { //0.33 chance of burn
				userIntent.getUser().applyStatus(Status.BURN);//apply the status to the attacker
				System.out.println(this.getName()+" passive is triggered");
			}
		}
	}

}
