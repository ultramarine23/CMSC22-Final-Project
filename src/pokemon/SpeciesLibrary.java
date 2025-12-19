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
		
	public static final PokemonSpecies URSALUNA = new PokemonSpecies(
		"Ursaluma", 
		StatsCalculator.realizeAllStats(StatsData.URSALUNA), 
		200,
		Types.NORMAL, 
		Types.GROUND, 
		List.of(), 
		MovesetData.URSALUNA);

	public static final PokemonSpecies HEATRAN = new PokemonSpecies(
		"Heatran", 
		StatsCalculator.realizeAllStats(StatsData.HEATRAN),
		948, 
		Types.STEEL, 
		Types.FIRE, 
		List.of(), 
		MovesetData.HEATRAN);
	
	public static final PokemonSpecies ZARUDE = new PokemonSpecies(
		"Zarude", 
		StatsCalculator.realizeAllStats(StatsData.ZARUDE), 
		154, 
		Types.DARK, 
		Types.GRASS, 
		List.of(), 
		MovesetData.ZARUDE);

	public static final PokemonSpecies ZAPDOS = new PokemonSpecies(
		"Zapdos", 
		StatsCalculator.realizeAllStats(StatsData.ZAPDOS), 
		116, 
		Types.ELECTRIC, 
		Types.FLYING, 
		List.of(), 
		MovesetData.ZAPDOS);

	public static final PokemonSpecies GLIMMORA = new PokemonSpecies(
		"Glimmora", 
		StatsCalculator.realizeAllStats(StatsData.GLIMMORA), 
		99,
		Types.ROCK, 
		Types.POISON, 
		List.of(), 
		MovesetData.GLIMMORA);

	public static final PokemonSpecies DARKRAI = new PokemonSpecies(
		"Darkrai", 
		StatsCalculator.realizeAllStats(StatsData.DARKRAI), 
		529, 
		Types.DARK, 
		Types.NONE, 
		List.of(), 
		MovesetData.DARKRAI);
	
	public static final PokemonSpecies PELLIPER = new PokemonSpecies(
		"Pelipper", 
		StatsCalculator.realizeAllStats(StatsData.PELIPPER), 
		61, 
		Types.WATER, 
		Types.FLYING, 
		List.of(),
		MovesetData.PELLIPER 
	);

	public static final PokemonSpecies HOOPA_UNBOUND = new PokemonSpecies(
		"Hoopa Unbound", 
		StatsCalculator.realizeAllStats(StatsData.HOOPA_UNBOUND), 
		19, 
		Types.PSYCHIC, 
		Types.GHOST, 
		List.of(), 
		MovesetData.HOOPA_UNBOUND);
	
}
