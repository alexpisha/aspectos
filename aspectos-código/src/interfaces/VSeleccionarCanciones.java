package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


import modelo.Cancion;
import modelo.ControladorMusica;
import modelo.SGBD;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;

public class VSeleccionarCanciones extends JFrame {

	private JPanel contentPane;
	private ArrayList<Cancion> listaEntera;
	private ArrayList<JCheckBox> listaCheck;
	private ArrayList<Cancion> listaSeleccionadas;
	private JTextArea textArea;
	private JTextField textField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VSeleccionarCanciones frame = new VSeleccionarCanciones();
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
	public VSeleccionarCanciones() {
		Image iconPrincipal = new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage();
		setIconImage(iconPrincipal);
		setTitle("EUITI MUSIC PLAYER 3");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setListaEntera(ControladorMusica.getControladorMusica().getTodasLasCanciones());
		setListaCheck();
		contentPane.add(getPanelTitulo(), BorderLayout.NORTH);
		contentPane.add(getPanelInsertar(), BorderLayout.CENTER);
		contentPane.add(getPanelBotones(), BorderLayout.SOUTH);

		setContentPane(contentPane);
	}
	
	private JPanel getPanelTitulo(){
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(new BorderLayout(0, 0));

		JLabel lblBienvenida = new JLabel("Seleccionar canciones");
		lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenida.setFont(new Font("Arial", Font.BOLD, 26));
		panelTitulo.add(lblBienvenida, BorderLayout.NORTH);
		
		textField = new JTextField("Busca una cancion por título, álbum o autor.");
		textField.setColumns(5);
		panelTitulo.add(textField, BorderLayout.CENTER);
		
		
		JButton ok= new JButton("OK");
		panelTitulo.add(ok, BorderLayout.EAST);
		
		JLabel lbl = new JLabel("            ");
		lblBienvenida.setFont(new Font("Arial", Font.BOLD, 26));
		panelTitulo.add(lbl, BorderLayout.SOUTH);
		
		
		return panelTitulo;
	}

	private JPanel getPanelBotones(){
		JPanel panelBotones = new JPanel();
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	getYSetSeleccionados();
	        	VEscucharCancion.setListaCanciones(listaSeleccionadas);
	        	System.out.println(listaSeleccionadas);
	        	VEscucharCancion v;
				try {
					v = new VEscucharCancion();
					v.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
	        	dispose();
	        }});

		btnAceptar.setBounds(138, 211, 97, 25);
		panelBotones.add(btnAceptar);
				
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	VMenu v= new VMenu();
	        	v.setVisible(true);
	        	dispose();
	        }});
		cancelar.setBounds(274, 211, 97, 25);
		panelBotones.add(cancelar);
		return panelBotones;
	}
	
	private JScrollPane getPanelInsertar(){
		JPanel panelInsertar = new JPanel();
		panelInsertar.setLayout(new GridLayout(0, 1, 10, 10));
		
		
		System.out.println(listaEntera.size());
		for(int i=0; i<listaCheck.size(); i++){
			JCheckBox checkbox = listaCheck.get(i);
			checkbox.setHorizontalAlignment(SwingConstants.CENTER);
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
}


