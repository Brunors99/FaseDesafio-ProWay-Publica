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
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class DisplayScore extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel displayPane;
	private int xx,xy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayScore frame = new DisplayScore();
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
	public DisplayScore() {
		setBackground(SystemColor.inactiveCaptionBorder);
		
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
				
				DisplayScore.this.setLocation(x - xx, y - xy);
			}
		});
		
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 411);
		displayPane = new JPanel();
		displayPane.setBackground(SystemColor.inactiveCaptionBorder);
		displayPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(displayPane);
		displayPane.setLayout(null);
		
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
		btnExit.setBounds(542, 0, 59, 36);
		displayPane.add(btnExit);
		
		JLabel title = new JLabel("Tabela do Campeonato");
		title.setFont(new Font("Tahoma", Font.BOLD, 21));
		title.setBounds(10, 13, 257, 23);
		displayPane.add(title);
		
		JScrollPane scrollList = new JScrollPane();
		scrollList.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, new Color(0, 0, 0), null));
		scrollList.setBounds(10, 76, 511, 324);
		displayPane.add(scrollList);
		
		JTextArea ScoreText = new JTextArea();
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=CarroSynthwave2101");
			
			Statement stQuery = connection.createStatement();
	
			ResultSet rs = stQuery.executeQuery("SELECT COUNT(*) FROM systemDB.championships");
			int row = 0;
	        while(rs.next()){
	            row = rs.getInt("COUNT(*)");                              
	        }
	        
			for(int i=1; i<=row; i++) {
				String query = "SELECT name FROM systemDB.championships WHERE id='"+i+"'";
				ResultSet rs2 = stQuery.executeQuery(query);
				String name = "";
				
				while(rs2.next()) {
					name = rs2.getString("name");
				}
				
				ScoreText.append(name + "\n");
			}
			
		}catch (SQLException e) {
	        e.printStackTrace();
	    }
		
		ScoreText.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, SystemColor.activeCaptionText, null, null));
		scrollList.setViewportView(ScoreText);
		ScoreText.setEditable(false);
	}
}
