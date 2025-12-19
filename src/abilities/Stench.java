package abilities;

import main.BattleContext;
import main.Globals;
import pokemon.Pokemon;
import pokemon.TurnIntent;

public class Stench extends Ability {

	public Stench() {
		super("Stench", Event.AFTER_ATTACK);
		// TODO Auto-generated constructor stub
	}
	//By releasing a stench when attacking, the Pokémon may cause the target to flinch. *add
	@Override
	public void trigger(BattleContext btx, Pokemon user, Pokemon target) {
		TurnIntent userIntent = btx.getSnapshot().getIntentsMap().get(user); //get the intent of attacker
		Pokemon attacker = userIntent.getUser();
		Pokemon defender = userIntent.getTarget();
		
		if (Globals.randomEngine.nextDouble()<0.1) {//if 10 percent chance and the move damages oponent
			defender.setFlinched(true);
		}
		

	}

}
