package main;

import moves.Move;
import pokemon.Pokemon;
import pokemon.TurnIntent;
import abilities.Ability;
import abilities.Ability.Event;


public class MoveExecutor {
	public void executeMove(BattleContext btx, TurnIntent intent) {
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
		
		
		
	
		
		
		//check for abilities before move execution byt the user
		if(user.getAbilty().getTriggerEvent() == Event.BEFORE_ATTACK) {
			user.getAbilty().trigger(btx, user, target);
		}
		
		
		// deal main damage component, or depending on the move checks something
		move.beforeExecution(user, target, context);
		
		//Checks abilities like if the target is immune to damage
		if(target.getAbilty().getTriggerEvent() == Event.BEFORE_GET_HIT) {
			target.getAbilty().trigger(btx, user, target);
		}
		
		// update user/target/move to account for after BEFORE ATTACK/ GET HIT
		user = intent.getUser();
		target = intent.getTarget();
		move = intent.getMove();
		
		int damageDealt = DamageCalculator.calculateDamage(user, target, move, context);
		target.takeDamage(damageDealt);
		
		
		// trigger post-execution effects, like adding status debuffs, buffs
		move.afterExecution(user, target, context);
		if(user.getAbilty().getTriggerEvent() == Event.AFTER_ATTACK) {
			user.getAbilty().trigger(btx, user, target);
		}
		
		
		//checks ability AFTER_GET_HIT event
		if(target.getAbilty().getTriggerEvent() == Event.AFTER_GET_HIT) {
			target.getAbilty().trigger(btx, user, target);
		}
		
		
		
		
		// print damaged message
		if (damageDealt != 0) {
			if (target.isAllied()) {
				System.out.print("Your ");
			} else {
				System.out.print("Enemy ");
			}
			System.out.println(user.getPokemonSpecies().getName() + " took " + damageDealt + " damage!");
		}
		
		
	}
	
}

