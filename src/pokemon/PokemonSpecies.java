package pokemon;

import java.util.ArrayList;

import abilities.Ability;
import moves.Move;
import main.Globals.Types;

public class PokemonSpecies {
	private final String name;
	private final StatsContainer baseStats;
	private final int weight;
	private final Types type1;
	private final Types type2;
	private final ArrayList<Ability> abilities;
	private final ArrayList<Move> learnableMoves;
	
	public PokemonSpecies(String name, StatsContainer baseStats, int weight, Types type1,
			Types type2, ArrayList<Ability> abilities, ArrayList<Move> learnableMoves) {
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
	public ArrayList<Ability> getAbilities() { return abilities; }
	public ArrayList<Move> getLearnableMoves() { return learnableMoves; }
	
	
}
