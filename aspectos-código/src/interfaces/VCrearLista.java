package interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import modelo.ControladorMusica;
import modelo.GestorListasReproduccion;



public class VCrearLista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {

					VCrearLista frame = new VCrearLista();

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VCrearLista() {
		setTitle("Crear lista de reproduccion");
		
		

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel() {
			private static final long serialVersionUID = 1L;

		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBorder(new MatteBorder(2, 2, 2, 2, new Color(73, 106, 144)));
		textField.setBounds(153, 142, 146, 30);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblIntroduceElNombre = new JLabel("Introduce el nombre de la lista");
		lblIntroduceElNombre.setForeground(Color.WHITE);
		lblIntroduceElNombre.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblIntroduceElNombre.setBounds(60, 89, 360, 27);
		// lblIntroduceElNombre.setBounds(137, 117, 203, 14);
		contentPane.add(lblIntroduceElNombre);

		ImageIcon icon= new ImageIcon(VPrincipal.class.getResource("/imagenes/botones.jpg"));
		Image image = icon.getImage();
		Image boton = image.getScaledInstance(190, 47,  java.awt.Image.SCALE_SMOOTH);
		JButton btnNewButton = new JButton("Aceptar",new ImageIcon(boton));
		
		//btnAceptar.setBorder(new MatteBorder(2, 2, 2, 2, new Color(32, 102, 60)));
		btnNewButton.setBackground(new Color(245, 216, 218));
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setVerticalTextPosition(SwingConstants.CENTER);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setForeground(SystemColor.text);
		
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String nombre = ControladorMusica.getControladorMusica().getUsuario().getNombre();
				
				if(GestorListasReproduccion.getGestorListasReproduccion().existeLista(nombre, textField.getText())){
					JOptionPane.showMessageDialog(null, "El nombre de la lista ya existe.");
				}
				else{
					GestorListasReproduccion.getGestorListasReproduccion().insertarListaRepr(textField.getText(), nombre);
					VListasReproduccion v = new VListasReproduccion();
					dispose();
					v.setVisible(true);
				}
				
					
			}
		});
		btnNewButton.setBounds(272, 259, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnCancelar = new JButton("Cancelar",new ImageIcon(boton));
		btnCancelar.setBackground(new Color(245, 216, 218));
		btnCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCancelar.setVerticalTextPosition(SwingConstants.CENTER);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancelar.setForeground(SystemColor.text);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VListasReproduccion v =new VListasReproduccion();
				dispose();
				v.setVisible(true);

			}
		});
		btnCancelar.setBounds(98, 259, 89, 23);
		contentPane.add(btnCancelar);
		centrarFrame();

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
