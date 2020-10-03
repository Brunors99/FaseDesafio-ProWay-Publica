package com.project.bruno;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import java.awt.SystemColor;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		mainPane.setBackground(SystemColor.inactiveCaptionBorder);
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
		btnListOfGames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=CarroSynthwave2101");
					
					Statement stQuery = connection.createStatement();
			
					ResultSet rs = stQuery.executeQuery("SELECT COUNT(*) FROM systemDB.championships");
					int row = 0;
			        while(rs.next()){
			            row = rs.getInt("COUNT(*)");                              
			        }
					
					if(row==0) {
						JOptionPane.showMessageDialog(null, "Não existem campeonatos cadastrados.", "Lista de Campeonatos", JOptionPane.ERROR_MESSAGE);
					}else {
						ListGame listGame = new ListGame();
						listGame.setVisible(true);
						setVisible(false);
				}
				}catch (SQLException e1) {
			        e1.printStackTrace();
			    }
			}
		});
		
		btnListOfGames.setForeground(SystemColor.text);
		btnListOfGames.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnListOfGames.setFocusPainted(false);
		btnListOfGames.setBackground(SystemColor.textHighlight);
		btnListOfGames.setBounds(400, 279, 244, 42);
		mainPane.add(btnListOfGames);
		
		JButton btnModifyGame = new JButton("Acessar um campeonato");
		btnModifyGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=CarroSynthwave2101");
					
					Statement stQuery = connection.createStatement();
			
					ResultSet rs = stQuery.executeQuery("SELECT COUNT(*) FROM systemDB.championships");
					int row = 0;
			        while(rs.next()){
			            row = rs.getInt("COUNT(*)");                              
			        }
					
					if(row==0) {
						JOptionPane.showMessageDialog(null, "Não existe campeonato algum para acessar.", "Acessar Campeonatos", JOptionPane.ERROR_MESSAGE);
					}else {
						AcessGame acessGame = new AcessGame();
						acessGame.setVisible(true);
						setVisible(false);
				}
				}catch (SQLException e1) {
			        e1.printStackTrace();
			    }
			}
		});
		btnModifyGame.setForeground(SystemColor.text);
		btnModifyGame.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModifyGame.setFocusPainted(false);
		btnModifyGame.setBackground(SystemColor.textHighlight);
		btnModifyGame.setBounds(400, 359, 244, 42);
		mainPane.add(btnModifyGame);
		
		JButton btnDeleteGame = new JButton("Apagar um campeonato");
		btnDeleteGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteGame deleteGame = new DeleteGame();
				deleteGame.setVisible(true);
				setVisible(false);
			}
		});
		btnDeleteGame.setForeground(SystemColor.text);
		btnDeleteGame.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDeleteGame.setFocusPainted(false);
		btnDeleteGame.setBackground(SystemColor.textHighlight);
		btnDeleteGame.setBounds(400, 438, 244, 42);
		mainPane.add(btnDeleteGame);
		
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
