package interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;



public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Clip clip;
	private AudioInputStream ais;
	private Image fondo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws NoArchivoAudioException
	 */
	public Menu()  {
		
	
		fondo = new ImageIcon(getClass().getResource("/imagenes/ecualizador.jpg")).getImage();
		// SONIDO-INICIO
		
		
		// SONIDO FIN

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
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

		

		JButton btnCargarPartida = new JButton("Reproducir");
		btnCargarPartida.setBounds(182, 187, 138, 33);
		btnCargarPartida.setForeground(Color.WHITE);
		btnCargarPartida.setBackground(new Color(73, 106, 144));
		btnCargarPartida.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCargarPartida.setBorder(new MatteBorder(2, 2, 2, 2, new Color(255, 255, 255)));
		
		contentPane.add(btnCargarPartida);

		

		

	}

	private void cerrar() {
		this.dispose();
	}
}
