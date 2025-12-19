package abilities;

import pokemon.Pokemon;
import pokemon.TurnIntent;
import main.BattleContext;
import main.Globals;
import main.Globals.Types;
import moves.GenFailMove;



public class Levitate extends Ability {
	 public Levitate() {
		 super("Levitate", Event.BEFORE_GET_HIT);
	 }
	 
	 //used in move Execution
	 public void trigger(BattleContext btx, Pokemon user, Pokemon target) {
		 //make the target pokemon target not take damage from ground type moves
		 TurnIntent userIntent = btx.getSnapshot().getIntentsMap().get(user);
		 Types type = userIntent.getMove().getMoveType();
		 
		 if (type == Types.GROUND) { //takes the attacker move type
			 userIntent.setMove(new GenFailMove()); //makes attacker move type that fails
			 System.out.println(this.getName()+" passive is triggered");
		 }
	 }
}
