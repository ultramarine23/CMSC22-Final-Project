package main;

import java.util.Random;
import java.awt.Color;

public class Globals {
	public static String gameName = "Sickmons Roguelike";
	public static int defWinWidth = (1280 / 3) * 2;
	public static int defWinHeight = (720 / 3) * 2;
	public static Color defBgColor = Color.darkGray;
	
	public enum Types {
		WATER, FIRE, GRASS, ELECTRIC, ICE, DARK, PSYCHIC, FAIRY, GHOST, NONE,
		DRAGON, NORMAL, FIGHTING, GROUND, ROCK, STEEL, FLYING, POISON, BUG
	}
	
	public enum MoveCategory {
		PHYSICAL, SPECIAL, STATUS
	}
	
	public enum Weather {
		SUN, RAIN, SNOW, SAND, NONE
	}
	
	public enum Stats {
		HP, ATK, DEF, SPA, SPD, SPE
	}
	
	public enum BattleEvent {
		POKEMON_DIED
	}
	
	public enum Status {
		BURN, PARALYSIS, FREEZE, SLEEP, NONE, POISON, TOXIC, // non-volatile statuses
		FLINCH, CONFUSE // volatile statuses
	}
	
	public static Random randomEngine = new Random();
	public static BattleInstance curInstance;
}
