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
	
	public static final PokemonSpecies ALOMOMOLA = new PokemonSpecies(
			"Alomomola", 
			StatsCalculator.realizeAllStats(StatsData.ALOMOMOLA),
			150, 
			Types.WATER, 
			Types.NONE, 
			List.of(), 
			MovesetData.ALOMOMOLA);

	public static final PokemonSpecies CERULEDGE = new PokemonSpecies(
			"Ceruledge",
			StatsCalculator.realizeAllStats(StatsData.CERULEDGE),
			100,
			Types.GHOST,
			Types.FIRE,
			List.of(),
			MovesetData.CERULEDGE);
		
	public static final PokemonSpecies DRAGONITE = new PokemonSpecies(
			"Dragonite", 
			StatsCalculator.realizeAllStats(StatsData.DRAGONITE), 
			150, 
			Types.DRAGON, 
			Types.NONE, 
			List.of(), 
			MovesetData.DRAGONITE);

	public static final PokemonSpecies ENAMORUS = new PokemonSpecies(
		"Enamorus",
		StatsCalculator.realizeAllStats(StatsData.ENAMORUS),
		48,
		Types.FAIRY,
		Types.FLYING,
		List.of(),
		MovesetData.ENAMORUS);
}
