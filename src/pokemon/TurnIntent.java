package pokemon;

import main.BattleContext;
import main.DamageCalculator;
import main.Globals.Status;
import moves.Move;

public class TurnIntent {
	private Pokemon user;
	private Pokemon target;
	private Move move;
	private BattleContext context;
	
	
	public void runTurn() {
		if (user.isFlinched()) {
			user.setFlinched(false);
			if (user.isAllied()) {
				System.out.print("Your ");
			} else {
				System.out.print("Enemy ");
			System.out.println(user.getPokemonSpecies().getName() + " flinched!");
			return;
			}
		}
		
		int damageDealt = DamageCalculator.calculateDamage(user, target, move, context);
		
		// print used move message
		if (move.getName() != "[Paralyzed]") {
			if (user.isAllied()) {
				System.out.print("Your ");
			} else {
				System.out.print("Enemy ");
			}
			System.out.println(user.getPokemonSpecies().getName() + " used " + move.getName() + "!");
		} else {
			System.out.println(user.getPokemonSpecies().getName() + " was paralyzed!");
		}
		
		
		//checks for other failedMoves like freeze and sleep
		
		
		//check for abilities that makes the target immune
		
		
		
		// deal main damage component
		move.beforeExecution(user, target, context);
		target.takeDamage(damageDealt);
		
		// print damaged message
		if (damageDealt != 0) {
			if (target.isAllied()) {
				System.out.print("Your ");
			} else {
				System.out.print("Enemy ");
			}
			System.out.println(user.getPokemonSpecies().getName() + " took " + damageDealt + " damage!");
		}
		
		// trigger post-execution effects
		move.afterExecution(user, target, context);
	}

	public int getEffectiveSpeed() { return user.getCurrentStats().getSpeed() + (move.getPriority() * 5000); }
	
	// set-get methods
	public Pokemon getUser() { return user; }
	public void setUser(Pokemon user) { this.user = user; }
	
	public Pokemon getTarget() { return target;	}
	public void setTarget(Pokemon target) {	this.target = target; }
	
	public Move getMove() {	return move; }
	public void setMove(Move move) { this.move = move; }
	
	public BattleContext getContext() { return context; } 
	public void setContext(BattleContext context) { this.context = context; } 
}
