package main;

import java.util.ArrayList;
import java.util.Scanner;

import main.Globals;
import main.Globals.BattleEvent;
import main.Globals.Status;
import main.Globals.Weather;
import moves.Move;

//used for checking FailedMoves
import moves.Paralyzed;
import moves.FailFreezed;
import moves.FailSleep;

import pokemon.Pokemon;
import pokemon.TurnIntent;



public class BattleInstance {
	private final BattleContext context;
	private final BattleEventBus eventBus;
	private final MoveExecutor moveExecutor;

	private Scanner scanner = new Scanner(System.in);
	private boolean isOver = false;
	private ArrayList<Pokemon> allies;
	private ArrayList<Pokemon> enemies;
	private Weather curWeather;
	private int weatherDuration;
	private Pokemon activeAlly;
	private Pokemon activeEnemy;
	private int roundCount;
	
	// flags
	private boolean hasBattleEnded;
	private boolean isAlliedVictory;
	
	public BattleInstance(ArrayList<Pokemon> allies, ArrayList<Pokemon> enemies) {
		this.allies = allies;
		this.enemies = enemies;
		this.activeAlly = allies.get(0);
		this.activeEnemy = enemies.get(0);
		this.roundCount = 0;
		
		this.context = new BattleContext(this);
		this.eventBus = new BattleEventBus();
		this.moveExecutor = new MoveExecutor();
		
		eventBus.subscribeToEvent(BattleEvent.POKEMON_DIED, args -> onPokemonDied(args));
	}
	
	//handles turn order, execution of moves, check if you won
	public void runBattle() {
		Globals.curInstance = this;
		runBattleInitialization();
		
		while (!isOver) {
			// true corresponds to ally, false to enemy; first in array = first to move
			boolean[] turnOrder; 
			
			// calculate turn order
			if (activeAlly.getCurrentStats().getSpeed() > activeEnemy.getCurrentStats().getSpeed()) {
				turnOrder = new boolean[] {true, false};
			} else if (activeAlly.getCurrentStats().getSpeed() < activeEnemy.getCurrentStats().getSpeed()) {
				turnOrder = new boolean[] {false, true};
			} else {
				// generate randomized turn order if speeds are equal
				boolean randomBool = Globals.randomEngine.nextBoolean();
				turnOrder = new boolean[] {randomBool, !randomBool};
			}
			
			// run the current round
			// get turn inputs first, then run simultaneously
			TurnIntent firstIntent = buildTurnIntent(turnOrder[0]);			
			TurnIntent secondIntent = buildTurnIntent(turnOrder[1]);
			
			// perform the round routine, with regular battle end checks
			moveExecutor.executeMove(firstIntent);
			printBattleStatus();
			scanner.nextLine();
			
			if (hasBattleEnded) {
				return;
			}
			
			moveExecutor.executeMove(secondIntent);
			printBattleStatus();
			scanner.nextLine();
			
			if (hasBattleEnded) {
				return;
			}
			
			runTransitionPhase();
			
			if (hasBattleEnded) {
				return;
			}
			
			// tick down weather turns, remove weather if turns tick down to zero
			if (weatherDuration != 0) {
				weatherDuration--;
			} else {
				curWeather = Weather.NONE;
			}
		}
	}
	
	
	public void changeWeather(Weather newWeather, int duration) {
		curWeather = newWeather;
		weatherDuration = duration;
	}
	
	// set-get functions
	public boolean isOver() { return isOver; }

	public ArrayList<Pokemon> getAllies() { return allies; }
	public ArrayList<Pokemon> getEnemies() { return enemies; }
	public Weather getWeather() { return curWeather; }
	public Pokemon getActiveAlly() { return activeAlly; }
	public Pokemon getActiveEnemy() { return activeEnemy; }
	public BattleContext getContext() { return context; }
	public int getRoundCount() { return roundCount; }
	public BattleEventBus getEventBus() { return eventBus; }
	
	public void setActiveAlly(Pokemon activeAlly) { this.activeAlly = activeAlly; }
	public void setActiveEnemy(Pokemon activeEnemy) { this.activeEnemy = activeEnemy; }

	
	// runBattle subfunctions
	public void runBattleInitialization() {
		String allyLeadInput;
		String enemyLeadInput;
		
		System.out.println("Battle Start!");
		System.out.println("Allies: ");
		for (Pokemon poke : allies) {
			System.out.println(poke.toString());
		}
		
		//chooses poke
		System.out.println("\nEnemies: ");
		for (Pokemon poke : enemies) {
			System.out.println(poke.toString());
		}
		System.out.print("\nAlly, choose your lead: ");
		allyLeadInput = scanner.nextLine();
		
		//checks your roster
		for (Pokemon poke : allies) {
			if (poke.getPokemonSpecies().getName().equals(allyLeadInput)) {
				System.out.println(poke.getPokemonSpecies().getName() + " selected as lead!");
				activeAlly = poke;
				break;
			}
		}
		
		System.out.print("\nEnemy, choose your lead: ");
		enemyLeadInput = scanner.nextLine();
		
		for (Pokemon poke : enemies) {
			if (poke.getPokemonSpecies().getName().equals(enemyLeadInput)) {
				System.out.println(poke.getPokemonSpecies().getName() + " selected as lead!");
				activeEnemy = poke;
				break;
			}
		}
	}
	
	
	public void printBattleStatus() {
		System.out.println("Ally side: ");
		System.out.println(activeAlly.toString());
		System.out.println("\nEnemy side: ");
		System.out.println(activeEnemy.toString());
	}
	
