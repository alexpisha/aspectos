package interfaces;

import java.awt.BorderLayout;
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
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import modelo.ControladorMusica;
import modelo.GestorListasReproduccion;
import modelo.ListaReproduccion;



public class VListasReproduccion extends JFrame  {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JRadioButton[] buttons;
	private ButtonGroup grupo;
	private JPanel panelRadioButtons;
	private Image fondo;
	private String nombreLista; 
	

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

		JLabel lblListasReproduccion = new JLabel("Listas de Reproducci�n");		
		lblListasReproduccion.setFont(new Font("Arial", Font.BOLD, 26));
		lblListasReproduccion.setHorizontalAlignment(SwingConstants.CENTER);
		lblListasReproduccion.setBounds(108, 11, 318, 25);
		contentPane.add(lblListasReproduccion);
		ImageIcon icon= new ImageIcon(VPrincipal.class.getResource("/imagenes/botones.jpg"));
		Image image = icon.getImage();
		Image boton = image.getScaledInstance(190, 47,  java.awt.Image.SCALE_SMOOTH);
		JButton btnGestionar = new JButton("Gestionar Canciones",new ImageIcon(boton));
		
		//btnAceptar.setBorder(new MatteBorder(2, 2, 2, 2, new Color(32, 102, 60)));
		btnGestionar.setBackground(new Color(245, 216, 218));
		btnGestionar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnGestionar.setVerticalTextPosition(SwingConstants.CENTER);
		btnGestionar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnGestionar.setForeground(SystemColor.text);
		btnGestionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VGestionarLista v = new VGestionarLista(nombreLista);
				dispose();
				v.setVisible(true);

			}
		});

		btnGestionar.setBounds(400, 400, 175, 45);
		contentPane.add(btnGestionar);
		JButton btnCrear = new JButton("Crear lista",new ImageIcon(boton));//FUNCIONA
		
		//btnAceptar.setBorder(new MatteBorder(2, 2, 2, 2, new Color(32, 102, 60)));
		btnCrear.setBackground(new Color(245, 216, 218)); 
		btnCrear.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCrear.setVerticalTextPosition(SwingConstants.CENTER);
		btnCrear.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCrear.setForeground(SystemColor.text);
		btnCrear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VCrearLista v = new VCrearLista();
				dispose();
				v.setVisible(true);

			}
		});
		btnCrear.setBounds(135, 400, 100, 45);
		contentPane.add(btnCrear);
		
		JButton btnCancelar = new JButton("Atras",new ImageIcon(boton)); //FUNCIONA
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
		JButton btnBorrar = new JButton("Borrar lista",new ImageIcon(boton)); //FUNCIONA
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ButtonModel b= grupo.getSelection();
				if(b!=null){
					int resp=JOptionPane.showConfirmDialog(null,"�Est�s seguro de que quieres borrar la lista?");
					if (JOptionPane.OK_OPTION == resp){
						int aux= GestorListasReproduccion.getGestorListasReproduccion().eliminarListaRep(nombreLista);
						if(aux!=-1){
							JOptionPane.showMessageDialog(null, "La lista de reproducci�n ha sido eliminada.");
							VListasReproduccion v = new VListasReproduccion();
							dispose();
							v.setVisible(true);
						}
						else{
							JOptionPane.showMessageDialog(null, "Lo sentimos, ha ocurrido un error.");

						}
				     }
				}
			}
		});
		
		btnBorrar.setForeground(SystemColor.text);
		btnBorrar.setBackground(new Color(245, 216, 218));
		btnBorrar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBorrar.setVerticalTextPosition(SwingConstants.CENTER);
		btnBorrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnBorrar.setBounds(240, 400, 125, 45);
		contentPane.add(btnBorrar);
		centrarFrame();
		addListas();
	}

	
	private void addListas() {
		String usuario=ControladorMusica.getControladorMusica().getUsuario().getNombre();
		ArrayList<ListaReproduccion> lista = GestorListasReproduccion.getGestorListasReproduccion().getListasReprod(usuario);
		imprimirLista(lista);
		buttons = new JRadioButton[lista.size()];
		
		grupo = new ButtonGroup();
		JScrollPane scrollPane = new JScrollPane();
		panelRadioButtons = new JPanel();
		panelRadioButtons.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 139)));
		panelRadioButtons.setBackground(SystemColor.menu);
		panelRadioButtons.setBounds(10, 47, 491, 203);

		int i = 0;
		
		for (ListaReproduccion unalista : lista) {
			String nombre=unalista.getNombreLista();
			System.out.println(nombre);
			
			JRadioButton btn = new JRadioButton(
					"-->: " + nombre);
			System.out.println(nombre);

			//btn.addActionListener(this);
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println(nombreLista);
					System.out.println(nombre);
					nombreLista = nombre;
					System.out.println(nombre);
					System.out.println(nombreLista);
					
				}
			});
			btn.setName(nombre);
			grupo.add(btn);
			panelRadioButtons.add(btn);
			buttons[i] = btn;
			i++;
			
		}
		if(buttons.length>0){
			buttons[0].setSelected(true);
			nombreLista = buttons[0].getName();
		}
		//grupo.clearSelection();
		scrollPane.setBounds(10, 47, 565, 342);
		scrollPane.setViewportView(panelRadioButtons);
		panelRadioButtons.setLayout(new GridLayout(lista.size(), 1));
		contentPane.add(scrollPane);
	}


	
	

	private void cerrar() {
		this.dispose();
	}
	
	private void imprimirLista(ArrayList<ListaReproduccion> lista){
		for(int i=0; i<lista.size(); i++){
			System.out.println(lista.get(i).getNombreLista());
		}
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
