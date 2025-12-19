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
			 /*
			 int curPow = btx.getSnapshot().getIntentsMap().get(target).getMove().getBasePower(); //makes it so that will attack, but cant do damage */
			 userIntent.setMove(new GenFailMove());
			 System.out.println("Move doesnt work on floating pokemon.");
		 }
	 }
}
