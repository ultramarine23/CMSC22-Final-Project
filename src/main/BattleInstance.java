package main;

import java.util.ArrayList;
import java.util.Scanner;

import main.Globals.Weather;
import pokemon.Pokemon;

public class BattleInstance {
	private final BattleContext context;

	private Scanner scanner = new Scanner(System.in);
	private boolean isOver = false;
	private ArrayList<Pokemon> allies;
	private ArrayList<Pokemon> enemies;
	private Weather curWeather;
	private int weatherDuration;
	private Pokemon activeAlly;
	private Pokemon activeEnemy;
	
	public BattleInstance(ArrayList<Pokemon> allies, ArrayList<Pokemon> enemies) {
		this.allies = allies;
		this.enemies = enemies;
		this.activeAlly = allies.get(0);
		this.activeEnemy = enemies.get(0);
		this.context = new BattleContext(this);
	}
	
	public void runBattle() {
		printBattleStartPreamble();
		
		while (!isOver) {
			System.out.println("Ally turn!");
			
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
	
	public void setActiveAlly(Pokemon activeAlly) { this.activeAlly = activeAlly; }
	public void setActiveEnemy(Pokemon activeEnemy) { this.activeEnemy = activeEnemy; }

	
	public void printBattleStartPreamble() {
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
		System.out.println("\nAlly, choose your lead: ");
		allyLeadInput = scanner.nextLine();
		System.out.println("\nEnemy, choose your lead: ");
		enemyLeadInput = scanner.nextLine();
		
		for (Pokemon poke : allies) {
			if (poke.getPokemonSpecies().getName().equals(allyLeadInput)) {
				System.out.println("Ally selected as lead!");
				activeAlly = poke;
				break;
			}
		}
		
		for (Pokemon poke : enemies) {
			if (poke.getPokemonSpecies().getName().equals(enemyLeadInput)) {
				System.out.println("Enemy selected as lead!");
				activeEnemy = poke;
				break;
			}
		}
	}
}
