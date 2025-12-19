package abilities;



import main.BattleContext;
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

	@Override
	public void trigger(BattleContext btx, Pokemon user, Pokemon target) {
		Types type = btx.getSnapshot().getIntentsMap().get(target).getMove().getMoveType(); //move type of attacker
		TurnIntent targetIntent = btx.getSnapshot().getIntentsMap().get(target);
		TurnIntent userIntent = btx.getSnapshot().getIntentsMap().get(user);
		
		//get attacker moveType
		if (userIntent.getMove().getMoveType() == Types.FIRE) {
			if (Globals.randomEngine.nextDouble() < 1) {
				targetIntent.getUser().applyStatus(Status.BURN);
				System.out.println("Applied Flame Body Passive");
			}
		}
	
		
			
	}

}
