package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.ControladorMusica;
import modelo.GestorCanciones;

public class VMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VMenu frame = new VMenu();
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
	public VMenu() {
		try {
			GestorCanciones.getGestorCanciones().cargarCanciones();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Image iconPrincipal = new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage();
		setIconImage(iconPrincipal);
		setTitle("EUITI MUSIC PLAYER 3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		setResizable(false);
		contentPane = new JPanel();
		setBackground(Color.black);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	/*	Image icono = new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage();
		setIconImage(icono);*/
		
		
		
		ImageIcon icon= new ImageIcon(VPrincipal.class.getResource("/imagenes/botones.jpg"));
		Image image = icon.getImage();
		Image boton = image.getScaledInstance(250, 47,  java.awt.Image.SCALE_SMOOTH);
		
		
		JButton btnReproducir = new JButton("Reproducir canciones", new ImageIcon(boton));
		btnReproducir.setBackground(SystemColor.desktop);
		btnReproducir.setForeground(SystemColor.text);
		btnReproducir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	        	VElegirCanciones v= new VElegirCanciones();
	        	v.setVisible(true);
	        	dispose();
			}
		});
		

		btnReproducir.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnReproducir.setBounds(149, 59, 245, 40);
		btnReproducir.setHorizontalTextPosition(SwingConstants.CENTER);
		btnReproducir.setVerticalTextPosition(SwingConstants.CENTER);
		contentPane.add(btnReproducir);
		
		
		
		JButton botonGestionar = new JButton("Gestionar Listas", new ImageIcon(boton));
		botonGestionar.setBackground(SystemColor.desktop);
		botonGestionar.setForeground(SystemColor.text);
		botonGestionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VListasReproduccion v= new VListasReproduccion();
	        	v.setVisible(true);
	        	dispose();
				
			}
		});
		
		botonGestionar.setFont(new Font("Tahoma", Font.BOLD, 19));
		botonGestionar.setBounds(149, 115, 245, 40);
		botonGestionar.setHorizontalTextPosition(SwingConstants.CENTER);
		botonGestionar.setVerticalTextPosition(SwingConstants.CENTER);
		contentPane.add(botonGestionar);
		
		
		JButton btnPerfil = new JButton("Modificar Perfil", new ImageIcon(boton));
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VModificarPerfil v= new VModificarPerfil();
				v.setVisible(true);
				dispose();
			}
		});
		btnPerfil.setBackground(SystemColor.desktop);
		btnPerfil.setForeground(Color.WHITE);
		btnPerfil.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnPerfil.setBounds(149, 177, 245, 40);
		btnPerfil.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPerfil.setVerticalTextPosition(SwingConstants.CENTER);
		contentPane.add(btnPerfil);
		
		
		JButton btnSesion = new JButton("Cerrar Sesion", new ImageIcon(boton));
		btnSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ControladorMusica.getControladorMusica().resetearListasUsuario();
				ControladorMusica.getControladorMusica().resetearDatosUsuario();
				VPrincipal v = new VPrincipal();
				v.setVisible(true);
				dispose();
			}
		});
		btnSesion.setBackground(SystemColor.desktop);
		btnSesion.setForeground(Color.WHITE);
		btnSesion.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnSesion.setBounds(149, 239, 245, 40);
		btnSesion.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSesion.setVerticalTextPosition(SwingConstants.CENTER);
		contentPane.add(btnSesion);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(Color.white);
		lblNewLabel_1.setIcon(new ImageIcon(VEscucharCancion.class.getResource("/imagenes/principal.png")));
		lblNewLabel_1.setBounds(5, 5, 484, 308);
		contentPane.add(lblNewLabel_1);
		

		centrarFrame();
		
		
	}
	public void centrarFrame() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize(); // Tamanio del frame actual (ancho x
											// alto)
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
	}
}
