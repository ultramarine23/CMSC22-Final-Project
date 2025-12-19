package abilities;



import main.BattleContext;
import main.Globals.Status;
import main.Globals.Types;
import pokemon.Pokemon;
import pokemon.TurnIntent;

public class Insomnia extends Ability {

	public Insomnia() {
		super("Insomnia", Event.AFTER_GET_HIT);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void trigger(BattleContext btx, Pokemon user, Pokemon target) {
		Types type = btx.getSnapshot().getIntentsMap().get(target).getMove().getMoveType();
		TurnIntent curIntent= btx.getSnapshot().getIntentsMap().get(target);
		
		if (curIntent.getTarget().getCurStatus() == Status.SLEEP) {
			curIntent.getTarget().applyStatus(Status.NONE);
		}

	}

}
