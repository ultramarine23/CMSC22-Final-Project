package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JPanel;
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
		window.setTitle(Globals.game_name);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		window.setSize(Globals.def_win_width, Globals.def_win_height);
		window.setResizable(false);
		window.setVisible(true);
		
		con = window.getContentPane();
		
		// create title panel
		titleNamePanel = new JPanel();
		titleNamePanel.setBounds(
				Globals.def_win_width / 5, 
				Globals.def_win_height / 5,
				Globals.def_win_width * 3 / 5,
				Globals.def_win_height / 4);
		titleNamePanel.setBackground(Globals.def_bg_color);
		
		titleLabel = new JLabel();
		titleLabel.setText("ROGUELIKE");
		titleLabel.setForeground(Color.white);
		titleLabel.setFont(titleFont);
		
		
		// create start button
		titleButPanel = new JPanel();
		titleButPanel.setBounds(
				Globals.def_win_width / 3, 
				(Globals.def_win_height * 2) / 3,
				Globals.def_win_width / 3,
				Globals.def_win_height / 6
				);
		
		titleButton = new JButton("Start");
		titleButton.setForeground(Color.white);
		titleButton.setBackground(Globals.def_bg_color);
		
		titleButPanel.add(titleButton);
		titleNamePanel.add(titleLabel);
		con.add(titleNamePanel);
		con.add(titleButPanel);
		// good
		
		
		// create battle
		Summon[] alliesArr = {new Summon()};
		Summon[] enemiesArr = {new Summon()};
		
		BattleInstance newBattle = new BattleInstance(alliesArr, enemiesArr);
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
