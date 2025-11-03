package abilities;

import pokemon.Pokemon;

public class Levitate extends Ability {
	 public Levitate() {
		 super("Levitate", Event.before_get_hit);
	 }
	 
	 public void trigger(Pokemon target) {
		 
	 }
}
