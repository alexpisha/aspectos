package interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import modelo.Cancion;
import modelo.GestorCanciones;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

public class VElegirCanciones extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JCheckBox chckbx;
	private static ArrayList<Cancion> lista;
	private boolean crearLista=false;
	private boolean addCanciones=false;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VElegirCanciones frame = new VElegirCanciones();
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
	public VElegirCanciones() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSeleccionarCanciones = new JLabel("Buscar canciones");
		lblSeleccionarCanciones.setFont(new Font("Arial", Font.BOLD, 26));
		lblSeleccionarCanciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionarCanciones.setBounds(10, 11, 474, 30);
		contentPane.add(lblSeleccionarCanciones);
		
		textField = new JTextField();
		textField.setBounds(103, 52, 367, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Titulo", "Autor", "Genero"}));
		comboBox.setBounds(10, 52, 83, 20);
		contentPane.add(comboBox);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lista = new ArrayList<Cancion>();
				if(chckbx.isSelected()){
					lista = GestorCanciones.getGestorCanciones().getTodasLasCanciones();
				}	
				else{
					String seleccion = comboBox.getSelectedItem().toString();
					if(seleccion.equals("Titulo")){
						Cancion c = GestorCanciones.getGestorCanciones().buscarCancionPorTitulo(textField.getText());
						if(c!=null){
							lista.add(c);
						}
					}else if(seleccion.equals("Genero")){
						lista = GestorCanciones.getGestorCanciones().buscarCancionesPorAlbum(textField.getText());
					}
					else {
						lista = GestorCanciones.getGestorCanciones().buscarCancionesPorAutores(textField.getText());
					}
				}

				if(lista.isEmpty()){
					JOptionPane.showMessageDialog(null, "No hemos encontrado canciones. Revisa el campo introducido e int�ntalo de nuevo.");
				}
				else{
					VSeleccionarCanciones v;
					v = new VSeleccionarCanciones();
					if(crearLista){
						v.setCrearLista();
					}
					if(addCanciones){
						v.setAddCanciones();
					}
					setVisible(false);
					v.setVisible(true);
				}
			}
		});
		btnAceptar.setBounds(255, 140, 88, 23);
		contentPane.add(btnAceptar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VMenu v = new VMenu();
				setVisible(false);
				v.setVisible(true);
			}
		});
		btnVolver.setBounds(170, 140, 78, 23);
		contentPane.add(btnVolver);
		
		JLabel labelMostrandoTitulo = new JLabel("");
		labelMostrandoTitulo.setBounds(10, 138, 182, 14);
		contentPane.add(labelMostrandoTitulo);
		
		JLabel labelMostrandoAutores = new JLabel("");
		labelMostrandoAutores.setBounds(205, 138, 170, 14);
		contentPane.add(labelMostrandoAutores);
		
		
		chckbx = new JCheckBox("No deseo buscar canciones por t\u00EDtulo o autor.");
		chckbx.setBounds(10, 81, 321, 25);
		contentPane.add(chckbx);
		centrarFrame();
		
	}
	
	public static ArrayList<Cancion> getListaCanciones(){
		return lista;
	}
	
	public void setCrearLista(){
		crearLista= !crearLista;
	}
	public void setAddCanciones(){
		addCanciones= !addCanciones;
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
