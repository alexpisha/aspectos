package interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JTextPane;

public class VEscucharCanción extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VEscucharCanción frame = new VEscucharCanción();
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
	
	public VEscucharCanción() {
		setTitle("EUITI MUSIC PLAYER 3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEstasIdentificadoComo = new JLabel("Estas identificado como:");
		lblEstasIdentificadoComo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstasIdentificadoComo.setForeground(Color.WHITE);
		lblEstasIdentificadoComo.setBounds(10, 11, 147, 14);
		contentPane.add(lblEstasIdentificadoComo);
		
		JLabel lblIdUsuario = new JLabel("Pepito Garcia");
		lblIdUsuario.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIdUsuario.setForeground(Color.WHITE);
		lblIdUsuario.setBounds(158, 11, 147, 14);
		contentPane.add(lblIdUsuario);
		
		JButton btnCerrarSesion = new JButton("Cerrar sesi\u00F3n");
		btnCerrarSesion.setBounds(459, 11, 120, 23);
		contentPane.add(btnCerrarSesion);
		
		JLabel lblEstasEscuchando = new JLabel("Estas escuchando:");
		lblEstasEscuchando.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblEstasEscuchando.setForeground(Color.WHITE);
		lblEstasEscuchando.setBounds(10, 290, 569, 23);
		contentPane.add(lblEstasEscuchando);
		
		JLabel labelNombreCanción = new JLabel("...");
		labelNombreCanción.setForeground(Color.WHITE);
		labelNombreCanción.setFont(new Font("Tahoma", Font.BOLD, 17));
		labelNombreCanción.setBounds(10, 324, 569, 55);
		contentPane.add(labelNombreCanción);
		
		JButton btnPlayPause = new JButton("Play / Pause");
		btnPlayPause.setBounds(229, 256, 131, 23);
		contentPane.add(btnPlayPause);
		
		JButton btnAnterior = new JButton("Anterior");
		btnAnterior.setBounds(130, 256, 89, 23);
		contentPane.add(btnAnterior);
		
		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBounds(370, 256, 89, 23);
		contentPane.add(btnSiguiente);
	
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(VEscucharCanción.class.getResource("/imagenes/imagen_play.png")));
		lblNewLabel_1.setBounds(0, 0, 589, 390);
		contentPane.add(lblNewLabel_1);
		
	}
}
