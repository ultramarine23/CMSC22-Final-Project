package main;

import java.util.ArrayList;

import main.Globals.Weather;
import pokemon.Pokemon;

public class BattleInstance {
	private final BattleContext context;

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
		while (!isOver) {
			System.out.println("Battle Start!");
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

	
}
