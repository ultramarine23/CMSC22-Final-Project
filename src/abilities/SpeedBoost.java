package abilities;


import main.BattleContext;
import main.Globals.Stats;
import main.Globals.Types;
import pokemon.Pokemon;
import pokemon.TurnIntent;

public class SpeedBoost extends Ability {


	public SpeedBoost() {
		super("Speed Boost", Event.BEFORE_ATTACK);
		// TODO Auto-generated constructor stub
	}
	
	//The Pokémon's Speed stat is boosted every turn. 
	@Override
	public void trigger(BattleContext btx, Pokemon user, Pokemon target) {
		TurnIntent userIntent = btx.getSnapshot().getIntentsMap().get(user);
		
		userIntent.getUser().incrementStatStage(Stats.SPE, 1); //increases speed by 1
		System.out.println(this.getName()+" passive is triggered");
		

	}

}
