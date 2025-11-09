package main;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import main.Globals.BattleEvent;

public class BattleEventBus {
	Map<BattleEvent, List<Consumer<Object[]>>> listeners = new HashMap<>();
	
	{
		// initialize the listeners hashmap
		for (BattleEvent event : BattleEvent.values()) {
			listeners.put(event, new ArrayList<Consumer<Object[]>>());
		}
	}
	
	
	// a method to allow receiving objects to connect themselves to a specific event
	public void subscribeToEvent(BattleEvent event, Consumer<Object[]> receivingFunc) {
		listeners.get(event).add(receivingFunc);
	}
	
	
	public void triggerEvent(BattleEvent event, Object... args) {
		// this function is called by any object wanting to trigger an event
		for (Consumer<Object[]> func : listeners.get(event)) {
			func.accept(args);
		}
	}
}
