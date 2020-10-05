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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;

public class ListGame extends JFrame {

	/**
	 * Classe responsável por listar todos os campeonatos existentes no banco de dados.
	 * 
	 * @author Bruno Rogério da Silva
	 * @version 1.0
	 */
	
	private static final long serialVersionUID = 1L;
	private JPanel listGamePane;
	private int xx,xy;

	/**
	 * Inicia a aplicação.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListGame frame = new ListGame();
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
	public ListGame() {
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
				
				ListGame.this.setLocation(x - xx, y - xy);
			}
		});
		
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
		btnExit.setBounds(519, 0, 59, 36);
		listGamePane.add(btnExit);
		
		JLabel title = new JLabel("Lista dos campeonatos adicionados");
		title.setFont(new Font("Tahoma", Font.BOLD, 21));
		title.setBounds(10, 13, 375, 23);
		listGamePane.add(title);
		
		JScrollPane scrollList = new JScrollPane();
		scrollList.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), null, new Color(255, 255, 255), new Color(255, 255, 255)));
		scrollList.setViewportBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollList.setBounds(10, 95, 475, 308);
		listGamePane.add(scrollList);
		
		JTextArea textList = new JTextArea();
		
			try {
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=CarroSynthwave2101");
				
				Statement stQuery = connection.createStatement();
		
				//Comando de consulta ao banco de dados para obter o número de registros.
				ResultSet rs = stQuery.executeQuery("SELECT COUNT(*) FROM systemDB.championships");
				int row = 0;
		        while(rs.next()){
		            row = rs.getInt("COUNT(*)");                              
		        }
		        
		        //Comando de consulta ao banco para a obtenção dos nomes dos campeonatos cadastrados.
				for(int i=1; i<=row; i++) {
					String query = "SELECT name FROM systemDB.championships WHERE id='"+i+"'";
					ResultSet rs2 = stQuery.executeQuery(query);
					String name = "";
					
					while(rs2.next()) {
						name = rs2.getString("name");
					}
					//Neste textArea os campeonatos são apresentados.
					textList.append(name + "\n");
				}
				
			}catch (SQLException e) {
		        e.printStackTrace();
		    }
		
		textList.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, new Color(0, 0, 0), null, null));
		textList.setEditable(false);
		textList.setLineWrap(true);
		textList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollList.setViewportView(textList);
		setUndecorated(true);
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 589, 414);
		listGamePane = new JPanel();
		listGamePane.setBackground(SystemColor.inactiveCaptionBorder);
		listGamePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(listGamePane);
		listGamePane.setLayout(null);
	}
}
