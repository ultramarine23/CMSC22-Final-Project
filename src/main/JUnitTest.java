package main;

import pokemon.MovesetData;
import pokemon.Pokemon;
import pokemon.StatsManager;
import pokemon.StatsContainer;
import pokemon.StatsData;
import pokemon.PokemonSpecies;
import java.util.List;

import abilities.Ability;
import abilities.None;
import abilities.SwiftSwim;
import main.Globals.Stats;
import main.Globals.Types;
import moves.GenFailMove;
import moves.Move;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

public class JUnitTest {
	//add all the objects that needs testing
	StatsManager statsManager;
	StatsContainer statsContainer;
	PokemonSpecies pokemonSpecies;
	
	
	
	public JUnitTest() {
		
	}
	
	
	@Test
	public void testStatsManager() {
		
		
	}
	
	@Test
	public void testStatsContainer() {
		// Initialize stats
        int[] initialStats = {100, 50, 60, 70, 80, 90};
        StatsContainer stats = new StatsContainer(initialStats);

        // Test getters
        assertEquals(100, stats.getHp());
        assertEquals(50, stats.getAtk());
        assertEquals(60, stats.getDef());
        assertEquals(90, stats.getSpeed());
        assertEquals(70, stats.getSpAtk());
        assertEquals(80, stats.getSpDef());
        
        // Test setStat with Stats enum
        stats.setStat(Stats.DEF, 70);
        assertEquals(70, stats.getDef());

        // Test modifyStat
        stats.modifyStat(Stats.ATK, 2.0);
        assertEquals(100, stats.getAtk()); // 55 * 2

        stats.modifyStat(Stats.SPE, 0.5);
        assertEquals(45, stats.getSpeed()); 

	}
	
	@Test
	public void testPokemonSpecies() {
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
