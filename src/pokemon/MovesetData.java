package pokemon;

import java.util.List;

import moves.*;

public class MovesetData {
	public static final List<Move> WEEZING = List.of(
			new Flamethrower(),
			new BodySlam(),
			new SludgeBomb(),
			new Curse(),
			new DarkPulse()
			);
	
	public static final List<Move> DRAGAPULT = List.of(
			new Flamethrower()
			);
	
	public static final List<Move> ZAMAZENTA = List.of(
			new Bite(),
			new BrickBreak(),
			new SandsearStorm(),
			new WildCharge()

	);

	public static final List<Move> DRAGONITE = List.of(
		new ThunderWave(),
		new IcePunch(),
		new Flamethrower(),
		new Hurricane()
	);

	public static final List<Move> ENAMORUS = List.of(
		new BodySlam(),
		new SludgeBomb(),
		new Moonblast(),
		new Facade(null, 0, 0, null, null, 0, 0, null, false, false)
	);

	public static final List<Move> CERUDLEDGE = List.of(
		new WillOWisp(),
		new StoneEdge(),
		new Curse(),
		new BrickBreak()
	);
	
}
