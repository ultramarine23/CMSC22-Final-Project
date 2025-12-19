package abilities;


import main.BattleContext;
import pokemon.Pokemon;
import pokemon.TurnIntent;
import main.Globals.Status;

public class Limber extends Ability {

	public Limber() {
		super("Limber", Event.AFTER_GET_HIT);
		// TODO Auto-generated constructor stub
	}

	//The Pokémon's limber body prevents it from being paralyzed.
	@Override
	public void trigger(BattleContext btx, Pokemon user, Pokemon target) {
		TurnIntent userIntent = btx.getSnapshot().getIntentsMap().get(user); //get the intent of attacker
		Pokemon attacked = userIntent.getTarget();
		
		if (userIntent.getTarget().getCurStatus() == Status.PARALYSIS){
			//make the attacked poke remove the paralysis
			attacked.applyStatus(Status.NONE);
			System.out.println(this.getName()+" passive is triggered");
		}
		

	}

}
