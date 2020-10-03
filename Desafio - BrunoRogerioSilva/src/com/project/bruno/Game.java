package com.project.bruno;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Game extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel gamePane;
	private int xx, xy;
	private JTextField entryCamp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game frame = new Game();
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
	public Game() {
		setUndecorated(true);
		setResizable(false);
		
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
				
				Game.this.setLocation(x - xx, y - xy);
			}
		});
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 760, 392);
		gamePane = new JPanel();
		gamePane.setBorder(new EmptyBorder(0, 0, 0, 0));
		gamePane.setBackground(SystemColor.inactiveCaptionBorder);
		setContentPane(gamePane);
		gamePane.setLayout(null);
		
		JButton btnExit = new JButton("X");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main main = new Main();
				main.setVisible(true);
				setVisible(false);
			}
		});
		btnExit.setFocusable(false);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(Color.RED);
		btnExit.setBounds(692, 0, 59, 36);
		gamePane.add(btnExit);
		
		JLabel title = new JLabel("Gerenciamento de campeonato");
		title.setFont(new Font("Tahoma", Font.BOLD, 21));
		title.setBounds(10, 13, 343, 26);
		gamePane.add(title);
		
		JLabel subTitle = new JLabel("Adicione jogos ao seu campeonato e obtenha os placares mínimos e máximos da temporada.");
		subTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		subTitle.setBounds(20, 50, 670, 26);
		gamePane.add(subTitle);
		
		entryCamp = new JTextField();
		entryCamp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		entryCamp.setToolTipText("Deve ser um número inteiro, positivo e menor que 1000");
		entryCamp.setBounds(10, 179, 343, 31);
		gamePane.add(entryCamp);
		entryCamp.setColumns(10);
		
		JLabel scoreTitle = new JLabel("Informe o placar:");
		scoreTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scoreTitle.setBounds(10, 148, 189, 20);
		gamePane.add(scoreTitle);
		
		JButton btnAdd = new JButton("Adicionar");
		btnAdd.setFocusable(false);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String score = entryCamp.getText();
				float numScore = Float.parseFloat(score);
				
				if(score.equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor, preencha o campo acima.", "Jogos", JOptionPane.ERROR_MESSAGE);
				}else if((numScore < 1000) && (numScore > 0) && (numScore % 1.0 == 0.0)){
					try {
						Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=CarroSynthwave2101");
					
						Statement stQuery = connection.createStatement();
					
						String query = "INSERT INTO systemDB.games (score) VALUES"
								+"('"+numScore+"')";
					
						//String query2 = "SELECT id FROM systemDB.championships WHERE "
					
						stQuery.execute(query);
						
						JOptionPane.showMessageDialog(null, "O jogo foi adicionado com sucesso!", "Jogos", JOptionPane.PLAIN_MESSAGE);
					}	
					catch (SQLException e1){
						e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null, "Formato inválido.", "Jogos", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd.setBackground(SystemColor.textHighlight);
		btnAdd.setForeground(SystemColor.text);
		btnAdd.setBounds(10, 221, 141, 36);
		gamePane.add(btnAdd);
		
		JButton btnTable = new JButton("Visualizar Tabela");
		btnTable.setFocusable(false);
		btnTable.setForeground(SystemColor.text);
		btnTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTable.setBackground(SystemColor.textHighlight);
		btnTable.setBounds(273, 339, 244, 42);
		gamePane.add(btnTable);
	}

}
