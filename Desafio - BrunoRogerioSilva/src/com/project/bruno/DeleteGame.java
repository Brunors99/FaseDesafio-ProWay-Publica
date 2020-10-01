package com.project.bruno;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
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
		setBounds(100, 100, 794, 414);
		deleteGame = new JPanel();
		deleteGame.setBackground(new Color(255, 255, 255));
		deleteGame.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(deleteGame);
		deleteGame.setLayout(null);
		
		JButton btnExit = new JButton("X");
		btnExit.setBounds(725, 0, 59, 36);
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
		
		JLabel subTitle = new JLabel("Para remover um campeonato da lista, preencha o campo abaixo com o nome do mesmo");
		subTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		subTitle.setBounds(20, 50, 634, 20);
		deleteGame.add(subTitle);
		
		entryCamp = new JTextField();
		entryCamp.setToolTipText("ex. Campeonato Estadual");
		entryCamp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		entryCamp.setBounds(10, 278, 343, 31);
		deleteGame.add(entryCamp);
		entryCamp.setColumns(10);
		
		JButton btnDelete = new JButton("Apagar Campeonato");
		btnDelete.setForeground(SystemColor.text);
		btnDelete.setBackground(SystemColor.textHighlight);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Campeonato removido com sucesso!", "Remover Campeonato", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnDelete.setBounds(10, 320, 244, 42);
		deleteGame.add(btnDelete);
		
	}
}
