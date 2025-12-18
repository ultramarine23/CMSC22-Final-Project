package main;

import moves.Move;
import pokemon.Pokemon;
import pokemon.TurnIntent;

public class MoveExecutor {
	public void executeMove(TurnIntent intent) {
		Pokemon user = intent.getUser();
		Pokemon target = intent.getTarget();
		Move move = intent.getMove();
		BattleContext context = intent.getContext();
		
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
}
