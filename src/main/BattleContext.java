package main;

import main.Globals.Weather;
import pokemon.Pokemon;

// facade class that provides an interface through which moves and abilities
// can interact with the battleinstance (setting weather, applying damage, etc.)

public class BattleContext {
	private final BattleInstance curBattle;
	private TurnHistory curTurnHistory;
	private TurnSnapshot curTurnSnapshot;
	
	public BattleContext(BattleInstance curBattle) {
		this.curBattle = curBattle;
	}
	
	public BattleInstance getCurBattle() { return curBattle; }
	public TurnHistory getHistory() { return curTurnHistory; }
	public TurnSnapshot getSnapshot() { return curTurnSnapshot; }
	
	public void applyDamage(Pokemon target, int damage) {
		target.takeDamage(damage);
	}
	
	public void applyWeather(Weather newWeather, int duration) {
		curBattle.changeWeather(newWeather, duration);
	}
	
	public void setTurnHistory(TurnHistory newHistory) {
		curTurnHistory = newHistory;
	}
	
	public void setTurnSnapshot(TurnSnapshot newSnapshot) {
		curTurnSnapshot = newSnapshot;
	}
}
