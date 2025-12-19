package abilities;

import pokemon.Pokemon;
import main.BattleContext;


public class Levitate extends Ability {
	 public Levitate() {
		 super("Levitate", Event.before_get_hit);
	 }
	 
	 public void trigger(BattleContext btx) {
		 //make the target pokemon target not take damage from ground type moves
		 
	 }
}
