package pokemon;

import java.util.List;

import abilities.*;
import main.Globals.Types;
import main.StatsCalculator;

public class SpeciesLibrary {
	public static final PokemonSpecies WEEZING = new PokemonSpecies(
			"Weezing", 
			StatsCalculator.realizeAllStats(StatsData.WEEZING), 
			9, 
			Types.POISON, 
			Types.NONE,
			List.of(new Levitate()),
			MovesetData.WEEZING);
	
	public static final PokemonSpecies ZAMAZENTA = new PokemonSpecies(
			"Zamazenta",
			StatsCalculator.realizeAllStats(StatsData.ZAMAZENTA),
			210,
			Types.FIGHTING,
			Types.NONE,
			List.of(),
			MovesetData.ZAMAZENTA);
}
