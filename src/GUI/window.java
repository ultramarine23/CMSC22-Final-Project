package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Globals;

public class window extends JFrame {

    private JPanel titlePanel;
    private JLabel titleLabel;

    private JPanel titleChildPanel;
    private JLabel titleChildLabel;

    private JButton titleButton;
    private JPanel titleButPanel;

    private Container con;

    private static Font pokemonFont = new Font("PKMN RBYGSC", Font.PLAIN, 20);
    private static boolean fontLoaded = false;

    // Keep the no-arg constructor for GUI.runner
    public window() {
    }

    public window(Runnable onStart) {

        // Load/register the custom font once
        loadPokemonFontOnce();

        // defaults
        this.setSize(Globals.defWinWidth, Globals.defWinHeight);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        // sets title
        this.setTitle(Globals.gameName);

        // getter
        con = this.getContentPane();
        con.setBackground(new Color(252, 255, 253));

        // TITLE
        titleLabel = new JLabel("SICKMONS");
        titleLabel.setForeground(Globals.defBgColor);
        titleLabel.setFont(pokemonFont.deriveFont(Font.PLAIN, 35f));

        titlePanel = new JPanel();
        titlePanel.setLayout(null);
        titlePanel.setOpaque(false);
        titlePanel.setBounds(
                Globals.defWinWidth / 3,
                Globals.defWinHeight / 4,
                Globals.defWinWidth / 3,
                Globals.defWinHeight / 6
        );
        titleLabel.setBounds(10, 10, titlePanel.getWidth(), titlePanel.getHeight());
        titlePanel.add(titleLabel);
        con.add(titlePanel);

        // SUBTITLE
        titleChildLabel = new JLabel("Roguelike");
        titleChildLabel.setForeground(Globals.defBgColor);
        titleChildLabel.setFont(pokemonFont.deriveFont(Font.PLAIN, 20f));

        titleChildPanel = new JPanel();
        titleChildPanel.setLayout(null);
        titleChildPanel.setOpaque(false);
        titleChildPanel.setBounds(
                (Globals.defWinWidth * 2) / 5 + 20,
                (Globals.defWinHeight * 2) / 6 + 3,
                Globals.defWinWidth / 3,
                Globals.defWinHeight / 6
        );
        titleChildLabel.setBounds(5, 5, titleChildPanel.getWidth(), titleChildPanel.getHeight());
        titleChildPanel.add(titleChildLabel);
        con.add(titleChildPanel);

        // START BUTTON PANEL
        titleButPanel = new JPanel();
        titleButPanel.setOpaque(false);
        titleButPanel.setBounds(
                Globals.defWinWidth / 3,
                (Globals.defWinHeight * 2) / 3,
                Globals.defWinWidth / 3,
                Globals.defWinHeight / 6
        );

        titleButton = new JButton("Start");
        titleButton.setFont(pokemonFont);
        titleButton.setForeground(Color.white);
        titleButton.setBackground(Globals.defBgColor);

        // Hook button to callback if provided
        if (onStart != null) {
            titleButton.addActionListener(e -> onStart.run());
        }

        titleButPanel.add(titleButton);
        con.add(titleButPanel);

        this.setVisible(true);
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
