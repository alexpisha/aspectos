package interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.Cancion;
import modelo.ControladorMusica;
import modelo.GestorListasReproduccion;

import java.awt.GridLayout;
import java.awt.Component;
import java.awt.FlowLayout;

public class VGestionarLista extends JFrame {

	private JPanel contentPane;
	private ArrayList<Cancion> listaEntera;
	private ArrayList<JCheckBox> listaCheck;
	private ArrayList<Cancion> listaSeleccionadas= new ArrayList<Cancion>();
	private JRadioButton reproducirTodas;
	private String nombreLista;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VGestionarLista frame = new VGestionarLista("a");
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
	public VGestionarLista(final String nombreLista) {
		this.nombreLista=nombreLista;
		Image iconPrincipal = new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage();
		setIconImage(iconPrincipal);
		setTitle("EUITI MUSIC PLAYER 3");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		String usuario=ControladorMusica.getControladorMusica().getUsuario().getNombre();
		setListaEntera(GestorListasReproduccion.getGestorListasReproduccion().getCancionesListaId(nombreLista, usuario));
		setListaCheck();
		contentPane.add(getPanelTitulo(), BorderLayout.NORTH);
		contentPane.add(getPanelInsertar(), BorderLayout.CENTER);
		contentPane.add(getPanelBotones(), BorderLayout.SOUTH);
		setContentPane(contentPane);
		centrarFrame();

	}
	
	private JPanel getPanelTitulo(){
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(new BorderLayout(0, 0));

		JLabel lblBienvenida = new JLabel("Gestionar Lista");
		lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenida.setFont(new Font("Arial", Font.BOLD, 26));
		panelTitulo.add(lblBienvenida, BorderLayout.NORTH);
			
		return panelTitulo;
	}

	private JPanel getPanelBotones(){
		JPanel panelBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
				
		
		JButton cancelar = new JButton("Volver");
		cancelar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	VListasReproduccion v= new VListasReproduccion();
	        	v.setVisible(true);
	        	dispose();
	        }});
		cancelar.setBounds(274, 211, 97, 25);
		panelBotones.add(cancelar);
		
		
		JButton btnAceptar = new JButton("A�adir Canciones");
		btnAceptar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	VElegirCanciones v= new VElegirCanciones();
	        	GestorListasReproduccion.setNombreListaSeleccionada(nombreLista);
	        	v.setAddCanciones();
	        	v.setVisible(true);
	        	dispose();
	    	   
	        }});
		
				btnAceptar.setBounds(138, 211, 97, 25);
				panelBotones.add(btnAceptar);
				
				JButton btnBorrar = new JButton("Borrar Canciones");
				btnBorrar.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			        	getYSetSeleccionados();
			        	if(listaSeleccionadas.size()>0 ){
			        		String usuario= ControladorMusica.getControladorMusica().getUsuario().getNombre();
			        		GestorListasReproduccion.getGestorListasReproduccion().borrarCancionesLista(listaSeleccionadas,usuario,nombreLista);
			        		dispose();
			        		VGestionarLista v= new VGestionarLista(nombreLista);
				        	v.setVisible(true);
				        	
			        	}
			        	else{
							JOptionPane.showMessageDialog(null, "Es necesario elegir como m�nimo una canci�n.");
			        	}
			    	   
			        }});
				
				btnBorrar.setBounds(138, 211, 97, 25);
				panelBotones.add(btnBorrar);
				
				JButton reproducir = new JButton("Reproducir Lista");
				reproducir.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			        	VEscucharCancion v;
						try {
							VEscucharCancion.setListaCanciones(listaEntera);
							v = new VEscucharCancion();
							v.setVisible(true);
				        	dispose();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			        	
			        }});
				reproducir.setBounds(137, 211, 97, 25);
				panelBotones.add(reproducir);
		return panelBotones;
	}
	
	private JScrollPane getPanelInsertar(){
		JPanel panelInsertar = new JPanel();
		panelInsertar.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelInsertar.setLayout(new GridLayout(0, 1, 10, 10));
		
		for(int i=0; i<listaCheck.size(); i++){
			JCheckBox checkbox = listaCheck.get(i);
			checkbox.setHorizontalAlignment(SwingConstants.LEFT);
			checkbox.setVerticalAlignment(SwingConstants.TOP);
			panelInsertar.add(checkbox);
		}
	    JScrollPane scroll = new JScrollPane(panelInsertar);
		return scroll;
	}	
	
	public void setListaEntera(ArrayList<Cancion> pL){
		listaEntera= pL;
	}
	
	private void setListaCheck(){
		ArrayList<JCheckBox> lista= new ArrayList<JCheckBox>();
		
		for(int i= 0; i<listaEntera.size();i++){
			JCheckBox checkBox = new JCheckBox(listaEntera.get(i).getTitulo());
			lista.add(checkBox);
		
		}
		
		listaCheck= lista;
	}
	
	
	private void getYSetSeleccionados(){
		ArrayList<Cancion> lista= new ArrayList<Cancion>();
		Cancion c;
		for(int i=0; i<listaCheck.size();i++){
			if(listaCheck.get(i).isSelected()){
				c=listaEntera.get(i);
				lista.add(c);
			}
		}
		listaSeleccionadas= lista;
		
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


