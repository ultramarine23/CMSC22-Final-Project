package pokemon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.Globals;
import main.Globals.BattleEvent;
import main.Globals.Status;
import main.Globals.Types;
import main.Globals.VolatileStatus;
import main.Globals.Stats;
import moves.Move;
import abilities.Ability;

public class Pokemon {
	private PokemonSpecies pokemonSpecies;
	private StatsContainer currentStats;
	private Types type1;
	private Types type2;
	private List<Move> moves;
	private Status curStatus;
	private ArrayList<VolatileStatus> volatileStatus; 
	private int statusTurns; // used for computation of toxic damage
	private boolean isAllied;
	private Map<Stats, Integer> statChanges;
	private List<Move> previousMoves;
	private boolean isFlinched; 
	private Ability ability;



	public Pokemon(PokemonSpecies pokemonSpecies, boolean isAllied) {
		this.pokemonSpecies = pokemonSpecies;
		this.currentStats = pokemonSpecies.getBaseStats().clone();
		this.type1 = pokemonSpecies.getType1();
		this.type2 = pokemonSpecies.getType2();
		this.moves = pokemonSpecies.getLearnableMoves();
		this.curStatus = Status.NONE;
		this.statusTurns = 0;
		this.volatileStatus = new ArrayList<VolatileStatus>();
		this.isAllied = isAllied;
		this.isFlinched = false;
		this.previousMoves = new ArrayList<Move>();
		this.ability = pokemonSpecies.getAbilities().getFirst(); //temporary

		// initialize statChanges
		this.statChanges = new HashMap<Stats, Integer>();
		this.statChanges.put(Stats.ATK, 0);
		this.statChanges.put(Stats.DEF, 0);
		this.statChanges.put(Stats.SPA, 0);
		this.statChanges.put(Stats.SPD, 0);
		this.statChanges.put(Stats.SPE, 0);
	}

	public void applyStatus(Status status) {
		//init first
		statusTurns = 0;
		curStatus = status;
		
		//check for type immunity
		if (curStatus == Status.NONE) {
			// poison types cannot be poisoned/toxiced
			if ((status == Status.POISON || status == Status.TOXIC)
					&& (type1 == Types.POISON || type2 == Types.POISON)) {
				return;
			}

			// fire types cannot be burned
			if (status == Status.BURN && (type1 == Types.FIRE || type2 == Types.FIRE)) {
				return;
			}

			// ice types cannot be frozen
			if (status == Status.FREEZE && (type1 == Types.ICE || type2 == Types.ICE)) {
				return;
			}

		
			//SET STATS depending on current status, after the immune type check
			//paralysis
			if (curStatus == Status.PARALYSIS) {
				StatsContainer currentStats = getCurrentStats();
				int newSpd = currentStats.getSpeed()/2;
				currentStats.setSpeed(newSpd);
			} else {
				//set to the orig stats
				StatsContainer currentStats = this.getCurrentStats();
				int newSpd = this.getPokemonSpecies().getBaseStats().getSpeed();
				currentStats.setSpeed(newSpd);
			}
			
			//freeze
			if (curStatus == Status.FREEZE) {
				//do nothing
			}
			


			statusTurns = 0;
			curStatus = status;

			System.out.println(pokemonSpecies.getName() + " was inflicted with " + curStatus.name() + "!");
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

	public void addMove(Move move) {
		moves.add(move);
	}

	public void removeMove(Move move) {
		moves.remove(move);
	}

	public void die() {
		Globals.curInstance.getEventBus().triggerEvent(BattleEvent.POKEMON_DIED, new Object[] { this });
	}

	public void incrementStatStage(Stats stat, int incrementAmount) {
		Integer statStage = statChanges.get(stat);
		statStage += incrementAmount;
		statStage = this.limitMod(statStage); //limit from -4 to 4
	}
	
	//return modified value
	private int applyModifier(int stat, int mod) {
        if (mod >= 0) {
            return (int)Math.round(stat * (2+mod) / 2); //buff
        } else {
            return (int)Math.round((stat * 2 / (2+mod))); //debuff
        }
    }
	
	//used for moves that increases atk for example
	public void applyModStat(Stats stat, int modifier) {
		
		switch (stat) {
		case Stats.ATK:
			this.currentStats.setAtk(applyModifier(this.currentStats.getAtk(), modifier));
		case Stats.DEF:
			this.currentStats.setDef(applyModifier(this.currentStats.getDef(), modifier));
		case Stats.SPA:
			this.currentStats.setSpAtk(applyModifier(this.currentStats.getSpAtk(), modifier));
		case Stats.SPD:
			this.currentStats.setSpDef(applyModifier(this.currentStats.getSpDef(), modifier));
		case Stats.SPE:
			this.currentStats.setSpeed(applyModifier(this.currentStats.getSpeed(), modifier));
		case Stats.HP:
			return;
		default:
			throw new RuntimeException("Invalid stat name.");
		}
	}
		
	//this func is to limit the modifiers to -4 to +4
    private int limitMod(int mod) {
        return Math.max(-4, Math.min(4, mod));
    }

	@Override
	public String toString() {
		// add string repr of name, types
		String repr = "\t" + pokemonSpecies.getName();
		repr += "\n\t  " + type1 + " / " + type2;

		// add string repr of HP bar
		repr += "\n\t  HP : " + getCurrentStats().getHp() + " / " + pokemonSpecies.getBaseStats().getHp();
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

		// add string repr of status
		repr += " [ Status : " + curStatus.name() + " ]";

		// add string repr of stats aside from HP
		repr += "\n\t  Atk " + getCurrentStats().getAtk() + " | Def " + getCurrentStats().getDef() + " | SpA "
				+ getCurrentStats().getSpAtk() + " | SpD " + getCurrentStats().getSpDef() + " | Spe "
				+ getCurrentStats().getSpeed();

		// add string repr of moves
		repr += "\n\t    ";
		for (int i = 0; i < 4; i++) {
			if (moves.size() > i) {
				Move move = moves.get(i);
				repr += "• " + move.getName() + " ".repeat(20 - move.getName().length());

			} else {
				repr += "• [no move]           ";
			}

			if (i % 2 == 1) {
				repr += "\n\t    ";
			}
		}
		return repr;
	}

	
	// getters
	public Types getType1() { return type1; }
	public Types getType2() { return type2; }
	public PokemonSpecies getPokemonSpecies() { return pokemonSpecies; } 
	public StatsContainer getBaseStats() { return pokemonSpecies.getBaseStats(); } //get the orig stats
	public StatsContainer getCurrentStats() { return currentStats; } 
	public List<Move> getMoves() { return moves; }
	public Status getCurStatus() { return curStatus; }
	public int getStatusTurns() { return statusTurns; }
	public List<VolatileStatus> getVolatileStatus() { return volatileStatus; }
	public void setFlinched(boolean newStatus) { isFlinched = newStatus; }
	
	public int getStatMod(Stats stat) {
	    return statChanges.getOrDefault(stat, 0);
	}
	public Ability getAbilty() {
		return this.ability;
	}
	
	
	//setters
	public void setType1(Types type1) { this.type1 = type1; }
	public void setType2(Types type2) { this.type2 = type2; }
	public void setCurrentStats(StatsContainer currentStats) { this.currentStats = currentStats; }
	public void setStatusTurns(int turn) {this.statusTurns = turn;}
	
	
	//helper funcs
	public boolean isAllied() { return isAllied; }
	public boolean isFlinched() { return isFlinched; }
	public void incrementStatusTurns() { statusTurns++; } 
	
	

}