	//Determines the order of turns, and checks for current Status of both ally and enemy poke
	public TurnIntent buildTurnIntent(boolean isForAlly) {
		TurnIntent turnIntent = new TurnIntent();
		
		if (isForAlly) {
			String moveInput = "";
			
			// set the user and target depending on passed in field isForAlly
			System.out.println("\nAlly turn!");
			turnIntent.setUser(activeAlly);
			turnIntent.setTarget(activeEnemy);
			
			System.out.print("Select move: ");
			moveInput = scanner.nextLine();
			
			// note: if no matching move is found this will result in an error
			for (Move move : activeAlly.getMoves()) {
				if (move.getName().equals(moveInput)) {
					turnIntent.setMove(move);
				}
			}
		} else {
			String moveInput = "";
			
			// set the user and target depending on passed in field isForAlly
			System.out.println("\nEnemy turn!");
			turnIntent.setUser(activeEnemy);
			turnIntent.setTarget(activeAlly);
			
			System.out.print("Select move: ");
			moveInput = scanner.nextLine();
			
			// note: if no matching move is found this will result in an error
			for (Move move : activeEnemy.getMoves()) {
				if (move.getName().equals(moveInput)) {
					turnIntent.setMove(move);
				}
			}		
		}
		
		//CHECK CURRENT STATUS
		
		// apply paralysis check
		if (activeAlly.getCurStatus() == Status.PARALYSIS) {
			if (Globals.randomEngine.nextDouble() < 0.25) {
				turnIntent.setMove(new Paralyzed());
			}
		}
		
		//apply freezed check
		if (activeAlly.getCurStatus() == Status.FREEZE) {
			if (Globals.randomEngine.nextDouble() < 0.10) {
				turnIntent.setMove(new FailFreezed());
			}
		}
		
		//apply sleeping check
		if (activeAlly.getCurStatus() == Status.SLEEP){
			if (Globals.randomEngine.nextDouble() <= 1) {
				turnIntent.setMove(new FailSleep());
			}
		}
		
		turnIntent.setContext(context);
		return turnIntent;
	}
	
	
	//before the turn begins
	public void runTransitionPhase() {
		// runs the transition phase that sets up the following turn of battle
		// trigger poison/burn damage
		Pokemon[] activePokemons = new Pokemon[] {activeAlly, activeEnemy};
		
		for (Pokemon activeMon : activePokemons) {
			// remove flinch from pokemon
			activeMon.setFlinched(false);
			
			int allyStatusTurn = activeMon.getStatusTurns();
			
			//checks if status timer has run out
			if (activeMon.getCurStatus() == Status.FREEZE & allyStatusTurn >= 3) {
				activeMon.applyStatus(Status.NONE);
			} else if (activeMon.getCurStatus() == Status.SLEEP & allyStatusTurn >= 4) {
				activeMon.applyStatus(Status.NONE);
			} 
			
			//checks for various status effects
			if (activeMon.getCurStatus() == Status.BURN) {
				activeMon.takeDamage(activeMon.getBaseStats().getHp() / 16);
				System.out.println(activeMon.getPokemonSpecies().getName() + " took burn damage!");
			} else if (activeMon.getCurStatus() == Status.POISON) {
				activeMon.takeDamage(activeMon.getBaseStats().getHp() / 8);
				System.out.println(activeMon.getPokemonSpecies().getName() + " took poison damage!");
			} else if (activeMon.getCurStatus() == Status.TOXIC) {
				activeMon.takeDamage(activeMon.getBaseStats().getHp() * activeMon.getStatusTurns() / 16);
				System.out.println(activeMon.getPokemonSpecies().getName() + " took poison damage!");
				
			//chance to remove status debuffs every turn
			} else if (activeMon.getCurStatus() == Status.FREEZE) {
				if (Globals.randomEngine.nextDouble() < 0.33) {
					activeMon.applyStatus(Status.NONE);
					System.out.println(activeMon.getPokemonSpecies().getName() + " thawed");
				}
			} else if (activeMon.getCurStatus() == Status.SLEEP) {
				if(Globals.randomEngine.nextDouble() < 0.125) {
					activeMon.applyStatus(Status.NONE);
					System.out.println(activeMon.getPokemonSpecies().getName() + " woke up");
				}
			} else if (activeMon.getCurStatus() == Status.PARALYSIS) {
				if(Globals.randomEngine.nextDouble() < 0.25) {
					activeMon.applyStatus(Status.NONE);
					System.out.println(activeMon.getPokemonSpecies().getName() + " recovered from paralysis");
				}
			}
			
			//each status debuffs turns will increment
			activeMon.incrementStatusTurns();
		}
		
		// increment round count
		roundCount++;
	}
	
	
	// EVENT-TRIGGERED FUNCTIONS
	private void onPokemonDied(Object[] deadMons) {
		Pokemon deadPoke = (Pokemon) deadMons[0];
		
		allies.remove(deadPoke);
		enemies.remove(deadPoke);
		
		// then, check if battle ended
		if (allies.size() == 0) {
			endBattle(false);
		} else if (enemies.size() == 0) {
			endBattle(true);
		}
	}
	
	private void endBattle(boolean didAlliesWin) {
		hasBattleEnded = true;
		isAlliedVictory = didAlliesWin;
		
		if (didAlliesWin) {
			System.out.println("YOU WIN!");
		} else {
			System.out.println("YOU LOSE!");
		}
	}
}
