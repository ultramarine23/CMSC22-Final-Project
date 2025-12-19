package pokemon;

import java.util.List;

import moves.*;

public class MovesetData {
	public static final List<Move> WEEZING = List.of(
			new Flamethrower(),
			new BodySlam(),
			new SludgeBomb(),
			new Curse(),
			new DarkPulse(),
			new Self_Destruct()
			);
	
	public static final List<Move> DRAGAPULT = List.of(
			new Flamethrower(),
			new WillOWisp(),
			new Thunderbolt(),
			new Thunder(),
			new Curse(),
			new Acrobatics(),
			new Self_Destruct()
			);
	
	public static final List<Move> ZAMAZENTA = List.of(
			new Bite(),
			new BrickBreak(),
			new SandsearStorm(),
			new WildCharge(),
			new Self_Destruct()

	);

	public static final List<Move> DRAGONITE = List.of(
		new ThunderWave(),
		new IcePunch(),
		new Flamethrower(),
		new Hurricane(),
		new Self_Destruct()
	);

	public static final List<Move> ENAMORUS = List.of(
		new BodySlam(),
		new SludgeBomb(),
		new Moonblast(),
		new Facade(null, 0, 0, null, null, 0, 0, null, false, false),
		new Self_Destruct()
	);

	public static final List<Move> CERUDLEDGE = List.of(
		new WillOWisp(),
		new StoneEdge(),
		new Curse(),
		new BrickBreak(),
		new Self_Destruct()
	);

	public static final List<Move> URSALUNA = List.of(
		new Avalanche(),
		new Curse(),
		new DrainPunch(),
		new Facade(null, 0, 0, null, null, 0, 0, null, false, false),
		new BrickBreak(),
		new Self_Destruct()
	);

	public static final List<Move> HEATRAN = List.of(
		new StoneEdge(),
		new WillOWisp(),
		new BodySlam(),
		new Flamethrower(),
		new ScorchingSands(),
		new Self_Destruct()
	);

	public static final List<Move> ZARUDE = List.of(
		new Bite(),
		new Acrobatics(),
		new BodySlam(),
		new Facade(null, 0, 0, null, null, 0, 0, null, false, false),
		new BrickBreak(),
		new Self_Destruct()
	);

	public static final List<Move> ZAPDOS = List.of(
		new ThunderWave(),
		new Thunder(),
		new CloseCombat(),
		new BrickBreak(),
		new Acrobatics(),
		new Facade(null, 0, 0, null, null, 0, 0, null, false, false),
		new Hurricane(),
		new Self_Destruct()
	);

	public static List<Move> ALOMOMOLA = List.of(
		new Surf(),
		new BodySlam(),
		new Blizzard(),
		new Acrobatics(),
		new Self_Destruct()
	);

	public static List<Move> CERULEDGE = List.of(
		new WillOWisp(),
		new Flamethrower(),
		new BrickBreak(),
		new Curse(),
		new Self_Destruct()
	);

	public static List<Move> GLIMMORA = List.of(
		new SludgeWave(),
		new StoneEdge(),
		new SludgeBomb(),
		new SludgeWave(),
		new Self_Destruct()
	);

	public static List<Move> DARKRAI = List.of(
		new DrainPunch(),
		new Curse(),
		new Blizzard(),
		new BrickBreak(),
		new Thunder(),
		new Thunderbolt(),
		new WillOWisp(),
		new ThunderWave(),
		new Self_Destruct()
	);

	public static List<Move> PELLIPER = List.of(
		new Self_Destruct(),
		new Hurricane(),
		new BodySlam(),
		new Acrobatics(),
		new Surf(),
		new IceBeam(),
		new Blizzard(),
		new HydroPump()
	);

	public static List<Move> HOOPA_UNBOUND = List.of(
		
	);
}
