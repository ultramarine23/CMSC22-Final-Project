package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JPanel;

import pokemon.Pokemon;
import pokemon.SpeciesLibrary;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
	JFrame window;
	JPanel titleNamePanel;
	JLabel titleLabel;
	JButton titleButton;
	JPanel titleButPanel;
	Container con;
	Font titleFont = new Font("Times New Roman", Font.PLAIN, 60);
	
	public Main() {
		// create game window
		window = new JFrame();
		window.setTitle(Globals.gameName);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		window.setSize(Globals.defWinWidth, Globals.defWinHeight);
		window.setResizable(false);
		window.setVisible(true);
		
		con = window.getContentPane();
		
		// create title panel
		titleNamePanel = new JPanel();
		titleNamePanel.setBounds(
				Globals.defWinWidth / 5, 
				Globals.defWinHeight / 5,
				Globals.defWinWidth * 3 / 5,
				Globals.defWinHeight / 4);
		titleNamePanel.setBackground(Globals.defBgColor);
		
		titleLabel = new JLabel();
		titleLabel.setText("ROGUELIKE");
		titleLabel.setForeground(Color.white);
		titleLabel.setFont(titleFont);
		
		
		// create start button
		titleButPanel = new JPanel();
		titleButPanel.setBounds(
				Globals.defWinWidth / 3, 
				(Globals.defWinHeight * 2) / 3,
				Globals.defWinWidth / 3,
				Globals.defWinHeight / 6
				);
		
		titleButton = new JButton("Start");
		titleButton.setForeground(Color.white);
		titleButton.setBackground(Globals.defBgColor);
		
		titleButPanel.add(titleButton);
		titleNamePanel.add(titleLabel);
		con.add(titleNamePanel);
		con.add(titleButPanel);
		// good
		
		
		// create battle
		ArrayList<Pokemon> alliesArr = new ArrayList<Pokemon>();
		ArrayList<Pokemon> enemiesArr = new ArrayList<Pokemon>();
		
		alliesArr.add(new Pokemon(SpeciesLibrary.WEEZING));
		
		BattleInstance newBattle = new BattleInstance(alliesArr, enemiesArr);
		newBattle.runBattle();
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
