package moves;

import java.util.EnumSet;

import main.BattleContext;
import main.Globals.MoveCategory;
import main.Globals.MoveFlags;
import main.Globals.Types;
import pokemon.Pokemon;

public class Switch extends Move {
	private Pokemon replacer;
	
	public Switch(Pokemon replacer) {
		super(
				"[Switches out]",
				0,
				999, 
				MoveCategory.STATUS,
				Types.NONE, 
				999, 
				100, 
				"",
				EnumSet.noneOf(MoveFlags.class));
		
		this.replacer = replacer;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void beforeExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterExecution(Pokemon user, Pokemon target, BattleContext ctx) {
		ctx.requestSwitch(user, replacer);
	}
	
	public Pokemon getReplacer() { return replacer; }

}
