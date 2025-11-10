package pokemon;

import java.util.List;

import main.Globals;
import main.Globals.BattleEvent;
import main.Globals.Status;
import main.Globals.Types;
import moves.Move;

public class Pokemon {
	private PokemonSpecies pokemonSpecies;
	private StatsContainer currentStats;
	private Types type1;
	private Types type2;
	private List<Move> moves;
	private Status curStatus;
	
	public Pokemon(PokemonSpecies pokemonSpecies) {
		this.pokemonSpecies = pokemonSpecies;
		this.currentStats = pokemonSpecies.getBaseStats().clone();
		this.type1 = pokemonSpecies.getType1();
		this.type2 = pokemonSpecies.getType2();
		this.moves = pokemonSpecies.getLearnableMoves();	
		this.curStatus = Status.NONE;
	}
	
	
	public void applyStatus(Status status) {
		if (curStatus == Status.NONE) {
			curStatus = status;
		}
	}
	
	
	public void takeDamage(int amount) {
		int curHp = getCurrentStats().getHp();
		
		getCurrentStats().setHp(Integer.max(curHp - amount, 0));
		
		if (getCurrentStats().getHp() == 0) {
			die();
		}
	}
	
	public void healHealth(int amount) {
		int curHp = getCurrentStats().getStat("hp");
		int maxHp = getBaseStats().getStat("hp");
		
		getCurrentStats().setStat("hp", Integer.min(maxHp, curHp + amount));
	}
	
	public void addMove(Move move) { moves.add(move); }
	public void removeMove(Move move) { moves.remove(move); }
	
	public void die() {
		Globals.curInstance.getEventBus().triggerEvent(BattleEvent.POKEMON_DIED, new Object[] {this});
	}
	
	
	
	@Override
	public String toString() {
		// add string repr of name, types
		String repr = pokemonSpecies.getName();
		repr += "\n  " + type1 + " / " + type2;
		
		// add string repr of HP bar
		repr += "\n  HP : " + getCurrentStats().getHp() + " / " + pokemonSpecies.getBaseStats().getHp();
		repr += "  [";
		for (int i = 1; i <= 16; i++) {
			double threshold = i * pokemonSpecies.getBaseStats().getHp() / 16;
			if (getCurrentStats().getHp() >= threshold) {
				repr += "■";
			} else {
				repr += " ";
			}
		} 
		repr += "]";
		
		// add string repr of stats aside from HP
		repr += "\n  Atk " + getCurrentStats().getAtk() + " | Def " + getCurrentStats().getDef() + " | SpA " + getCurrentStats().getSpAtk() + " | SpD " + getCurrentStats().getSpDef() + " | Spe " + getCurrentStats().getSpeed();
		
		// add string repr of moves
		repr += "\n    ";
		for (int i = 0; i < 4; i++) {
			if (moves.size() > i) {
				Move move = moves.get(i);
				repr += "• " + move.getName() + " ".repeat(20 - move.getName().length());
				
			} else {
				repr += "• [no move]           ";
			}
			
			if (i % 2 == 1) {
				repr += "\n    ";
			}
		}
		return repr;
	}

	// set-get functions
	public Types getType1() { return type1; }
	public Types getType2() { return type2; }
	public PokemonSpecies getPokemonSpecies() { return pokemonSpecies; } 
	public StatsContainer getBaseStats() { return pokemonSpecies.getBaseStats(); }
	public StatsContainer getCurrentStats() { return currentStats; }
	public List<Move> getMoves() { return moves; }
	public Status getCurStatus() { return curStatus; }

	public void setType1(Types type1) { this.type1 = type1; }
	public void setType2(Types type2) { this.type2 = type2; }
	public void setCurrentStats(StatsContainer currentStats) { this.currentStats = currentStats; }

	
	public static void main(String[] args) {
		
	}
}
