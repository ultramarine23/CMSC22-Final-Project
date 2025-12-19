package abilities;

import pokemon.Pokemon;
import pokemon.TurnIntent;
import main.BattleContext;
import main.Globals;
import main.Globals.Types;
import moves.GenFailMove;


//used in pokemonSpecies, immutable
public abstract class Ability {
	private String name;
	private Pokemon user;
	private Event triggerEvent; // the event that calls the function trigger()
	
	
	public Ability(String newName, Event newTriggerEvent) {
		name = newName;
		triggerEvent = newTriggerEvent;
	}
	
	//this is different for every ability, since they have different Event triggers
	public abstract void trigger(BattleContext btx, Pokemon user, Pokemon target);
	
	// set-get functions
	//this is just for creating new abilities
	public void setUser(Pokemon newUser) { user = newUser; }
	public Pokemon getUser() { return user; }
	
	public void setName(String newName) { name = newName; }
	public String getName() { return name; }
	
	public void setTriggerEvent(Event newTriggerEvent) { triggerEvent = newTriggerEvent; }
	public Event getTriggerEvent() { return triggerEvent; }
	
	public enum Event {
		//switch not implemented yet
		before_switchin,
		after_switchin,
		
		//used when receiving the moves/damage/hits -> the user has the ability
		BEFORE_GET_HIT,
		AFTER_GET_HIT,
		
		//used when dealing damage/hits;moves might affect opponent or self -> the target has the abi
		BEFORE_ATTACK,
		AFTER_ATTACK
	}
}
