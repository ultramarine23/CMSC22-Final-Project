package abilities;



import main.BattleContext;
import main.Globals.Stats;
import main.Globals.Weather;
import pokemon.Pokemon;
import pokemon.TurnIntent;

public class Chlorophyll extends Ability {

	public Chlorophyll(String newName, Event newTriggerEvent) {
		super("Chlorophyll", Event.BEFORE_ATTACK);
		// TODO Auto-generated constructor stub
	}
	
	//Boosts the Pokémon's Speed stat in harsh sunlight. 
	@Override
	public void trigger(BattleContext btx, Pokemon user, Pokemon target) {
		TurnIntent userIntent = btx.getSnapshot().getIntentsMap().get(user); //get the intent of attacker
		Pokemon attacker = userIntent.getUser();
		Pokemon defender = userIntent.getTarget();
		
		if (userIntent.getContext().getCurBattle().getWeather() == Weather.SUN) {
			userIntent.getUser().incrementStatStage(Stats.SPE, 1); //increases speed by 1
			System.out.println(this.getName()+" passive is triggered");
		}


	}

}
