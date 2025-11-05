package pokemon;

import java.util.List;

import abilities.*;
import main.Globals.Types;

public class SpeciesLibrary {
	public static final PokemonSpecies WEEZING = new PokemonSpecies(
			"Weezing", 
			StatsData.WEEZING, 
			9, 
			Types.POISON, 
			Types.NONE,
			List.of(new Levitate()),
			MovesetData.WEEZING);
	
}
