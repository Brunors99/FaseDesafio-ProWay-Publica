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
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class AcessGame extends JFrame {

	/**
	 * Este Frame possibilita a inserção de jogos aos campeonatos, estabelecendo a relação entre jogo e o seu respectivo campeonato
	 * selecionado. É possível ver o calculo dos placares mínimos, máximos e dos recordes, porém ainda não está totalmente funcional.
	 * 
	 * @author Bruno Rogério da Silva
	 * @version 1.0
	 */
	
	
	private static final long serialVersionUID = 1L;
	private JPanel acessPane;
	private int xx,xy;
	private JTextField entryCamp;
	private JTextField entryCamp2;
	private String GameName = "";
	private String ScoreNum = "";


	/**
	 * Inicia a aplicação.
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
	 * Cria o Frame.
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
				
				//Verifica se o campo de texto está vazio, disparando um aviso ao usuário caso verdadeiro.
				if(GameName.equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor, preencha o campo acima.", "Campeonato", JOptionPane.ERROR_MESSAGE);
				}else {
					try{
						Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=CarroSynthwave2101");
				
						Statement stQuery = connection.createStatement();
						
						//Analisa se o nome informado consta no banco, caso verdadeiro o campeonato e selecionado, caso falso um aviso é disparado.
						String queryGameName = "SELECT name FROM systemDB.championships WHERE name='"+GameName+"'";
						ResultSet rs2 = stQuery.executeQuery(queryGameName);

						while(rs2.next()) {
						GameQuery = rs2.getString("name");
						}
						
						if(GameQuery.equals(GameName)) {
							//Aviso de sucesso
							JOptionPane.showMessageDialog(null, "O campeonato foi selecionado!", "Campeonato", JOptionPane.INFORMATION_MESSAGE);
						}else {
							//Aviso de falha
							JOptionPane.showMessageDialog(null, "O campeonato não foi encontrado.", "Campeonato", JOptionPane.ERROR_MESSAGE);
					}
			
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
		title2.setBounds(10, 197, 343, 26);
		acessPane.add(title2);
		
		JLabel subTitle2 = new JLabel("Após acessar o campeonato, adicione novos jogos, placares e visualize a tabela geral.");
		subTitle2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		subTitle2.setBounds(20, 234, 623, 26);
		acessPane.add(subTitle2);
		
		JLabel addScore = new JLabel("Informe o placar:");
		addScore.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addScore.setBounds(20, 285, 137, 20);
		acessPane.add(addScore);
		
		entryCamp2 = new JTextField();
		entryCamp2.setToolTipText("Deve ser um número positivo, inteiro e menor que 1000");
		entryCamp2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		entryCamp2.setBounds(20, 316, 244, 31);
		acessPane.add(entryCamp2);
		entryCamp2.setColumns(10);
		
		JButton btnAdd = new JButton("Adicionar Jogo");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ScoreNum = entryCamp2.getText();
				float scoreFlt = Float.parseFloat(ScoreNum);
				
				if(ScoreNum.equals("")) {
					//Confere se o placar informado atende ao padrão numérico requerido, caso falso um aviso de erro é emitido e o usuário deve
					//tentar novamente.
					JOptionPane.showMessageDialog(null, "Por favor, preencha o campo acima.", "Adicionar Jogo", JOptionPane.ERROR_MESSAGE);
				}else if((scoreFlt < 1000) && (scoreFlt > 0.0f) && (scoreFlt % 1.0f == 0.0f)) {	
						try {
							Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=CarroSynthwave2101");
						
							Statement stQuery = connection.createStatement();
						
							//Busca o ID do campeonato informado na caixa de texto do botão de acesso ao campeonato.
							String champID = "SELECT id FROM systemDB.championships WHERE name='"+GameName+"'";
							ResultSet rs2 = stQuery.executeQuery(champID);

							while(rs2.next()) {
								champID = rs2.getString("id");
							}
						
							int champINT = Integer.parseInt(champID);
							int scoreInt = (int) scoreFlt;
						
							//Atribui o placar informado a tabela do placar no banco e o ID do campeonato que foi informado anteriromente à foreign key
							//da tabela do placar.
							String query = "INSERT INTO systemDB.games (score, championships_id) VALUES"
									+ "('"+scoreInt+"','"+champINT+"')";
						
							stQuery.execute(query);
							
						}catch (SQLException e1) {
							e1.printStackTrace();
						}
				    	}else {
				    		//Aviso de erro mencionado anteriormente
				    		JOptionPane.showMessageDialog(null, "Formato inválido.", "Adicionar Jogo", JOptionPane.ERROR_MESSAGE);
				    	}
						entryCamp2.setText("");
					}
		});
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd.setFocusable(false);
		btnAdd.setBackground(SystemColor.textHighlight);
		btnAdd.setBounds(20, 358, 244, 42);
		acessPane.add(btnAdd);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 473, 666, 179);
		acessPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 17));
		scrollPane.setViewportView(textArea);
		
		JButton btnTable = new JButton("Visualizar Tabela");
		btnTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GameName = entryCamp.getText();
				int scoreInt = 0;
				String idMatch = "";
				int idMatchInt = 0;
	    		String idChamps = "";
	    		int idChampsInt = 0;
	    		String minSeason = "";
	    		int minSeasonInt = 0;
	    		int maxSeasonInt = 0;
	    		int minRecord = 0;
	    		int maxRecord = 0;
				
				try{
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/systemDB?user=root&password=CarroSynthwave2101");
			
					Statement stQuery = connection.createStatement();
					
					//Verifica se há registros na tabela de jogo.
					ResultSet rs = stQuery.executeQuery("SELECT COUNT(*) FROM systemDB.games");
					int row = 0;
			        while(rs.next()){
			            row = rs.getInt("COUNT(*)");                              
			        }
			        
			        if(row==0) {
			        	//Mensagem de erro caso não existam jogos na tabela.
			        	JOptionPane.showMessageDialog(null, "Não há jogos para mostrar.", "Tabela de Jogos", JOptionPane.ERROR_MESSAGE);
			        }else {
			        	//Busca o ID do campeonato informado anteriormente.	
			        		ResultSet rs1 = stQuery.executeQuery("SELECT id FROM systemDB.championships WHERE name='"+GameName+"'");
			        		while(rs1.next()) {
			        			idChamps = rs1.getString("id");
			        		}
			        		
			        		idChampsInt = Integer.parseInt(idChamps);
			        		
			    			ResultSet rs2 = stQuery.executeQuery("SELECT championships_id FROM systemDB.games JOIN championships ON games.championships_id = '"+idChampsInt+"'");
			    			while(rs2.next()) {
			    				idMatch = rs2.getString("championships_id");
			    			}
			    			
			    			idMatchInt = Integer.parseInt(idMatch);
			    	        
			    			//Trecho de código que deve calcular, adicionar e apresentar o placar, minimos e maximos da temporada e os recordes com base
			    			//no ID do campeonato e na foreign key da tabela de jogos que a referencia. Porém ainda não está corrigido.
			    			for(int i=1; i<=row; i++) {
			    				String query2 = "SELECT championships.name, games.score from systemDB.championships left join systemDB.games on championships_id ='"+idMatchInt+"'";
			    				ResultSet rs3 = stQuery.executeQuery(query2);
			    				String score = "";
			    				String name = "";
			    				
			    				while(rs3.next()) {
			    					score = rs3.getString("score");
			    					scoreInt = Integer.parseInt(score);
			    					name = rs3.getString("name");
			    					minSeason = rs3.getString("score");
			    					minSeasonInt = Integer.parseInt(minSeason);
			    					if(scoreInt<minSeasonInt) {
			    						minSeasonInt=scoreInt;
			    						minRecord++;
			    					}else if(scoreInt>maxSeasonInt) {
			    						maxSeasonInt=scoreInt;
			    						maxRecord++;
			    					}
			    				}
			    				//Apresenta as informações em um JTextArea.
			    				textArea.append("Campeonato: " + name + " | Pontuação: " + score + " | Mínimo da Temporada: " + minSeasonInt + " | Máximo da Temporada: "
			    				+ maxSeasonInt + " | Quebra recorde min. "+ minRecord + " | Quebra recorde max. "+ maxRecord +"\n");
			    			}
			    		
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
		btnTable.setBounds(490, 680, 244, 42);
		acessPane.add(btnTable);
		
		JLabel tableTitle = new JLabel("Tabela do Jogo");
		tableTitle.setFont(new Font("Tahoma", Font.BOLD, 21));
		tableTitle.setBounds(10, 427, 315, 26);
		acessPane.add(tableTitle);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 743, 732);
		acessPane = new JPanel();
		acessPane.setBackground(SystemColor.inactiveCaptionBorder);
		acessPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(acessPane);
	}
}
