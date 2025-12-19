package main;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import GUI.window;
import pokemon.Pokemon;
import pokemon.SpeciesLibrary;

public class Main {

    private window gameWindow;
    private PipedOutputStream commandSender;

    public Main() {
        SwingUtilities.invokeLater(() -> {
            // Initialize the GUI
            gameWindow = new window();

            // Set up the callback for when the user selects a Pokemon
            gameWindow.setSelectionCallback((index) -> {
                startBattle(index);
            });

            // Set up the callback for when the user clicks
            gameWindow.setInputCallback((command) -> {
                sendCommandToGame(command);
            });
        });
    }

    // ====Sets up the I/O piping and starts the battle thread====
    public void startBattle(int selectionIndex) {
        // Create pipes to hijack System.in and System.out
        PipedInputStream inPipe = new PipedInputStream();
        commandSender = new PipedOutputStream();

        PipedOutputStream outPipe = new PipedOutputStream();
        PipedInputStream outPipeReader = new PipedInputStream();

        try {
            // Connect the GUI buttons to the Game's System.in
            inPipe.connect(commandSender);

            // Connect the Game's System.out to the GUI text area
            outPipe.connect(outPipeReader);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // ====THREAD 1: The Game Logic====
        Thread battleThread = new Thread(() -> {
            // Redirect System streams for this thread context
            System.setIn(inPipe);
            System.setOut(new PrintStream(outPipe));

            ArrayList<Pokemon> alliesArr = new ArrayList<>();
            ArrayList<Pokemon> enemiesArr = new ArrayList<>();

            // ========THIS IS A VERY TEMPORARY TESTING FIX PLEASE CHANGE==============
            // Logic to choose pokemon based on selection button
            // Mapping indices to actual species in SpeciesLibrary
            if (selectionIndex == 0) {
                alliesArr.add(new Pokemon(SpeciesLibrary.WEEZING, true));
            } else if (selectionIndex == 1) {
                alliesArr.add(new Pokemon(SpeciesLibrary.ZAMAZENTA, true));
            } else {
                alliesArr.add(new Pokemon(SpeciesLibrary.SCIZOR, true));
            }

            // =======THIS AS WELL===========
            // Enemy (Defaulting to Weezing for now, or you can randomize this)
            enemiesArr.add(new Pokemon(SpeciesLibrary.WEEZING, false));

            BattleInstance newBattle = new BattleInstance(alliesArr, enemiesArr);

            // Notify GUI battle has started
            System.out.println("Battle Started!");

            newBattle.runBattle();

        }, "Battle-Logic-Thread");

        // ====THREAD 2: The Output Reader====
        // Reads text output from the game and puts it on the GUI
        Thread outputThread = new Thread(() -> {
            Scanner sc = new Scanner(outPipeReader);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                gameWindow.appendBattleText(line + "\n");
            }
            sc.close();
        }, "GUI-Output-Thread");

        battleThread.start();
        outputThread.start();
    }

    // Sends a string command (e.g. "1") to the running BattleInstance
    private void sendCommandToGame(String command) {
        if (commandSender != null) {
            try {
                // BattleInstance's Scanner expects a line, so we add newline
                commandSender.write((command + "\n").getBytes());
                commandSender.flush();
            } catch (IOException e) {
                e.printStackTrace();
                gameWindow.appendBattleText("[Error sending command]\n");
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}