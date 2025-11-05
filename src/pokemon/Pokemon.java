package pokemon;

import java.util.List;

import main.Globals.Types;
import moves.Move;

public class Pokemon {
	private PokemonSpecies pokemonSpecies;
	private StatsContainer currentStats;
	private Types type1;
	private Types type2;
	private List<Move> moves;
	
	
	public Pokemon(PokemonSpecies pokemonSpecies) {
		this.pokemonSpecies = pokemonSpecies;
		this.currentStats = pokemonSpecies.getBaseStats().clone();
		this.type1 = pokemonSpecies.getType1();
		this.type2 = pokemonSpecies.getType2();
		this.moves = pokemonSpecies.getLearnableMoves();		
	}
	
	public void takeDamage(int amount) {
		int curHp = getCurrentStats().getHp();
		
		getCurrentStats().setHp(Integer.max(curHp - amount, 0));
		
	}
	
	public void healHealth(int amount) {
		int curHp = getCurrentStats().getStat("hp");
		int maxHp = getBaseStats().getStat("hp");
		
		getCurrentStats().setStat("hp", Integer.min(maxHp, curHp + amount));
	}
	
	public void addMove(Move move) { moves.add(move); }
	public void removeMove(Move move) { moves.remove(move); }
	
	public void die() {
		
	}
	
	
	
	@Override
	public String toString() {
		return "Pokemon [\n\tSPECIES : " + pokemonSpecies.getName() + "\n\tSTATS : " + currentStats.toString() + "\n\tTYPE : " + type1
				+ " / " + type2 + "\n\tMOVES : " + moves + "\n]";
	}

	// set-get functions
	public Types getType1() { return type1; }
	public Types getType2() { return type2; }
	public PokemonSpecies getPokemonSpecies() { return pokemonSpecies; } 
	public StatsContainer getBaseStats() { return pokemonSpecies.getBaseStats(); }
	public StatsContainer getCurrentStats() { return currentStats; }
	public List<Move> getMoves() { return moves; }

	public void setType1(Types type1) { this.type1 = type1; }
	public void setType2(Types type2) { this.type2 = type2; }
	public void setCurrentStats(StatsContainer currentStats) { this.currentStats = currentStats; }

	
	public static void main(String[] args) {
		
	}
}
