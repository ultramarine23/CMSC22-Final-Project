package abilities;

import main.BattleContext;
import pokemon.Pokemon;

public class None extends Ability {

	public None() {
		super("None", Event.DO_NOTHING);
		
	}
	
	//this is used for when pokemon has no abilities
	@Override
	public void trigger(BattleContext btx, Pokemon user, Pokemon target) {
		//do nothing

	}

}
