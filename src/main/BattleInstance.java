package main;

import java.util.ArrayList;

import pokemon.Pokemon;

public class BattleInstance {
	boolean isOver = false;
	ArrayList<Pokemon> allies;
	ArrayList<Pokemon> enemies;
	Pokemon activeAlly;
	Pokemon activeEnemy;
	
	public BattleInstance(ArrayList<Pokemon> allies, ArrayList<Pokemon> enemies) {
		this.allies = allies;
		this.enemies = enemies;
		this.activeAlly = allies.get(0);
		this.activeEnemy = enemies.get(0);
	}
	
	public void runBattle() {
		while (!isOver) {
			
		}
	}
	
	
}
