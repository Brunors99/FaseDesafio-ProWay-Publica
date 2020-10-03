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

public class DeleteGame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel deleteGame;
	private int xx,xy;
	private JTextField entryCamp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteGame frame = new DeleteGame();
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
	public DeleteGame() {
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
				
				DeleteGame.this.setLocation(x - xx, y - xy);
			}
		});
		
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 738, 343);
		deleteGame = new JPanel();
		deleteGame.setBackground(SystemColor.inactiveCaptionBorder);
		deleteGame.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(deleteGame);
		deleteGame.setLayout(null);
		
		JButton btnExit = new JButton("X");
		btnExit.setBounds(669, 0, 59, 36);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main main = new Main();
				main.setVisible(true);
				setVisible(false);
			}
		});
		deleteGame.setLayout(null);
		deleteGame.add(btnExit);
		btnExit.setFocusable(false);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(Color.RED);
		deleteGame.add(btnExit);
		
		JLabel title = new JLabel("Apagar um campeonato existente");
		title.setFont(new Font("Tahoma", Font.BOLD, 21));
		title.setBounds(10, 13, 401, 26);
		deleteGame.add(title);
		
		JLabel subTitle = new JLabel("Para remover um campeonato da lista, preencha o campo abaixo com o nome do mesmo.");
		subTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		subTitle.setBounds(20, 50, 649, 20);
		deleteGame.add(subTitle);
		
		entryCamp = new JTextField();
		entryCamp.setToolTipText("ex. Campeonato Estadual");
		entryCamp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		entryCamp.setBounds(10, 208, 343, 31);
		deleteGame.add(entryCamp);
		entryCamp.setColumns(10);
		
		JButton btnDelete = new JButton("Apagar Campeonato");
		btnDelete.setFocusable(false);
		btnDelete.setForeground(SystemColor.text);
		btnDelete.setBackground(SystemColor.textHighlight);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = entryCamp.getText();
				
				if(name.equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor, preencha o campo acima.", "Remover", JOptionPane.ERROR_MESSAGE);
				}else {
					try{
						Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=CarroSynthwave2101");
				
						Statement stQuery = connection.createStatement();
				
						String query = "SET SQL_SAFE_UPDATES = 0";
						
						String query2 = "DELETE FROM systemDB.championships WHERE name = '"+name+ "';";
						
						String query3 = "ALTER TABLE systemDB.championships AUTO_INCREMENT = 1";
						
						stQuery.execute(query);
						stQuery.execute(query2);
						stQuery.execute(query3);
				
						JOptionPane.showMessageDialog(null, "O campeonato foi removido com sucesso!", "Remover Campeonato", JOptionPane.PLAIN_MESSAGE);
				
					} catch (SQLException e1){
						e1.printStackTrace();
					}
					entryCamp.setText("");
				}
				
			}
		});
		btnDelete.setBounds(10, 250, 170, 36);
		deleteGame.add(btnDelete);
		
		JButton btnDeleteAll = new JButton("Apagar todos os campeonatos");
		btnDeleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					try{
						Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=CarroSynthwave2101");
				
						Statement stQuery = connection.createStatement();
			
						String query = "SET SQL_SAFE_UPDATES = 0";
						
						String query2 = "DELETE FROM systemDB.championships;";
						
						String query3 = "ALTER TABLE systemDB.championships AUTO_INCREMENT = 1";
						
						stQuery.execute(query);
						stQuery.execute(query2);
						stQuery.execute(query3);
				
						JOptionPane.showMessageDialog(null, "Todos os campeonatos foram apagados com sucesso!", "Apagar Campeonatos", JOptionPane.PLAIN_MESSAGE);
						
					} catch (SQLException e1){
						e1.printStackTrace();
					}
				}
		});
		btnDeleteAll.setFocusable(false);
		btnDeleteAll.setForeground(Color.WHITE);
		btnDeleteAll.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDeleteAll.setBackground(SystemColor.textHighlight);
		btnDeleteAll.setBounds(496, 296, 232, 36);
		deleteGame.add(btnDeleteAll);
		
	}
}
