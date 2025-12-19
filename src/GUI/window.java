package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultCaret;
import java.lang.reflect.Field;
import java.util.function.Consumer;

import main.Globals;
import pokemon.PokemonSpecies;
import pokemon.SpeciesLibrary;

public class window extends JFrame {

    // CardLayout to manage the 3 pages
    private CardLayout cardLayout;
    private JPanel mainPanel;

    // Page IDs
    private static final String cardStartPage = "START";
    private static final String cardSelectPage = "SELECT";
    private static final String cardBattlePage = "BATTLE";

    // Battle Components
    private JTextArea battleLog;
    private Consumer<String> inputCallback; // Handles sending commands to the game loop

    // Custom Font
    private static Font pokemonFont;
    private static boolean fontLoaded = false;

    // Callbacks
    private Runnable onStartGame;
    private Consumer<Integer> onPokemonSelected;

    public window() {
        loadPokemonFontOnce();

        // Basic Frame Setup
        this.setTitle("Sickmons");
        this.setSize(Globals.defWinWidth, Globals.defWinHeight);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // Main Container with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        this.add(mainPanel);

        // Initialize the 3 Pages
        mainPanel.add(createStartPanel(), cardStartPage);
        mainPanel.add(createSelectionPanel(), cardSelectPage);
        mainPanel.add(createBattlePanel(), cardBattlePage);

        this.setVisible(true);
    }

