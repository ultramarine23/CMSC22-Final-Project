package main;

import pokemon.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
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

	@Test
	public void testPokemonInitialization() {
		Pokemon p = new Pokemon(SpeciesLibrary.WEEZING, true);

		assertNotNull(p.getPokemonSpecies(), "Pokemon species should not be null");
		assertEquals("Weezing", p.getPokemonSpecies().getName());
		assertNotNull(p.getMoves(), "Pokemon should have moves");
	}

	@Test
	public void testBattleInstanceCreation() {
		ArrayList<Pokemon> allies = new ArrayList<>();
		ArrayList<Pokemon> enemies = new ArrayList<>();

		allies.add(new Pokemon(SpeciesLibrary.WEEZING, true));
		enemies.add(new Pokemon(SpeciesLibrary.ZAMAZENTA, false));

		BattleInstance battle = new BattleInstance(allies, enemies);

		assertNotNull(battle, "BattleInstance should be created");
		assertNotNull(battle.getContext(), "BattleContext should not be null");
	}

	@Test
	void testPokemonStats() {
		PokemonSpecies species = new PokemonSpecies(
				"Weezing",
				StatsCalculator.realizeAllStats(StatsData.WEEZING),
				60,
				Types.POISON,
				Types.NONE,
				List.of(new SwiftSwim()),
				MovesetData.WEEZING); // Weezing has 10 moves available

		assertEquals("Weezing", species.getName());
		assertNotNull(species.getBaseStats(), "Base stats should not be null");
		assertEquals(60, species.getWeight());
		assertEquals(Types.POISON, species.getType1());
		assertEquals(Types.NONE, species.getType2());
		assertEquals(1, species.getAbilities().size());
		assertEquals(10, species.getLearnableMoves().size());
	}
}

