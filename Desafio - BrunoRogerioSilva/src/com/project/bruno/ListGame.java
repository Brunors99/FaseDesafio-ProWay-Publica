package com.project.bruno;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
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

public class ListGame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel listGamePane;
	private int xx,xy;

	/**
	 * Launch the application.
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
	 * Create the frame.
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
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 732, 414);
		listGamePane = new JPanel();
		listGamePane.setBackground(new Color(255, 255, 255));
		listGamePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(listGamePane);
		listGamePane.setLayout(null);
		
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
		btnExit.setBounds(664, 0, 59, 36);
		listGamePane.add(btnExit);
		
		JLabel title = new JLabel("Lista dos campeonatos adicionados");
		title.setFont(new Font("Tahoma", Font.BOLD, 21));
		title.setBounds(10, 13, 375, 23);
		listGamePane.add(title);
		
		JScrollPane scrollList = new JScrollPane();
		scrollList.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), null, new Color(255, 255, 255), null));
		scrollList.setViewportBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollList.setBounds(10, 95, 545, 308);
		listGamePane.add(scrollList);
		
		JTextArea textList = new JTextArea();
		textList.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, new Color(255, 255, 255), null, null));
		textList.setEditable(false);
		textList.setLineWrap(true);
		textList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollList.setViewportView(textList);
		setUndecorated(true);
		setResizable(false);
		
		/*--------------------------------------------------*/
		ArrayList<String> list = new ArrayList<>();
		list.add("Campeonato Municipal");
		list.add("Campeonato Mundial");
		
		StringBuffer sb = new StringBuffer();
		
		for(String sGames : list) {
			sb.append("> "+sGames+"\n");
			String finalList = sb.toString();
			textList.setText(finalList);
		}
		/*--------------------------------------------*/
	}
}
