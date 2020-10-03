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
				
				String name = entryCamp.getText();
				int idGame = 0;
				
				if(name.equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor, preencha o campo acima.", "Acesso", JOptionPane.ERROR_MESSAGE);
				}else {
					try {
						Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=CarroSynthwave2101");
					
						Statement stQuery = connection.createStatement();
						
						ResultSet rs = stQuery.executeQuery("SELECT COUNT(*) FROM systemDB.championships");
						int row = 0;
				        while(rs.next()){
				            row = rs.getInt("COUNT(*)");                              
				        }
				        
				        ResultSet rs2 = stQuery.executeQuery("SELECT id FROM systemDB.championships WHERE name = '"+name+"'");
				        
				        for(int i=1; i<=row; i++) {
				        	while(rs2.next()) {
				        		idGame = rs2.getInt("id");
				        	}
				        }
						
						String query2 = "INSERT INTO systemDB.games (championships_id) VALUES"
								+ "('"+idGame+"');";
						
						stQuery.executeUpdate(query2);
					
						entryCamp.setText("");
				
					
					}catch (SQLException e1){
						e1.printStackTrace();
					}
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
		addScore.setBounds(20, 354, 137, 20);
		acessPane.add(addScore);
		
		entryCamp2 = new JTextField();
		entryCamp2.setBounds(20, 385, 244, 31);
		acessPane.add(entryCamp2);
		entryCamp2.setColumns(10);
		
		JButton btnAdd = new JButton("Adicionar Jogo");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String score = entryCamp2.getText();
				
				if(score.equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor, preencha o campo acima.", "Adicionar Jogo", JOptionPane.ERROR_MESSAGE);
				}else {
					try {
						Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=CarroSynthwave2101");
						
						Statement stQuery = connection.createStatement();
						
						String query = "INSERT INTO systemDB.games (score) VALUES"
								+ "('"+score+"');";
						
						stQuery.execute(query);
					
					}catch (SQLException e1){
						e1.printStackTrace();
					}
				}
			}
		});
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd.setFocusable(false);
		btnAdd.setBackground(SystemColor.textHighlight);
		btnAdd.setBounds(20, 427, 244, 42);
		acessPane.add(btnAdd);
		
	}
}
