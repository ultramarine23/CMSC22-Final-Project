package abilities;

import pokemon.Pokemon;
import main.BattleContext;

enum Event {
	before_switchin,
	after_switchin,
	before_get_hit, 
	after_get_hit,
	before_attack,
	after_attack
}


public abstract class Ability {
	private String name;
	private Pokemon user;
	private Event triggerEvent; // the event that calls the function trigger()
	
	public Ability(String newName, Event newTriggerEvent) {
		name = newName;
		triggerEvent = newTriggerEvent;
	}
	
	//this is different for every ability, since they have different Event triggers
	public abstract void trigger(BattleContext btx);
	
	// set-get functions
	public void setUser(Pokemon newUser) { user = newUser; }
	public Pokemon getUser() { return user; }
	
	public void setName(String newName) { name = newName; }
	public String getName() { return name; }
	
	public void setTriggerEvent(Event newTriggerEvent) { triggerEvent = newTriggerEvent; }
	public Event getTriggerEvent() { return triggerEvent; }
}
