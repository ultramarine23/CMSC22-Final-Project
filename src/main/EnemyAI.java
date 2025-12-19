package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import main.Globals.MoveCategory;
import moves.Move;
import moves.Switch;
import pokemon.Pokemon;
import pokemon.TurnIntent;

public class EnemyAI {
	// an EnemyAI object is owned by the BattleInstance in order to select moves for
	// the opponent
	private final Map<Pokemon, List<Move>> knownMoves;
	
	public EnemyAI(BattleContext ctx) {
		// the AI starts off "guessing" right one move of each pokemon
		this.knownMoves = new HashMap<Pokemon, List<Move>>();
		
		for (Pokemon poke : ctx.getCurBattle().getAllies()) {
			knownMoves.put(poke, new ArrayList<Move>());
			
			int randomMoveIndex = Globals.randomEngine.nextInt(poke.getMoves().size());
			
			knownMoves.get(poke).add(poke.getMoves().get(randomMoveIndex));
		}
	}
	
	class ScoredIntent {
		final TurnIntent intent;
		final double score;
		
		public ScoredIntent(TurnIntent intent, double score) {
			this.intent = intent;
			this.score = score;
		}
		
		public double getScore() { return score; }
	}
	
	public TurnIntent generateEnemyIntent(Pokemon user, BattleContext ctx) {
		TurnIntent selectedIntent = null;
		List<TurnIntent> possibleIntents = getPossibleIntents(user, ctx);
		List<ScoredIntent> sortedIntents = new ArrayList<ScoredIntent>();
		
		for (TurnIntent intent : possibleIntents) {
			sortedIntents.add(new ScoredIntent(intent, evaluateTurnIntent(intent, ctx)));
		}
		
		sortedIntents.sort(Comparator.comparingDouble((ScoredIntent s) -> s.score));
		selectedIntent = sortedIntents.reversed().get(0).intent;
		
		return selectedIntent;
	}
	
	public List<TurnIntent> getPossibleIntents(Pokemon user, BattleContext ctx) {
		List<TurnIntent> possibleIntents = new ArrayList<TurnIntent>();
		Pokemon target = ctx.getCurBattle().getActiveAlly();

		// register any of the moves as a possible intent
		for (Move move : user.getMoves()) {
			TurnIntent newIntent = new TurnIntent();
			newIntent.setUser(user);
			newIntent.setTarget(target);
			newIntent.setMove(move);
			possibleIntents.add(newIntent);
		}
		
		// register switching to any other enemy as a possible intent
		for (Pokemon enemy : ctx.getCurBattle().getEnemies()) {
			if (enemy == user) {
				continue;
			} 
			
			TurnIntent newIntent = new TurnIntent();
			newIntent.setUser(user);
			newIntent.setTarget(target);
			newIntent.setMove(new Switch(enemy));
			newIntent.setContext(ctx);
		}
		
		return possibleIntents;
	}
	
	public double evaluateTurnIntent(TurnIntent intent, BattleContext ctx) {
		double mainScore = 0; // represents conventional logic
		Pokemon currentAlly = ctx.getCurBattle().getActiveAlly();
		
		if (intent.getMove() instanceof Switch) {
			// add to mainScore based on damage avoided (for switches) 
			int maxStayInDamage = 0;
			double maxSTIDPercent = 0.0;
			
			for (Move curAllyMove : knownMoves.get(ctx.getCurBattle().getActiveAlly())) {
				int dmgTaken = DamageCalculator.calculateDamage(currentAlly, intent.getUser(), curAllyMove, ctx);
				maxStayInDamage = Integer.max(dmgTaken, maxStayInDamage);
			}
			
			maxSTIDPercent = (double)maxStayInDamage / (double)intent.getUser().getCurrentStats().getHp();
			
			// compare the damage taken by the other enemy
			Switch move = (Switch)intent.getMove();
			Pokemon switchin = move.getReplacer();
			
			int maxSwitchinDamage = 0;
			double maxSWIDPercent = 0.0;
			
			for (Move allyMove : knownMoves.get(currentAlly)) {
				int dmgTaken = DamageCalculator.calculateDamage(currentAlly, switchin, allyMove, ctx);
				maxSwitchinDamage = Integer.max(dmgTaken, maxSwitchinDamage);
				}
			
			maxSWIDPercent = (double)maxSwitchinDamage / (double)switchin.getCurrentStats().getHp();
			
			mainScore += (maxSTIDPercent - maxSWIDPercent) * 100;
		} else {
			// add to mainScore based on damage dealt
			int damage = DamageCalculator.calculateDamage(
					intent.getUser(), intent.getTarget(), intent.getMove(), ctx);
			
			int targetHp = intent.getTarget().getCurrentStats().getHp();
			mainScore = ((double)damage / (double)targetHp) * 100;
		}
		
		if (intent.getMove().getMoveCategory() == MoveCategory.STATUS) {
			mainScore = 50;
		}
		
		return mainScore;
	}
}
