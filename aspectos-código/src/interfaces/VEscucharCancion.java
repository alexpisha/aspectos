package interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JTextPane;

import modelo.Cancion;
import modelo.ControladorMusica;
import modelo.Reproductor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VEscucharCancion extends JFrame {

	private JPanel contentPane;
	private static ArrayList<Cancion> laLista;
	private boolean reproduciendo = true; 
	private Reproductor rp = new Reproductor();
	private int actual = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VEscucharCancion frame = new VEscucharCancion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	
	public VEscucharCancion() throws Exception {
		
		initialize();
	}
	
	public static void setListaCanciones(ArrayList<Cancion> lista){
		laLista = lista;
	}
	
	private void initialize() throws Exception {
		Image iconPrincipal = new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage();
		setIconImage(iconPrincipal);
		setTitle("EUITI MUSIC PLAYER 3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 429);
		contentPane = new JPanel();
		setResizable(false);
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
		rp.AbrirFichero(laLista.get(actual).getRuta());
		btnPlayPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(reproduciendo){
					try {
						rp.Stop();
					} catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					try {
						rp.Play();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				reproduciendo=!reproduciendo;
			}
		});
		btnPlayPause.setBounds(229, 256, 131, 23);
		contentPane.add(btnPlayPause);
		
		JButton btnAnterior = new JButton("Anterior");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					rp.AbrirFichero(laLista.get(actual).getRuta());
					rp.Play();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		actual--;
		if(actual<0){
			actual = laLista.size()-1;
		}
		btnAnterior.setBounds(130, 256, 89, 23);
		if(laLista.size()==1){
			btnAnterior.setEnabled(false);
		}else{
			btnAnterior.setEnabled(true);
		}
		contentPane.add(btnAnterior);
		
		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rp.AbrirFichero(laLista.get(actual).getRuta());
					rp.Play();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		actual++;
		if(actual>laLista.size()-1){
			actual = 0;
		}
		btnSiguiente.setBounds(370, 256, 89, 23);
		if(laLista.size()==1){
			btnSiguiente.setEnabled(false);
		}else{
			btnSiguiente.setEnabled(true);
		}
		contentPane.add(btnSiguiente);
		
		JButton btnCompartir = new JButton("Compartir");
		btnCompartir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cancion c= laLista.get(actual);
				String txt= "¡Estoy escuchando" + c.getTitulo() + "de" + c.getAutor() + "del album" +
				c.getAlbum() +" en EUITI MUSIC PLAYER 3!";
				ControladorMusica.getControladorMusica().compartirTwitter(txt);
			}
		});
		btnCompartir.setBounds(459, 45, 120, 23);
		contentPane.add(btnCompartir);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(VEscucharCancion.class.getResource("/imagenes/imagen_play.png")));
		lblNewLabel_1.setBounds(0, 0, 589, 390);
		contentPane.add(lblNewLabel_1);
		}
}
