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


public class VListasReproduccion extends JFrame  {

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
					VListasReproduccion frame = new VListasReproduccion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VListasReproduccion()  {
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
		JButton btnAceptar = new JButton("Gestionar Canciones",new ImageIcon(boton));
		
		//btnAceptar.setBorder(new MatteBorder(2, 2, 2, 2, new Color(32, 102, 60)));
		btnAceptar.setBackground(new Color(245, 216, 218));
		btnAceptar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAceptar.setVerticalTextPosition(SwingConstants.CENTER);
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAceptar.setForeground(SystemColor.text);
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VGestionarLista v = new VGestionarLista();
				dispose();
				v.setVisible(true);

			}
		});

		btnAceptar.setBounds(400, 400, 175, 45);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Atras",new ImageIcon(boton));
		btnCancelar.setForeground(SystemColor.text);
		//btnCancelar.setBorder(new MatteBorder(2, 2, 2, 2, new Color(115, 35, 41)));
		btnCancelar.setBackground(new Color(245, 216, 218));
		btnCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCancelar.setVerticalTextPosition(SwingConstants.CENTER);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VMenu v = new VMenu();
				dispose();
				v.setVisible(true);
				
			}
		});
		
		btnCancelar.setBounds(30, 400, 100, 45);
		contentPane.add(btnCancelar);
		JButton btnBorrar = new JButton("Borrar lista",new ImageIcon(boton));
		
		btnBorrar.setForeground(SystemColor.text);
		btnBorrar.setBackground(new Color(245, 216, 218));
		btnBorrar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBorrar.setVerticalTextPosition(SwingConstants.CENTER);
		btnBorrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnBorrar.setBounds(250, 400, 125, 45);
		contentPane.add(btnBorrar);
		centrarFrame();
	}

	



	
	

	private void cerrar() {
		this.dispose();
	}
	
	public void centrarFrame() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize(); // Tama�o del frame actual (ancho x
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
