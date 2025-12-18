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

		// initialize statChanges
		this.statChanges = new HashMap<Stats, Integer>();
		this.statChanges.put(Stats.ATK, 0);
		this.statChanges.put(Stats.DEF, 0);
		this.statChanges.put(Stats.SPA, 0);
		this.statChanges.put(Stats.SPD, 0);
		this.statChanges.put(Stats.SPE, 0);
	}

	public void applyStatus(Status status) {
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
	}

	public void calculateCurrentStats() {
		// temporarily reset current stats
		setCurrentStats(getBaseStats());

		// calculate stat stage changes
		for (Stats stat : Stats.values()) {
			int value = statChanges.get(stat);

			switch (stat) {
			case Stats.ATK:
				currentStats.setAtk((int) (currentStats.getAtk() * ((0.5 * value) + 1)));
				return;
			case Stats.DEF:
				currentStats.setDef((int) (currentStats.getDef() * ((0.5 * value) + 1)));
				return;
			case Stats.SPA:
				currentStats.setSpAtk((int) (currentStats.getSpAtk() * ((0.5 * value) + 1)));
				return;
			case Stats.SPD:
				currentStats.setSpDef((int) (currentStats.getSpDef() * ((0.5 * value) + 1)));
				return;
			case Stats.SPE:
				currentStats.setSpeed((int) (currentStats.getSpeed() * ((0.5 * value) + 1)));
				return;
			case Stats.HP:
				return;

			}
		}

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

	// set-get functions
	public Types getType1() {
		return type1;
	}

	public Types getType2() {
		return type2;
	}

	public PokemonSpecies getPokemonSpecies() {
		return pokemonSpecies;
	}

	public StatsContainer getBaseStats() {
		return pokemonSpecies.getBaseStats();
	}

	public StatsContainer getCurrentStats() {
		calculateCurrentStats();
		return currentStats;
	}

	public List<Move> getMoves() {
		return moves;
	}

	public Status getCurStatus() {
		return curStatus;
	}

	public boolean isAllied() {
		return isAllied;
	}

	public int getStatusTurns() {
		return statusTurns;
	}

	public List<VolatileStatus> getVolatileStatus() {
		return volatileStatus;
	}

	public boolean isFlinched() {
		return isFlinched;
	}

	public void setType1(Types type1) {
		this.type1 = type1;
	}

	public void setType2(Types type2) {
		this.type2 = type2;
	}

	public void setCurrentStats(StatsContainer currentStats) {
		this.currentStats = currentStats;
	}

	public void incrementStatusTurns() {
		statusTurns++;
	}

	public void setFlinched(boolean newStatus) {
		isFlinched = newStatus;
	}
}
