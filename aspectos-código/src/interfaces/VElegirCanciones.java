package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

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

public class VElegirCanciones extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

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
		setBounds(100, 100, 500, 280);
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
		textField.setBounds(103, 52, 272, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Titulo", "Autores"}));
		comboBox.setBounds(10, 52, 83, 20);
		contentPane.add(comboBox);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String seleccion = comboBox.getSelectedItem().toString();
				Cancion c;
				if(seleccion.equals("Titulo")){
					c = GestorCanciones.getGestorCanciones().buscarCancionPorTitulo(textField.getText());
				} else {
					c = GestorCanciones.getGestorCanciones().buscarCancionPorAutores(textField.getText());
				}
				ArrayList<Cancion> lista = new ArrayList<Cancion>();
				lista.add(c);
				VEscucharCancion v;
				try {
					v = new VEscucharCancion();
					v.setListaCanciones(lista);
					setVisible(false);
					v.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAceptar.setBounds(277, 206, 89, 23);
		contentPane.add(btnAceptar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VMenu v = new VMenu();
				setVisible(false);
				v.setVisible(true);
			}
		});
		btnVolver.setBounds(140, 206, 89, 23);
		contentPane.add(btnVolver);
		
		JLabel labelMostrarTitulo = new JLabel("T\u00EDtulo");
		labelMostrarTitulo.setBounds(10, 105, 182, 14);
		contentPane.add(labelMostrarTitulo);
		
		JLabel lblMostrarNombre = new JLabel("Autores");
		lblMostrarNombre.setBounds(202, 105, 180, 14);
		contentPane.add(lblMostrarNombre);
		
		JLabel labelMostrandoTitulo = new JLabel("");
		labelMostrandoTitulo.setBounds(10, 138, 182, 14);
		contentPane.add(labelMostrandoTitulo);
		
		JLabel labelMostrandoAutores = new JLabel("");
		labelMostrandoAutores.setBounds(205, 138, 170, 14);
		contentPane.add(labelMostrandoAutores);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String seleccion = comboBox.getSelectedItem().toString();
				Cancion c;
				if(seleccion.equals("Titulo")){
					c = GestorCanciones.getGestorCanciones().buscarCancionPorTitulo(textField.getText());
				} else {
					c = GestorCanciones.getGestorCanciones().buscarCancionPorAutores(textField.getText());
				}
				if (c==null){
					JOptionPane.showMessageDialog(null, "No hemos encontrado la canción. Revisa el campo introducido e inténtalo de nuevo.");
				} else {
					labelMostrandoTitulo.setText(c.getTitulo());
					labelMostrandoAutores.setText(c.getAutor());
				}
			}
		});
		btnBuscar.setBounds(385, 51, 89, 23);
		contentPane.add(btnBuscar);
		
		
	}
}
