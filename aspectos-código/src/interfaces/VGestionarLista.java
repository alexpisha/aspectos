package interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import modelo.GestorListasReproduccion;


public class VGestionarLista extends JFrame  {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JRadioButton[] buttons;
	private ButtonGroup grupo;
	private JPanel panelRadioButtons;
	private int partidaSeleccionada;
	private String partidaSelec;
	private Image fondo;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					VGestionarLista frame = new VGestionarLista("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VGestionarLista(String nombrelista)  {
		setTitle("Listas de Reproduccion");
		Image iconPrincipal = new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage();
		setIconImage(iconPrincipal);
		setResizable(false);

		// ----------------- SONIDO ------------------------
		Image icono = new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage();
		setIconImage(icono);
		//fondo = new ImageIcon(getClass().getResource("/imagenes/ecualizador1.gif")).getImage();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblListasReproduccion = new JLabel("Listas de Reproduccion");
		lblListasReproduccion.setForeground(Color.WHITE);
		lblListasReproduccion.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblListasReproduccion.setHorizontalAlignment(SwingConstants.CENTER);
		lblListasReproduccion.setBounds(108, 11, 318, 25);
		contentPane.add(lblListasReproduccion);
		ImageIcon icon= new ImageIcon(VPrincipal.class.getResource("/imagenes/botones.jpg"));
		Image image = icon.getImage();
		Image boton = image.getScaledInstance(190, 47,  java.awt.Image.SCALE_SMOOTH);
		JButton btnAceptar = new JButton("Añadir Canciones",new ImageIcon(boton));
		btnAceptar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAceptar.setVerticalTextPosition(SwingConstants.CENTER);
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAceptar.setForeground(SystemColor.text);
		

		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GestorListasReproduccion.setNombreListaSeleccionada(nombrelista);
				VElegirCanciones v = new VElegirCanciones();
				dispose();
				v.setVisible(true);
			}
		});

		btnAceptar.setBounds(400, 400, 150, 45);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Atras",new ImageIcon(boton));
		btnCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCancelar.setVerticalTextPosition(SwingConstants.CENTER);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancelar.setForeground(SystemColor.text);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VListasReproduccion v = new VListasReproduccion();
				dispose();
				v.setVisible(true);
				
			}
		});
		
		
		
		btnCancelar.setBounds(30, 400, 100, 45);
		contentPane.add(btnCancelar);
		
		JButton btnBorrar = new JButton("Borrar Cancion",new ImageIcon(boton));
		btnBorrar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBorrar.setVerticalTextPosition(SwingConstants.CENTER);
		btnBorrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBorrar.setForeground(SystemColor.text);
		
		
		
		btnBorrar.setBounds(225, 400, 150, 45);
		contentPane.add(btnBorrar);
		centrarFrame();
	}

	



	
	

	private void cerrar() {
		this.dispose();
	}
	
	public void centrarFrame() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize(); // Tamaï¿½o del frame actual (ancho x
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
