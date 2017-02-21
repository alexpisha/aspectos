package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class VPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VPrincipal frame = new VPrincipal();
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
	public VPrincipal() {
		setTitle("EUITI MUSIC PLAYER 3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		setResizable(false);
		contentPane = new JPanel();
		setBackground(Color.black);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		
		ImageIcon icon= new ImageIcon(VPrincipal.class.getResource("/imagenes/botones.jpg"));
		Image image = icon.getImage();
		Image boton = image.getScaledInstance(163, 47,  java.awt.Image.SCALE_SMOOTH);
		
		
		JButton btnIdentificarse = new JButton("Identificarse", new ImageIcon(boton));
		btnIdentificarse.setBackground(SystemColor.desktop);
		btnIdentificarse.setForeground(SystemColor.text);
		btnIdentificarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VIdentificarse.main(null);
			}
		});
		

		btnIdentificarse.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnIdentificarse.setBounds(173, 91, 163, 47);
		btnIdentificarse.setHorizontalTextPosition(SwingConstants.CENTER);
		btnIdentificarse.setVerticalTextPosition(SwingConstants.CENTER);
		contentPane.add(btnIdentificarse);
		
		
		
		JButton botonRegistrarse = new JButton("Registrarse", new ImageIcon(boton));
		botonRegistrarse.setBackground(SystemColor.desktop);
		botonRegistrarse.setForeground(SystemColor.text);
		botonRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		botonRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 19));
		botonRegistrarse.setBounds(173, 187, 163, 47);
		botonRegistrarse.setHorizontalTextPosition(SwingConstants.CENTER);
		botonRegistrarse.setVerticalTextPosition(SwingConstants.CENTER);
		contentPane.add(botonRegistrarse);
		
		/*JButton botonRegistrarse = new JButton("Registrarse");
		botonRegistrarse.setForeground(SystemColor.desktop);
		botonRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		botonRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 19));
		botonRegistrarse.setBounds(173, 187, 163, 47);
		contentPane.add(botonRegistrarse);*/
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(Color.white);
		lblNewLabel_1.setIcon(new ImageIcon(VEscucharCanción.class.getResource("/imagenes/principal.png")));
		lblNewLabel_1.setBounds(5, 5, 484, 308);
		contentPane.add(lblNewLabel_1);
		
		
		
	}
}
