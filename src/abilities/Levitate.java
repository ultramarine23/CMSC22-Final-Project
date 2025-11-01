package abilities;

import pokemon.Pokemon;

public class Levitate extends Ability {
	 public Levitate(Pokemon user) {
		 super("Levitate", user, Event.before_get_hit);
	 }
	 
	 public void trigger(Pokemon target) {
		 
	 }
}
