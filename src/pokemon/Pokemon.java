package pokemon;

import java.util.ArrayList;

import main.Globals.Types;
import moves.Move;

public class Pokemon {
	private PokemonSpecies pokemonSpecies;
	private StatsContainer currentStats;
	private Types type1;
	private Types type2;
	private ArrayList<Move> moves;
	
	
	public Pokemon(PokemonSpecies pokemonSpecies) {
		this.currentStats = pokemonSpecies.getBaseStats().clone();
		this.type1 = pokemonSpecies.getType1();
		this.type2 = pokemonSpecies.getType2();
		this.moves = pokemonSpecies.getLearnableMoves();
		
		System.out.println();
		System.out.println(String.valueOf(getCurrentStats().getHp()));
		takeDamage(10);
		System.out.println(String.valueOf(getCurrentStats().getHp()));
		
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
	
	// set-get functions
	public Types getType1() { return type1; }
	public Types getType2() { return type2; }
	public PokemonSpecies getPokemonSpecies() { return pokemonSpecies; } 
	public StatsContainer getBaseStats() { return pokemonSpecies.getBaseStats(); }
	public StatsContainer getCurrentStats() { return currentStats; }
	public ArrayList<Move> getMoves() { return moves; }

	public void setType1(Types type1) { this.type1 = type1; }
	public void setType2(Types type2) { this.type2 = type2; }
	public void setCurrentStats(StatsContainer currentStats) { this.currentStats = currentStats; }

	
	public static void main(String[] args) {
		
	}
}
