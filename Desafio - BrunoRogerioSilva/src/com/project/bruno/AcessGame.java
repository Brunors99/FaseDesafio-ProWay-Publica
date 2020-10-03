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

public class AcessGame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel acessPane;
	private int xx,xy;
	private JTextField entryCamp;

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
		setBounds(100, 100, 743, 302);
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
		entryCamp.setBounds(10, 189, 343, 31);
		entryCamp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		acessPane.add(entryCamp);
		entryCamp.setColumns(10);
		
		JButton btnAcess = new JButton("Acessar Campeonato");
		btnAcess.setFocusable(false);
		btnAcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = entryCamp.getText();
				
				if(name.equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor, preencha o campo acima.", "Acesso", JOptionPane.ERROR_MESSAGE);
				}else {
					try {
						Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=CarroSynthwave2101");
					
						Statement stQuery = connection.createStatement();
					
						String query = "SELECT name FROM systemDB.championships WHERE name='"+name+"'";
					
						stQuery.execute(query);
					
						entryCamp.setText("");
				
					
					}catch (SQLException e1){
						e1.printStackTrace();
					}
					Game game = new Game();
					game.setVisible(true);
					setVisible(false);
				}
			}
		});
		btnAcess.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAcess.setBackground(SystemColor.textHighlight);
		btnAcess.setForeground(SystemColor.text);
		btnAcess.setBounds(10, 231, 244, 42);
		acessPane.add(btnAcess);
	}
}