    // ====PAGE 1: START SCREEN====
    private JPanel createStartPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Globals.defBgColor);

        // Title Label
        JLabel titleLabel = new JLabel("SICKMONS", SwingConstants.CENTER);
        titleLabel.setFont(pokemonFont.deriveFont(40f));
        titleLabel.setForeground(Color.BLACK); // Shadow
        titleLabel.setBounds(5, 50, Globals.defWinWidth, 60);

        JLabel titleLabelFg = new JLabel("SICKMONS", SwingConstants.CENTER);
        titleLabelFg.setFont(pokemonFont.deriveFont(40f));
        titleLabelFg.setForeground(Color.YELLOW);
        titleLabelFg.setBounds(0, 50, Globals.defWinWidth, 60);

        panel.add(titleLabelFg);
        panel.add(titleLabel);

        // Subtitle
        JLabel subLabel = new JLabel("A 201 / 3 group project", SwingConstants.CENTER);
        subLabel.setFont(pokemonFont.deriveFont(20f));
        subLabel.setForeground(Color.WHITE);
        subLabel.setBounds(0, 110, Globals.defWinWidth, 30);
        panel.add(subLabel);

        // Start Button
        JButton startBtn = createStyledButton("PRESS START");
        startBtn.setBounds((Globals.defWinWidth / 2) - 100, 400, 200, 50);
        startBtn.addActionListener(e -> {
            cardLayout.show(mainPanel, cardSelectPage);
        });

        panel.add(startBtn);

        return panel;
    }

    // ====PAGE 2: SELECTION SCREEN====
    private JPanel createSelectionPanel() {
        // gets all the pokemon fields in SpeciesLibrary
        Field[] pokemonFields = SpeciesLibrary.class.getFields();

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Globals.defBgColor);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel title = new JLabel("CHOOSE YOUR POKEMON", SwingConstants.CENTER);
        title.setFont(pokemonFont.deriveFont(20f));
        title.setForeground(Color.WHITE);
        panel.add(title, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(4, 5, 1, 1));
        grid.setOpaque(false);

        // gets all name from pokemonFields and creates buttons linked to them
        int i = 0;
        for (Field field : pokemonFields) {
            JPanel cell = new JPanel(new GridBagLayout());
            cell.setOpaque(false);

            JButton btn = createSelectionButton(field.getName(), i);
            cell.add(btn);

            grid.add(cell);
            i++;
        }

        panel.add(grid, BorderLayout.CENTER);

        return panel;
    }

    private JButton createSelectionButton(String name, int selectionIndex) {
        JButton btn = new JButton(name);
        btn.setFont(pokemonFont.deriveFont(10f));
        btn.setBackground(Color.WHITE);
        btn.setFocusPainted(false);

        // ✅ load image
        ImageIcon icon = loadPokemonIcon(name, 32);
        if (icon != null) {
            btn.setIcon(icon);

            // place image BESIDE text
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.setIconTextGap(8);
        }

        // preferred size (GridLayout respects this indirectly via wrapper panel)
        btn.setPreferredSize(new Dimension(140, 40));

        btn.addActionListener(e -> {
            if (onPokemonSelected != null) {
                onPokemonSelected.accept(selectionIndex);
            }
            cardLayout.show(mainPanel, cardBattlePage);
        });

        return btn;
    }

    private ImageIcon loadPokemonIcon(String pokemonName, int size) {
        String path = "/GUI/images/" + pokemonName + ".png";

        try (InputStream is = getClass().getResourceAsStream(path)) {
            if (is == null) {
                System.err.println("Missing image: " + path);
                return null;
            }

            byte[] bytes = is.readAllBytes();
            ImageIcon raw = new ImageIcon(bytes);
            Image scaled = raw.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    // ====PAGE 3: BATTLE SCREEN====
    // Layout designed to "fit the picture" of a standard Pokemon battle
    private JPanel createBattlePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        // ==TOP HALF: BATTLE SCENE==
        JPanel scenePanel = new JPanel(null);
        scenePanel.setPreferredSize(new Dimension(Globals.defWinWidth, 300));
        scenePanel.setBackground(new Color(240, 240, 240)); // Light gray background

        // Enemy HUD (Top Left)
        JPanel enemyHud = createHud("Enemy");
        enemyHud.setBounds(20, 30, 250, 60);
        scenePanel.add(enemyHud);

        // Enemy Sprite Placeholder (Top Right)
        JLabel enemySprite = new JLabel("Enemy", SwingConstants.CENTER);
        enemySprite.setOpaque(true);
        enemySprite.setBackground(new Color(200, 100, 100)); // Reddish
        enemySprite.setBounds(Globals.defWinWidth - 180, 50, 120, 120);
        scenePanel.add(enemySprite);

        // Player Sprite Placeholder (Bottom Left)
        JLabel playerSprite = new JLabel("Player", SwingConstants.CENTER);
        playerSprite.setOpaque(true);
        playerSprite.setBackground(new Color(100, 100, 200)); // Blueish
        playerSprite.setBounds(60, 150, 120, 120);
        scenePanel.add(playerSprite);

        // Player HUD (Bottom Right)
        JPanel playerHud = createHud("Player");
        playerHud.setBounds(Globals.defWinWidth - 270, 200, 250, 60);
        scenePanel.add(playerHud);

        panel.add(scenePanel, BorderLayout.CENTER);

        // ==BOTTOM HALF: DIALOG & CONTROLS==
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(Globals.defWinWidth, 200));
        bottomPanel.setBackground(new Color(50, 50, 50));
        bottomPanel.setBorder(new LineBorder(Color.BLACK, 4));

        // Text Log Area
        battleLog = new JTextArea();
        battleLog.setEditable(false);
        battleLog.setFont(new Font("Monospaced", Font.BOLD, 14));
        battleLog.setForeground(Color.WHITE);
        battleLog.setBackground(new Color(50, 50, 50));
        battleLog.setLineWrap(true);
        battleLog.setWrapStyleWord(true);

        // Auto-scroll logic
        DefaultCaret caret = (DefaultCaret)battleLog.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JScrollPane scroll = new JScrollPane(battleLog);
        scroll.setBorder(new EmptyBorder(10, 10, 10, 10));
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        bottomPanel.add(scroll, BorderLayout.CENTER);

        // Control Grid (Fight, Bag, etc.)
        JPanel controls = new JPanel(new GridLayout(2, 2, 5, 5));
        controls.setPreferredSize(new Dimension(250, 0));
        controls.setBackground(new Color(100, 100, 100));
        controls.setBorder(new EmptyBorder(5,5,5,5));

        // Mapping buttons to Game Inputs (1, 2, 3, 4)
        controls.add(createGameInputButton("FIGHT", "1"));
        controls.add(createGameInputButton("BAG", "2"));
        controls.add(createGameInputButton("POKEMON", "3"));
        controls.add(createGameInputButton("RUN", "4"));

        bottomPanel.add(controls, BorderLayout.EAST);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createHud(String label) {
        JPanel hud = new JPanel(new GridLayout(2, 1));
        hud.setBorder(new LineBorder(Color.BLACK, 2));
        hud.setBackground(Color.WHITE);

        JLabel name = new JLabel(" " + label);
        name.setFont(pokemonFont.deriveFont(14f));

        JProgressBar hp = new JProgressBar(0, 100);
        hp.setValue(100);
        hp.setForeground(Color.GREEN);

        hud.add(name);
        return hud;
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(pokemonFont.deriveFont(16f));
        btn.setBackground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
    }

    private JButton createGameInputButton(String text, String command) {
        JButton btn = new JButton(text);
        btn.setFont(pokemonFont.deriveFont(14f));
        btn.setBackground(new Color(240, 240, 240));
        btn.setFocusPainted(false);
        btn.addActionListener(e -> {
            if (inputCallback != null) {
                inputCallback.accept(command);
            }
        });
        return btn;
    }

    // ====LOGIC METHODS====

    //Appends text to the battle log
    public void appendBattleText(String text) {
        SwingUtilities.invokeLater(() -> {
            battleLog.append(text);
        });
    }

    // Sets the handler for when a Pokemon is chosen on Page 2
    public void setSelectionCallback(Consumer<Integer> callback) {
        this.onPokemonSelected = callback;
    }

    // Sets the handler for sending input commands to the game engine
    public void setInputCallback(Consumer<String> callback) {
        this.inputCallback = callback;
    }

    private static void loadPokemonFontOnce() {
        if (fontLoaded) return;

        // Font file is in src/GUI/PKMN RBYGSC.ttf
        try (InputStream is = window.class.getResourceAsStream("/GUI/PKMN RBYGSC.ttf")) {
            if (is != null) {
                Font f = Font.createFont(Font.TRUETYPE_FONT, is);
                GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(f);
                pokemonFont = f.deriveFont(Font.PLAIN, 20f);
            }
        } catch (IOException | FontFormatException ex) {
            // If font fails, we just keep fallback font (no crash)
        }

        fontLoaded = true;
    }
}