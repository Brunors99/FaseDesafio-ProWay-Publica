package com.project.bruno;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class AddGame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel addGamePane;
	private int xx,xy;
	private JTextField entryCamp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddGame frame = new AddGame();
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
	public AddGame() {
		setBackground(SystemColor.inactiveCaptionBorder);
		setUndecorated(true);
		setFocusCycleRoot(false);
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
				
				AddGame.this.setLocation(x - xx, y - xy);
			}
		});
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 704, 343);
		addGamePane = new JPanel();
		addGamePane.setBackground(SystemColor.inactiveCaptionBorder);
		addGamePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(addGamePane);
		addGamePane.setLayout(null);
		
		JLabel title = new JLabel("Adicionar um novo campeonato");
		title.setFont(new Font("Tahoma", Font.BOLD, 21));
		title.setBounds(10, 19, 442, 23);
		addGamePane.add(title);
		
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
		btnExit.setBounds(633, 0, 59, 36);
		addGamePane.add(btnExit);
		
		JLabel subTitle = new JLabel("Para incluir um novo campeonato, preencha o campo abaixo com o nome desejado.");
		subTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		subTitle.setBounds(20, 53, 617, 23);
		addGamePane.add(subTitle);
		
		entryCamp = new JTextField();
		entryCamp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		entryCamp.setToolTipText("ex. Campeonato Municipal");
		entryCamp.setBounds(10, 209, 343, 31);
		addGamePane.add(entryCamp);
		entryCamp.setColumns(10);
		
		JButton btnAddGame = new JButton("Adicionar");
		btnAddGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = entryCamp.getText();
				
				if(name.equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor, preencha o campo acima.", "Cadastro", JOptionPane.ERROR_MESSAGE);
				}else {
					try{
						Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=CarroSynthwave2101");
				
						Statement stQuery = connection.createStatement();
				
						String query = "INSERT INTO systemDB.championships (name) VALUES"
								+ "('"+name+"');";
				
						stQuery.execute(query);
				
						JOptionPane.showMessageDialog(null, "O campeonato foi cadastrado com sucesso!", "Cadastro", JOptionPane.PLAIN_MESSAGE);
				
					} catch (SQLException e1){
						e1.printStackTrace();
					}
					entryCamp.setText("");
				}
			}
		});
		btnAddGame.setFocusPainted(false);
		btnAddGame.setForeground(SystemColor.text);
		btnAddGame.setBackground(SystemColor.textHighlight);
		btnAddGame.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddGame.setBounds(10, 251, 244, 42);
		addGamePane.add(btnAddGame);
	}
}
