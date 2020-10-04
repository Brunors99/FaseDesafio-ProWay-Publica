package com.project.bruno;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.SystemColor;

public class AcessGame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel acessPane;
	private int xx,xy;
	private JTextField entryCamp;
	private JTextField entryCamp2;
	private String GameName = "";
	private String ScoreNum = "";
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AcessGame frame = new AcessGame();
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
	public AcessGame() {
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
				
				AcessGame.this.setLocation(x - xx, y - xy);
			}
		});
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 743, 525);
		acessPane = new JPanel();
		acessPane.setBackground(SystemColor.inactiveCaptionBorder);
		acessPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(acessPane);
		
		JButton btnExit = new JButton("X");
		btnExit.setBounds(675, 0, 59, 36);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main main = new Main();
				main.setVisible(true);
				setVisible(false);
			}
		});
		acessPane.setLayout(null);
		acessPane.add(btnExit);
		btnExit.setFocusable(false);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(Color.RED);
		acessPane.add(btnExit);
		
		JLabel title = new JLabel("Acessar um campeonato");
		title.setBounds(10, 13, 382, 26);
		title.setFont(new Font("Tahoma", Font.BOLD, 21));
		acessPane.add(title);
		
		JLabel lblNewLabel = new JLabel("Para gerenciar os jogos de um campeonato, informe o nome do mesmo no campo abaixo.");
		lblNewLabel.setBounds(20, 50, 666, 26);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		acessPane.add(lblNewLabel);
		
		entryCamp = new JTextField();
		entryCamp.setBounds(20, 87, 343, 31);
		entryCamp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		acessPane.add(entryCamp);
		entryCamp.setColumns(10);
		
		JButton btnAcess = new JButton("Acessar Campeonato");
		btnAcess.setFocusable(false);
		btnAcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameName = entryCamp.getText();
				
				String GameQuery = "";
				
				if(GameName.equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor, preencha o campo acima.", "Campeonato", JOptionPane.ERROR_MESSAGE);
				}else {
					try{
						Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=CarroSynthwave2101");
				
						Statement stQuery = connection.createStatement();
						
						String queryGameName = "SELECT name FROM systemDB.championships WHERE name='"+GameName+"'";
						ResultSet rs2 = stQuery.executeQuery(queryGameName);

						while(rs2.next()) {
						GameQuery = rs2.getString("name");
						}
						
						if(GameQuery.equals(GameName)) {
							JOptionPane.showMessageDialog(null, "O campeonato foi selecionado!", "Campeonato", JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null, "O campeonato não foi encontrado.", "Campeonato", JOptionPane.ERROR_MESSAGE);
					}
			
					}catch (SQLException e1){
						e1.printStackTrace();
					}
						entryCamp.setText("");
				}
			}
		});
		btnAcess.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAcess.setBackground(SystemColor.textHighlight);
		btnAcess.setForeground(SystemColor.text);
		btnAcess.setBounds(20, 129, 244, 42);
		acessPane.add(btnAcess);
		
		JLabel title2 = new JLabel("Gerenciamento do campeonato");
		title2.setFont(new Font("Tahoma", Font.BOLD, 21));
		title2.setBounds(10, 241, 343, 26);
		acessPane.add(title2);
		
		JLabel subTitle2 = new JLabel("Após acessar o campeonato, adicione novos jogos, placares e visualize a tabela geral.");
		subTitle2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		subTitle2.setBounds(20, 278, 623, 26);
		acessPane.add(subTitle2);
		
		JLabel addScore = new JLabel("Informe o placar:");
		addScore.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addScore.setBounds(20, 333, 137, 20);
		acessPane.add(addScore);
		
		entryCamp2 = new JTextField();
		entryCamp2.setToolTipText("Deve ser um número positivo, inteiro e menor que 1000");
		entryCamp2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		entryCamp2.setBounds(20, 364, 244, 31);
		acessPane.add(entryCamp2);
		entryCamp2.setColumns(10);
		
		JButton btnAdd = new JButton("Adicionar Jogo");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ScoreNum = entryCamp2.getText();
				float scoreFlt = Float.parseFloat(ScoreNum);
				
				if(ScoreNum.equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor, preencha o campo acima.", "Adicionar Jogo", JOptionPane.ERROR_MESSAGE);
				}else if((scoreFlt < 1000) && (scoreFlt > 0.0f) && (scoreFlt % 1.0f == 0.0f)) {	
						try {
							Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=CarroSynthwave2101");
						
							Statement stQuery = connection.createStatement();
						
							String champID = "SELECT id FROM systemDB.championships WHERE name='"+GameName+"'";
							ResultSet rs2 = stQuery.executeQuery(champID);

							while(rs2.next()) {
								champID = rs2.getString("id");
							}
						
							int champINT = Integer.parseInt(champID);
							int scoreInt = (int) scoreFlt;
						
							String query = "INSERT INTO systemDB.games (score, championships_id) VALUES"
									+ "('"+scoreInt+"','"+champINT+"')";
						
							stQuery.execute(query);
							
							
						
						
						}catch (SQLException e1) {
							e1.printStackTrace();
						}
				    	}else {
				    		JOptionPane.showMessageDialog(null, "Formato inválido.", "Adicionar Jogo", JOptionPane.ERROR_MESSAGE);
				    	}
						entryCamp2.setText("");
					}
		});
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd.setFocusable(false);
		btnAdd.setBackground(SystemColor.textHighlight);
		btnAdd.setBounds(20, 406, 244, 42);
		acessPane.add(btnAdd);
		
		JButton btnTable = new JButton("Visualizar Tabela");
		btnTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=CarroSynthwave2101");
			
					Statement stQuery = connection.createStatement();
					
					ResultSet rs = stQuery.executeQuery("SELECT COUNT(*) FROM systemDB.games");
					int row = 0;
			        while(rs.next()){
			            row = rs.getInt("COUNT(*)");                              
			        }
			        
			        if(row==0) {
			        	JOptionPane.showMessageDialog(null, "Não há jogos para mostrar.", "Tabela de Jogos", JOptionPane.ERROR_MESSAGE);
			        }else {
			        	DisplayScore displayScore = new DisplayScore();
						displayScore.setVisible(true);
						setVisible(false);
			        }
					
				}catch (SQLException e1){
					e1.printStackTrace();
				}
				}
		});
		btnTable.setForeground(Color.WHITE);
		btnTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTable.setFocusable(false);
		btnTable.setBackground(SystemColor.textHighlight);
		btnTable.setBounds(490, 472, 244, 42);
		acessPane.add(btnTable);
		
	}
}
