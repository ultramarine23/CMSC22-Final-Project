package moves;

import java.util.EnumSet;

import main.BattleContext;
import main.Globals;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Status;
import main.Globals.Types;
import main.Globals.Weather;
import pokemon.Pokemon;

public class SandsearStorm extends Move {

	public SandsearStorm() {
		super(
				"Sandsear Storm", 
				100, 
				80, 
				MoveCategory.SPECIAL, 
				Types.GROUND, 
				0, 
				16, 
				"20% chance to burn foe(s). Can't miss in rain.", 
				EnumSet.noneOf(MoveFlags.class)
			);
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		if (ctx.getCurBattle().getWeather() == Weather.RAIN) {
			this.setAccuracy(999);
		}
	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		if (Globals.randomEngine.nextDouble() < 0.2) {
			target.applyStatus(Status.BURN);
		}
	}

}
