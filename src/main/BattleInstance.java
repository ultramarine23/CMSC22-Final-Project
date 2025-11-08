package main;

import java.util.ArrayList;
import java.util.Scanner;

import main.Globals.BattleEvent;
import main.Globals.Weather;
import moves.Move;
import pokemon.Pokemon;
import pokemon.TurnIntent;

public class BattleInstance {
	private final BattleContext context;
	private final BattleEventBus eventBus;

	private Scanner scanner = new Scanner(System.in);
	private boolean isOver = false;
	private ArrayList<Pokemon> allies;
	private ArrayList<Pokemon> enemies;
	private Weather curWeather;
	private int weatherDuration;
	private Pokemon activeAlly;
	private Pokemon activeEnemy;
	private int roundCount;
	
	public BattleInstance(ArrayList<Pokemon> allies, ArrayList<Pokemon> enemies) {
		this.allies = allies;
		this.enemies = enemies;
		this.activeAlly = allies.get(0);
		this.activeEnemy = enemies.get(0);
		this.roundCount = 0;
		
		this.context = new BattleContext(this);
		this.eventBus = new BattleEventBus();
		
		eventBus.subscribeToEvent(BattleEvent.POKEMON_DIED, ctx -> {System.out.println("Pokemon died!");});
	}
	
	public void runBattle() {
		Globals.curInstance = this;
		runBattleInitialization();
		
		while (!isOver) {
			// true corresponds to ally, false to enemy; first in array = first to move
			boolean[] turnOrder; 
			
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
			TurnIntent allyTurnIntent = buildTurnIntent(turnOrder[0]);
			allyTurnIntent.runTurn();
			printBattleStatus();
			scanner.nextLine();
			
			TurnIntent enemyTurnIntent = buildTurnIntent(turnOrder[1]);
			enemyTurnIntent.runTurn();
			printBattleStatus();
			scanner.nextLine();
			
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
		System.out.println("\nEnemies: ");
		for (Pokemon poke : enemies) {
			System.out.println(poke.toString());
		}
		System.out.print("\nAlly, choose your lead: ");
		allyLeadInput = scanner.nextLine();
		
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
	
	
	public TurnIntent buildTurnIntent(boolean isForAlly) {
		String moveInput = "";
		TurnIntent turnIntent = new TurnIntent();
		
		// set the user and target depending on passed in field isForAlly
		if (isForAlly) {
			System.out.println("\nAlly turn!");
			turnIntent.setUser(activeAlly);
			turnIntent.setTarget(activeEnemy);
		} else {
			System.out.println("\nEnemy turn!");
			turnIntent.setUser(activeEnemy);
			turnIntent.setTarget(activeAlly);
		}
		
		System.out.print("Select move: ");
		moveInput = scanner.nextLine();
		
		// note: if no matching move is found this will result in an error
		for (Move move : activeAlly.getMoves()) {
			if (move.getName().equals(moveInput)) {
				turnIntent.setMove(move);
			}
		}
		
		turnIntent.setContext(context);
		
		return turnIntent;
	}
	
}
