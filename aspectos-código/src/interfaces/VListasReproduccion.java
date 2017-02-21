package interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
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
		

		// ----------------- SONIDO ------------------------
		
		//fondo = new ImageIcon(getClass().getResource("/imagenes/ecualizador1.gif")).getImage();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 367);
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

		JButton btnAceptar = new JButton("Gestionar Canciones");
		btnAceptar.setBackground(new Color(212, 248, 227));
		btnAceptar.setBorder(new MatteBorder(2, 2, 2, 2, new Color(32, 102, 60)));

		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				

			}
		});

		btnAceptar.setBounds(361, 272, 115, 45);
		contentPane.add(btnAceptar);

		JButton btnCancelar = new JButton("Atras");
		btnCancelar.setBorder(new MatteBorder(2, 2, 2, 2, new Color(115, 35, 41)));
		btnCancelar.setBackground(new Color(245, 216, 218));
		
		
		btnCancelar.setBounds(58, 272, 115, 45);
		contentPane.add(btnCancelar);

	}

	



	
	

	private void cerrar() {
		this.dispose();
	}


}
