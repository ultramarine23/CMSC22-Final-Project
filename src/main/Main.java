package main;



import java.util.ArrayList;
import javax.swing.SwingUtilities;

import GUI.window;

import pokemon.Pokemon;
import pokemon.SpeciesLibrary;


public class Main {

	
	public Main() {
        SwingUtilities.invokeLater(() -> {
            // Create the GUI window and hook the Start button to battle start
            new window(() -> startBattle());
        });
	}
	
    public static void startBattle() {
        Thread battleThread = new Thread(() -> {
            ArrayList<Pokemon> alliesArr = new ArrayList<>();
            ArrayList<Pokemon> enemiesArr = new ArrayList<>();

            alliesArr.add(new Pokemon(SpeciesLibrary.WEEZING, true));
            alliesArr.add(new Pokemon(SpeciesLibrary.ZAPDOS, true));
            alliesArr.add(new Pokemon(SpeciesLibrary.ZAMAZENTA, true));
            enemiesArr.add(new Pokemon(SpeciesLibrary.WEEZING, false));

            BattleInstance newBattle = new BattleInstance(alliesArr, enemiesArr);
            newBattle.runBattle();
        }, "battle-thread");

        battleThread.start();
    }
	
	public static void main(String[] args) {
		new Main();
	}
}
