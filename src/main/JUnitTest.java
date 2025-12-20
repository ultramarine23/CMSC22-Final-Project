package main;

import pokemon.MovesetData;
import pokemon.Pokemon;
import pokemon.StatsManager;
import pokemon.StatsContainer;
import pokemon.StatsData;
import pokemon.PokemonSpecies;
import java.util.List;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import abilities.Ability;
import abilities.None;
import abilities.SwiftSwim;
import main.Globals.Types;
import moves.GenFailMove;
import moves.Move;

public class JUnitTest {
	//add all the objects that needs testing
	StatsManager statsManager;
	StatsContainer statsContainer;
	PokemonSpecies pokemonSpecies;
	
	

	public JUnitTest() {
		
	}
	
	
	void testStatsManager() {
		
		
	}
	
	void testStatsContainer() {
		
	}
	
	void testPokemonSpecies() {
		StatsContainer stats = new StatsContainer(new int[]{100,100,100,100,100,100});
	    Ability ability = new None();
	    Move move = new GenFailMove();

	    PokemonSpecies species = new PokemonSpecies(
				"Weezing", 
				StatsCalculator.realizeAllStats(StatsData.WEEZING), 
				9, 
				Types.POISON, 
				Types.NONE,
				List.of(new SwiftSwim()),
				MovesetData.WEEZING);

	    assertEquals("Weezing", species.getName());
	    assertEquals(stats, species.getBaseStats());
	    assertEquals(60, species.getWeight());
	    assertEquals(Types.ELECTRIC, species.getType1());
	    assertEquals(Types.NONE, species.getType2());
	    assertEquals(1, species.getAbilities().size());
	    assertEquals(1, species.getLearnableMoves().size());
		}
		
	}
	
	
}
