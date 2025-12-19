package abilities;

import main.BattleContext;
import main.Globals;
import main.Globals.Status;
import pokemon.Pokemon;
import pokemon.TurnIntent;

public class EffectSpore extends Ability {

	public EffectSpore(String newName, Event newTriggerEvent) {
		super("Effect Spore", Event.AFTER_GET_HIT);
		// TODO Auto-generated constructor stub
	}
	//When a Pokémon with this Ability is hit by a move that makes contact, there is a 10% chance that the attacking Pokémon 
	//will become either poisoned, paralyzed, or asleep, with a 3.3% probability each.
	@Override
	public void trigger(BattleContext btx, Pokemon user, Pokemon target) {
		TurnIntent userIntent = btx.getSnapshot().getIntentsMap().get(user); //get the intent of attacker
		Pokemon attacker = userIntent.getUser();
		Pokemon defender = userIntent.getTarget();
		
		if (Globals.randomEngine.nextDouble()<0.1 && (btx.getHistory().damageTakenThisTurn(attacker) > 0)) {
			if (Globals.randomEngine.nextDouble() < 0.03) {
				defender.applyStatus(Status.POISON);
			}else if (Globals.randomEngine.nextDouble() < 0.03){
				defender.applyStatus(Status.PARALYSIS);
			}else if (Globals.randomEngine.nextDouble() < 0.03){
				defender.applyStatus(Status.SLEEP);
			}
		}

	}

}
