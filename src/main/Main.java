package main;

import GUI.window;
import moves.Move;
import pokemon.Pokemon;
import pokemon.PokemonSpecies;
import pokemon.SpeciesLibrary;

import javax.swing.*;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        // Create pipes with larger buffer to prevent blocking
        final int PIPE_SIZE = 8192; // 8KB buffer
        PipedInputStream inPipe = new PipedInputStream(PIPE_SIZE);
        commandSender = new PipedOutputStream();

        PipedOutputStream outPipe = new PipedOutputStream();
        PipedInputStream outPipeReader = new PipedInputStream(PIPE_SIZE);

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
            try {
                // Redirect System streams for this thread context
                System.setIn(inPipe);
                // Use auto-flush for immediate output
                System.setOut(new PrintStream(outPipe, true));
                System.setErr(new PrintStream(outPipe, true));

                ArrayList<Pokemon> alliesArr = new ArrayList<>();
                ArrayList<Pokemon> enemiesArr = new ArrayList<>();

                // Logic to choose pokemon based on selection button
                Field[] pokemonFields = SpeciesLibrary.class.getFields();
                int i = 0;
                for (Field field : pokemonFields) {
                    if (i == selectionIndex) {
                        try {
                            PokemonSpecies species = (PokemonSpecies) field.get(null);
                            alliesArr.add(new Pokemon(species, true));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    i++;
                }

                // Enemy (Defaulting to Weezing)
                int placeHolder = Globals.randomEngine.nextInt(19);
                i = 0;
                for (Field field : pokemonFields) {
                    if (i == placeHolder) {
                        try {
                            PokemonSpecies species = (PokemonSpecies) field.get(null);
                            enemiesArr.add(new Pokemon(species, true));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    i++;
                }

                final Pokemon playerMon = alliesArr.get(0);
                final Pokemon enemyMon = enemiesArr.get(0);

                BattleInstance newBattle = new BattleInstance(alliesArr, enemiesArr);

                // Pass HP update callback to battle instance
                newBattle.setHPUpdateCallback(() -> updateHPBars(playerMon, enemyMon));

                if (!alliesArr.isEmpty() && !enemiesArr.isEmpty()) {
                    String[] moveNames = extractMoveNames(playerMon);

                    // Pass this data to the Window
                    SwingUtilities.invokeLater(() -> {
                        gameWindow.setupBattleUI(
                                playerMon.getPokemonSpecies().getName(),
                                enemyMon.getPokemonSpecies().getName(),
                                moveNames
                        );
                    });
                }

                // Notify GUI battle has started
                System.out.println("Battle Started!");

                newBattle.getContext().applyWeather(Globals.Weather.RAIN, 3);

                newBattle.runBattle();

            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Battle thread error: " + e.getMessage());
            }

        }, "Battle-Logic-Thread");

        // ====THREAD 2: The Output Reader====
        Thread outputThread = new Thread(() -> {
            try {
                Scanner sc = new Scanner(outPipeReader);
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    gameWindow.appendBattleText(line + "\n");
                }
                sc.close();
            } catch (Exception e) {
                System.err.println("Output thread ended: " + e.getMessage());
            }
        }, "GUI-Output-Thread");

        battleThread.start();
        outputThread.start();
    }

    private String[] extractMoveNames(Pokemon p) {
        String[] names = new String[4];
        List<Move> moves = p.getMoves();

        // Initialize with default
        for(int i=0; i<4; i++) names[i] = "-";

        if (moves != null) {
            for (int i = 0; i < 4; i++) {
                if (i < moves.size() && moves.get(i) != null) {
                    names[i] = moves.get(i).getName();
                }
            }
        }
        return names;
    }

    // Update HP bars in GUI
    public void updateHPBars(Pokemon player, Pokemon enemy) {
        SwingUtilities.invokeLater(() -> {
            gameWindow.updateHealthBars(
                    player.getCurrentStats().getHp(),
                    player.getBaseStats().getHp(),
                    enemy.getCurrentStats().getHp(),
                    enemy.getBaseStats().getHp()
            );
        });
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