package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



import javax.swing.SwingConstants;

import modelo.ControladorMusica;
import modelo.SGBD;

import javax.swing.JPasswordField;

public class VIdentificarse extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUser;
	private JTextField textPass;
	private JPasswordField passwordField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VIdentificarse frame = new VIdentificarse();
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
	public VIdentificarse() {
		Image iconPrincipal = new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage();
		setIconImage(iconPrincipal);
		setTitle("EUITI MUSIC PLAYER 3");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelTitulo(), BorderLayout.NORTH);
		contentPane.add(getPanelInsertar(), BorderLayout.CENTER);
		

		setContentPane(contentPane);
	}
	
	private JPanel getPanelTitulo(){
		JPanel panelTitulo = new JPanel();
		JLabel lblBienvenida = new JLabel("Identificarse");
		lblBienvenida.setVerticalAlignment(SwingConstants.BOTTOM);
		lblBienvenida.setFont(new Font("Arial", Font.BOLD, 26));
		panelTitulo.add(lblBienvenida);
		return panelTitulo;
	}


	
	private JPanel getPanelInsertar(){
		JPanel panelInsertar = new JPanel();
		panelInsertar.setLayout(null);
		
		JLabel lblUser = new JLabel("Usuario:");
		lblUser.setFont(new Font("Arial", Font.PLAIN, 17));
		lblUser.setBounds(23, 30, 71, 46);
		panelInsertar.add(lblUser);
		
		textUser = new JTextField();
		textUser.setBounds(138, 43, 272, 22);
		panelInsertar.add(textUser);
		textUser.setColumns(10);
			
		JLabel lblPass = new JLabel("Contrase\u00F1a:");
		lblPass.setFont(new Font("Arial", Font.PLAIN, 17));
		lblPass.setBounds(23, 94, 97, 22);
		panelInsertar.add(lblPass);
		
		
		textPass = new JPasswordField();
		textPass.setBounds(138, 95, 272, 23);
		panelInsertar.add(textPass);
		textPass.setColumns(10);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String user=textUser.getText();
	            String pass= textPass.getText();
	            if(user.isEmpty() || pass.isEmpty()){
					JOptionPane.showMessageDialog(null, "Es necesario rellenar todos los campos. Pulsa aceptar e inténtalo de nuevo.");
	            }else{
					if(SGBD.getSGBD().existeUsuario(user)){
						boolean coincide = false;
						coincide= SGBD.getSGBD().validarUsuario(user, pass);
						if(coincide){
							String email = SGBD.getSGBD().obtenerCorreo(user);
							ControladorMusica.getControladorMusica().establecerDatosUsuario(user, pass, email);
					   		VMenu menu = new VMenu();
							menu.setVisible(true);
							dispose();
						}
						else{
							JOptionPane.showMessageDialog(null, "El usuario y la contraseña no coinciden. Pulsa aceptar e inténtalo de nuevo.");
							setVisible(true);
						}	
					}
					else{
						JOptionPane.showMessageDialog(null, "Los sentimos. El usuario no se encuentra registrado en el sistema.");
					}
	        }
	    }});

		btnAceptar.setBounds(245, 209, 97, 25);
		panelInsertar.add(btnAceptar);
				
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	VPrincipal v= new VPrincipal();
	        	v.setVisible(true);
	        	dispose();
	        
	    }});
		cancelar.setBounds(138, 209, 97, 25);
		panelInsertar.add(cancelar);
		
		
		JLabel fondo = new JLabel();
		fondo.setIcon(new ImageIcon(VEscucharCancion.class.getResource("/imagenes/ecualizador1.gif")));
		fondo.setBounds(5, 45, 484, 308);
		panelInsertar.add(fondo);
		
		
		return panelInsertar;
	}	
}
