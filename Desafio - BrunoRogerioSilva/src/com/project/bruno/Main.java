package com.project.bruno;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;
import java.awt.SystemColor;

public class Main extends JFrame {

	private JPanel mainPane;
	private int xx,xy;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setUndecorated(true);
		/*---------------------------------------- Torna possivel a movimentacao do JFrame ----------------------------------------*/
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				xx = e.getX();
				xy = e.getY();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				
				int x = arg0.getXOnScreen();
				int y = arg0.getYOnScreen();
				
				Main.this.setLocation(x - xx, y - xy);
			}
		});
		/*------------------------------------------------------------------------*/
		setFont(new Font("Arial", Font.PLAIN, 12));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 918, 563);
		mainPane = new JPanel();
		mainPane.setBackground(new Color(255, 255, 255));
		mainPane.setBorder(null);
		setContentPane(mainPane);
		mainPane.setLayout(null);
		
		JPanel imagePresentation = new JPanel();
		imagePresentation.setForeground(Color.WHITE);
		imagePresentation.setBorder(null);
		imagePresentation.setBackground(Color.DARK_GRAY);
		imagePresentation.setBounds(0, 0, 363, 551);
		mainPane.add(imagePresentation);
		
		JLabel image = new JLabel("");
		image.setIcon(new ImageIcon(Main.class.getResource("/images/basketball-court.jpg")));
		imagePresentation.add(image);
		
		/*------------------------------------------------------------------------*/
		
		JLabel title = new JLabel("Seja Bem-Vindo ao ScoreBasket!");
		title.setFont(new Font("Tahoma", Font.BOLD, 21));
		title.setBounds(390, 32, 347, 34);
		mainPane.add(title);
		
		JLabel subTitle = new JLabel("Sistema de gerenciamento para campeonatos de basquete.");
		subTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		subTitle.setBounds(400, 68, 415, 34);
		mainPane.add(subTitle);
		
		/*---------------------------------------- Botao para a adicao de campeonatos ----------------------------------------*/
		
		JButton btnNewGame = new JButton("Adicione um novo campeonato");
		btnNewGame.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				AddGame addGame = new AddGame();
				addGame.setVisible(true); 
				setVisible(false);
			}
		});
		
		btnNewGame.setFocusPainted(false);
		btnNewGame.setForeground(SystemColor.text);
		btnNewGame.setBackground(SystemColor.textHighlight);
		btnNewGame.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewGame.setBounds(400, 200, 244, 42);
		mainPane.add(btnNewGame);
		
		/*------------------------------------------------------------------------*/
		
		JButton btnListOfGames = new JButton("Listar os campeonatos");
		btnListOfGames.setForeground(SystemColor.text);
		btnListOfGames.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnListOfGames.setFocusPainted(false);
		btnListOfGames.setBackground(SystemColor.textHighlight);
		btnListOfGames.setBounds(400, 279, 244, 42);
		mainPane.add(btnListOfGames);
		
		JButton btnDeleteGame = new JButton("Apagar um campeonato");
		btnDeleteGame.setForeground(SystemColor.text);
		btnDeleteGame.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDeleteGame.setFocusPainted(false);
		btnDeleteGame.setBackground(SystemColor.textHighlight);
		btnDeleteGame.setBounds(400, 438, 244, 42);
		mainPane.add(btnDeleteGame);
		
		JButton btnModifyGame = new JButton("Acessar um campeonato");
		btnModifyGame.setForeground(SystemColor.text);
		btnModifyGame.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModifyGame.setFocusPainted(false);
		btnModifyGame.setBackground(SystemColor.textHighlight);
		btnModifyGame.setBounds(400, 359, 244, 42);
		mainPane.add(btnModifyGame);
		
		JButton btnExit = new JButton("X");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFocusable(false);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(Color.RED);
		btnExit.setBounds(849, 0, 59, 36);
		mainPane.add(btnExit);
		
		JLabel version = new JLabel("v1.0");
		version.setVerticalAlignment(SwingConstants.BOTTOM);
		version.setHorizontalAlignment(SwingConstants.RIGHT);
		version.setBounds(855, 538, 53, 14);
		mainPane.add(version);
	}
}