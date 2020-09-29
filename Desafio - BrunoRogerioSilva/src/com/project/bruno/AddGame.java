package com.project.bruno;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AddGame extends JFrame {

	private JPanel addGamePane;
	private int xx,xy;

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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 918, 563);
		addGamePane = new JPanel();
		addGamePane.setBackground(new Color(255, 255, 255));
		addGamePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(addGamePane);
		addGamePane.setLayout(null);
		
		JLabel exit_btn = new JLabel("X");
		exit_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}});
		exit_btn.setHorizontalAlignment(SwingConstants.CENTER);
		exit_btn.setFont(new Font("Tahoma", Font.BOLD, 20));
		exit_btn.setBounds(869, 0, 43, 34);
		addGamePane.add(exit_btn);
	}

}
