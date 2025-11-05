package pokemon;

import main.DamageCalculator;
import moves.Move;

public class TurnIntent {
	private Pokemon user;
	private Pokemon target;
	private Move move;
	
	
	public void runTurn() {
		int damageDealt = DamageCalculator.calculateDamage(user, target, move);
		target.takeDamage(damageDealt);
	}

	public int getEffectiveSpeed() { return user.getCurrentStats().getSpeed() + (move.getPriority() * 5000); }
	
	// set-get methods
	public Pokemon getUser() { return user; }
	public void setUser(Pokemon user) { this.user = user; }
	
	public Pokemon getTarget() { return target;	}
	public void setTarget(Pokemon target) {	this.target = target; }
	
	public Move getMove() {	return move; }
	public void setMove(Move move) { this.move = move; }
}
