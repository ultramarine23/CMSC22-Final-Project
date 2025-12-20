package main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import main.Globals;
import main.Globals.BattleEvent;
import main.Globals.Status;
import main.Globals.Weather;
import moves.Move;

//used for checking FailedMoves
import moves.Paralyzed;
import moves.Switch;
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
	private TurnSnapshot curTurnSnapshot;
	private TurnHistory curTurnHistory;

	// flags
	private boolean hasBattleEnded;
	private boolean isAlliedVictory;

	// Input queue for non-blocking reads
	private BlockingQueue<String> inputQueue = new LinkedBlockingQueue<>();

	// HP update callback
	private Runnable hpUpdateCallback;

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

		// Start input reader thread
		startInputReader();
	}

	private void startInputReader() {
		Thread inputThread = new Thread(() -> {
			try {
				while (!hasBattleEnded) {
					if (scanner.hasNextLine()) {
						String line = scanner.nextLine();
						inputQueue.offer(line);
					} else {
						Thread.sleep(50);
					}
				}
			} catch (Exception e) {
				System.err.println("Input reader thread ended: " + e.getMessage());
			}
		}, "Input-Reader-Thread");
		inputThread.setDaemon(true);
		inputThread.start();
	}

	//handles turn order, execution of moves, check if you won
	public void runBattle() {
		Globals.curInstance = this;
		runBattleInitialization();

		int turnCount = 1;
		while (!isOver && !hasBattleEnded) {
			try {
				System.out.println("\n========== TURN " + turnCount + " START ==========");

				// get turn inputs first, then run simultaneously
				TurnIntent allyIntent = buildTurnIntent(activeAlly, activeEnemy);
				TurnIntent enemyIntent = buildTurnIntent(activeEnemy, activeAlly);

				// Make sure both intents have valid moves
				if (allyIntent.getMove() == null) {
					System.out.println("ERROR: Ally has no move! Using first move.");
					allyIntent.setMove(activeAlly.getMoves().get(0));
				}
				if (enemyIntent.getMove() == null) {
					System.out.println("ERROR: Enemy has no move! Using first move.");
					enemyIntent.setMove(activeEnemy.getMoves().get(0));
				}

				// calculate turn order
				TurnIntent[] turnOrder;
				if (allyIntent.getEffectiveSpeed() > enemyIntent.getEffectiveSpeed()) {
					turnOrder = new TurnIntent[] {allyIntent, enemyIntent};
				} else if (allyIntent.getEffectiveSpeed() < enemyIntent.getEffectiveSpeed()) {
					turnOrder = new TurnIntent[] {enemyIntent, allyIntent};
				} else {
					// generate randomized turn order if speeds are equal
					boolean randomBool = Globals.randomEngine.nextBoolean();
					if (randomBool) {
						turnOrder = new TurnIntent[] {allyIntent, enemyIntent};
					} else {
						turnOrder = new TurnIntent[] {enemyIntent, allyIntent};
					}
				}

				// update snapshot and history
				curTurnSnapshot = new TurnSnapshot(turnOrder);
				curTurnHistory = new TurnHistory();

				context.setTurnSnapshot(curTurnSnapshot);
				context.setTurnHistory(curTurnHistory);

				// perform the round routine, with regular battle end checks
				System.out.println("\n--- First Move ---");
				moveExecutor.executeMove(this.getContext(), turnOrder[0]);

				if (hasBattleEnded) {
					return;
				}

				printBattleStatus();

				// Update HP bars after first move
				if (hpUpdateCallback != null) {
					hpUpdateCallback.run();
				}

				System.out.println("\n--- Second Move ---");
				moveExecutor.executeMove(this.getContext(), turnOrder[1]);

				if (hasBattleEnded) {
					return;
				}

				printBattleStatus();

				// Update HP bars after second move
				if (hpUpdateCallback != null) {
					hpUpdateCallback.run();
				}

				if (hasBattleEnded) {
					return;
				}

				System.out.println("\n--- End of Turn ---");
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

				System.out.println("========== TURN END ==========\n");

			} catch (Exception e) {
				System.err.println("Error in battle loop: " + e.getMessage());
				e.printStackTrace();
				hasBattleEnded = true;
			}
		}
		turnCount++;
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

	public void setHPUpdateCallback(Runnable callback) { this.hpUpdateCallback = callback; }

	// runBattle subfunctions
	public void runBattleInitialization() {
		this.activeAlly = allies.get(0);
		this.activeEnemy = enemies.get(0);

		System.out.println("========================================");
		System.out.println("           BATTLE START!");
		System.out.println("========================================");
		System.out.println("Your Pokemon: " + activeAlly.getPokemonSpecies().getName());
		System.out.println("Enemy Pokemon: " + activeEnemy.getPokemonSpecies().getName());
		System.out.println("========================================\n");
	}

	public void printBattleStatus() {
		System.out.println("\n--- Current Status ---");
		System.out.println("Your " + activeAlly.getPokemonSpecies().getName() +
				": HP " + activeAlly.getCurrentStats().getHp() + "/" + activeAlly.getBaseStats().getHp());
		System.out.println("Enemy " + activeEnemy.getPokemonSpecies().getName() +
				": HP " + activeEnemy.getCurrentStats().getHp() + "/" + activeEnemy.getBaseStats().getHp());
	}

	//Determines the order of turns, and checks for current Status of both ally and enemy poke
	public TurnIntent buildTurnIntent(Pokemon user, Pokemon target) {
		TurnIntent turnIntent = new TurnIntent();

		// set the user and target
		turnIntent.setUser(user);
		turnIntent.setTarget(target);

		if (user == activeAlly) {
			// PLAYER TURN - Wait for button input
			System.out.println("\n=== YOUR TURN ===");
			System.out.println("Available moves:");

			for (int i = 0; i < user.getMoves().size() && i < 4; i++) {
				System.out.println("  [" + i + "] " + user.getMoves().get(i).getName());
			}

			System.out.println("Waiting for your input...");

			try {
				// Wait for input from GUI (button click sends "0", "1", "2", or "3")
				String moveInput = inputQueue.poll(60, TimeUnit.SECONDS);

				if (moveInput != null) {
					moveInput = moveInput.trim();

					try {
						int moveIndex = Integer.parseInt(moveInput);

						if (moveIndex >= 0 && moveIndex < user.getMoves().size()) {
							turnIntent.setMove(user.getMoves().get(moveIndex));
							System.out.println("✓ You selected: " + user.getMoves().get(moveIndex).getName());
						} else {
							System.out.println("! Invalid index " + moveIndex + ", using first move");
							turnIntent.setMove(user.getMoves().get(0));
						}
					} catch (NumberFormatException e) {
						System.out.println("! Not a number, using first move");
						turnIntent.setMove(user.getMoves().get(0));
					}
				} else {
					System.out.println("! Timeout, using first move");
					turnIntent.setMove(user.getMoves().get(0));
				}
			} catch (InterruptedException e) {
				System.out.println("! Interrupted, using first move");
				turnIntent.setMove(user.getMoves().get(0));
			}

		} else if (user == activeEnemy) {
			// ENEMY TURN - AI picks random move (no input needed)
			System.out.println("\n=== ENEMY TURN ===");
			int randomMoveIndex = Globals.randomEngine.nextInt(Math.min(user.getMoves().size(), 4));
			Move selectedMove = user.getMoves().get(randomMoveIndex);
			turnIntent.setMove(selectedMove);
			System.out.println("Enemy will use: " + selectedMove.getName());
		}

		//CHECK CURRENT STATUS

		// apply paralysis check
		if (user.getCurStatus() == Status.PARALYSIS) {
			if (Globals.randomEngine.nextDouble() < 0.25) {
				turnIntent.setMove(new Paralyzed());
			}
		}

		//apply freezed check
		if (user.getCurStatus() == Status.FREEZE) {
			if (Globals.randomEngine.nextDouble() < 0.10) {
				turnIntent.setMove(new FailFreezed());
			}
		}

		//apply sleeping check
		if (user.getCurStatus() == Status.SLEEP){
			turnIntent.setMove(new FailSleep());
		}

		turnIntent.setContext(context);
		return turnIntent;
	}

	//before the turn begins
	public void runTransitionPhase() {
		Pokemon[] activePokemons = new Pokemon[] {activeAlly, activeEnemy};

		for (Pokemon activeMon : activePokemons) {
			// remove flinch from pokemon
			activeMon.setFlinched(false);

			int allyStatusTurn = activeMon.getStatusTurns();

			//checks if status timer has run out
			if (activeMon.getCurStatus() == Status.FREEZE && allyStatusTurn >= 3) {
				activeMon.applyStatus(Status.NONE);
			} else if (activeMon.getCurStatus() == Status.SLEEP && allyStatusTurn >= 4) {
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
				System.out.println(activeMon.getPokemonSpecies().getName() + " took toxic damage!");

				//chance to remove status debuffs every turn
			} else if (activeMon.getCurStatus() == Status.FREEZE) {
				if (Globals.randomEngine.nextDouble() < 0.33) {
					activeMon.applyStatus(Status.NONE);
					System.out.println(activeMon.getPokemonSpecies().getName() + " thawed out!");
				}
			} else if (activeMon.getCurStatus() == Status.SLEEP) {
				if(Globals.randomEngine.nextDouble() < 0.125) {
					activeMon.applyStatus(Status.NONE);
					System.out.println(activeMon.getPokemonSpecies().getName() + " woke up!");
				}
			} else if (activeMon.getCurStatus() == Status.PARALYSIS) {
				if(Globals.randomEngine.nextDouble() < 0.25) {
					System.out.println(activeMon.getPokemonSpecies().getName() + " is fully paralyzed!");
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
		isAlliedVictory = didAlliesWin;

		System.out.println("\n========================================");
		if (didAlliesWin) {
			System.out.println("          YOU WIN!");
		} else {
			System.out.println("          YOU LOSE!");
		}
		System.out.println("========================================");

		hasBattleEnded = true;
		isOver = true;
	}

	public Pokemon promptForReplacement(Pokemon switched) {
		System.out.print("Switch with which Pokemon: ");

		try {
			String replacerInput = inputQueue.poll(30, TimeUnit.SECONDS);
			Pokemon replacer = null;

			if (replacerInput != null) {
				if (switched == activeAlly) {
					for (Pokemon ally : allies) {
						if (ally.getPokemonSpecies().getName().equals(replacerInput)) {
							replacer = ally;
						}
					}
				} else {
					for (Pokemon enemy : enemies) {
						if (enemy.getPokemonSpecies().getName().equals(replacerInput)) {
							replacer = enemy;
						}
					}
				}
			}

			return replacer;
		} catch (InterruptedException e) {
			return null;
		}
	}

	public void switchPokemon(Pokemon switched, Pokemon replacer) {
		if (switched == activeAlly) {
			activeAlly = replacer;
		} else {
			activeEnemy = replacer;
		}

		for (TurnIntent intent : curTurnSnapshot.getTurnOrder()) {
			if (intent.getTarget() == switched) {
				intent.setTarget(replacer);
			}
		}
	}
}