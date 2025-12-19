package pokemon;

import java.util.List;

import abilities.Ability;
import moves.Move;
import main.Globals.Types;

public class PokemonSpecies {
	private final String name;
	private final StatsContainer baseStats;
	private final int weight;
	private final Types type1;
	private final Types type2;
	private final List<Ability> abilities;
	private final List<Move> learnableMoves;
	
	public PokemonSpecies(String name, StatsContainer baseStats, int weight, Types type1,
			Types type2, List<Ability> abilities, List<Move> learnableMoves) {
		this.name = name;
		this.baseStats = baseStats;
		this.weight = weight;
		this.type1 = type1;
		this.type2 = type2;
		this.abilities = abilities;
		this.learnableMoves = learnableMoves;
	}

	// set-get methods
	public String getName() { return name; }
	public StatsContainer getBaseStats() { return baseStats; }
	public int getWeight() { return weight; }
	public Types getType1() { return type1; }
	public Types getType2() { return type2; }
	public List<Ability> getAbilities() { return abilities; }
	public List<Move> getLearnableMoves() { return learnableMoves; }
	
	
	
}
