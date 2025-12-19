package abilities;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Status;
import pokemon.Pokemon;
import pokemon.TurnIntent;

public class Static extends Ability {

	public Static() {
		super("Static", Event.AFTER_GET_HIT);
		// TODO Auto-generated constructor stub
	}
	//The Pokémon is charged with static electricity and may paralyze attackers that make direct contact with it.
	@Override
	public void trigger(BattleContext btx, Pokemon user, Pokemon target) {
		TurnIntent userIntent = btx.getSnapshot().getIntentsMap().get(user); //get the intent of attacker
		Pokemon attacker = userIntent.getUser();
		Pokemon defender = userIntent.getTarget();
		//paralyze the attacker
		if (userIntent.getMove().getFlags().contains(MoveFlags.CONTACT_MOVE)) {
			attacker.applyStatus(Status.PARALYSIS);
			System.out.println(this.getName()+" passive is triggered");
		}
		

	}

}
